package net.minebit.networking.communication.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Function;

import net.minebit.networking.communication.RequestPacket;
import net.minebit.networking.communication.ResponsePacket;
import net.minebit.networking.conversations.requests.AbstractRequest;
import net.minebit.networking.conversations.responses.AbstractResponse;
import net.minebit.networking.converting.ConversionHandler;
import net.minebit.networking.exceptions.communication.CommunicationException;
import net.minebit.networking.exceptions.communication.client.ClientException;
import net.minebit.networking.exceptions.conversions.ConversionException;
import net.minebit.networking.handlers.IExceptionHandler;
import net.minebit.networking.handlers.IResponseHandler;
import net.minebit.networking.miscellaneous.Pair;

/**
 * This class represents the client in a client-server communication.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class Client {

	public static final byte IDLE_BREAKER = -1;

	private boolean running = false;
	private SocketChannel channel;
	private ByteBuffer buffer;
	private ByteBuffer sendBuffer;
	private ByteBuffer readBuffer;
	private EClientRead readStage = EClientRead.IDLE;
	private int packetSize;

	private final InetSocketAddress address;
	private final int bufferSize;
	private final ThreadPoolExecutor executor;
	private final boolean compress;
	private final IExceptionHandler exceptionHandler;

	private final Map<Long, AbstractRequest> conversations = new HashMap<>();
	private final Deque<byte[]> requests = new ArrayDeque<>();
	private final Object mutex = new Object();
	private final List<IResponseHandler> responseHandlerList = new ArrayList<>();
	private final Runnable clientLoop = () -> {
		while (true) {
			synchronized (this.mutex) {
				if (!running) {
					break;
				}
				this.write();
				this.read();
			}
		}
	};

	/**
	 * This constructor constructs a new {@link Client} with the given address and
	 * byte buffer size.
	 * 
	 * @param address    The address of the server to connect to
	 * @param bufferSize The size of the buffers used for communication.S
	 * @throws ClientException If an error occurs while constructing the client
	 */
	public Client(InetSocketAddress address, int bufferSize, ThreadPoolExecutor executor, boolean compress, IExceptionHandler exceptionHandler) throws ClientException {
		if (address == null) {
			throw new ClientException("The given address cannot be NULL!");
		}
		if (executor == null) {
			throw new ClientException("The given executor cannot be NULL!");
		}
		this.address = address;
		this.bufferSize = bufferSize;
		this.executor = executor;
		this.compress = compress;
		this.exceptionHandler = exceptionHandler;
	}

	/**
	 * This method starts the client
	 * 
	 * @throws ClientException If an error occurs while the client is starting
	 */
	public void start() throws ClientException {
		synchronized (this.mutex) {
			if (this.running) {
				throw new ClientException("The client is already running!");
			}
			this.clear();
			try {
				this.channel = SocketChannel.open();
				this.channel.configureBlocking(false);
				this.channel.connect(address);
				while (!this.channel.finishConnect())
					;
			} catch (IOException exception) {
				throw new ClientException("An error occured while connecting the client to the server!", exception);
			}
			this.buffer = ByteBuffer.allocate(this.bufferSize);
			this.executor.execute(clientLoop);
			this.running = true;
		}
	}

	/**
	 * This method clears the client's previous data.
	 */
	private void clear() {
		this.channel = null;
		this.buffer = null;
	}

	/**
	 * This method stops the client
	 * 
	 * @throws ClientException If an error occurs while the client is stopping
	 */
	public void stop() throws ClientException {
		synchronized (this.mutex) {
			if (!this.running) {
				throw new ClientException("The client is already stopped!");
			}
			try {
				this.channel.close();
			} catch (IOException exception) {
				throw new ClientException("An error occured while closing the channel!", exception);
			}
			this.running = false;
		}
	}

	/**
	 * This method add the given request to the deque to be sent.
	 * 
	 * @param request The request to send
	 * @throws ClientException If an error occurs while adding the request to the
	 *                         deque
	 */
	public void send(AbstractRequest request) throws ClientException {
		synchronized (this.mutex) {
			if (!this.running) {
				throw new ClientException("The client is not currently running!");
			}
			if (request == null) {
				throw new ClientException("The given request cannot be NULL!");
			}
			long id = 0;
			while (this.conversations.containsKey(id)) {
				id = id + 1;
			}
			request.setConversationId(id);
			this.conversations.put(id, request);
			RequestPacket packet;
			try {
				packet = new RequestPacket(request, this.compress);
				this.requests.addLast(packet.asBytes());
			} catch (CommunicationException exception) {
				throw new ClientException("An error occured while adding the packet to the deque!", exception);
			}
		}
	}

	/**
	 * This method writes the data that have to get send to the server.
	 */
	private void write() {
		if (this.sendBuffer == null) {
			if (this.requests.size() == 0) {
				return;
			}
			byte[] nextBytes = this.requests.pollFirst();
			this.sendBuffer = ByteBuffer.allocate(nextBytes.length + 5);
			try {
				this.sendBuffer.put(Client.IDLE_BREAKER);
				this.sendBuffer.put(ConversionHandler.toBytes(nextBytes.length));
				this.sendBuffer.put(nextBytes);
				this.sendBuffer.position(0);
			} catch (ConversionException exception) {
				if (this.exceptionHandler != null) {
					this.exceptionHandler.handle(exception);
				}
			}
		}
		byte[] bytes = new byte[Math.min(this.sendBuffer.remaining(), this.bufferSize)];
		this.sendBuffer.get(bytes);
		this.buffer.position(this.bufferSize - bytes.length);
		this.buffer.put(bytes);
		this.buffer.position(this.bufferSize - bytes.length);
		try {
			this.channel.write(this.buffer);
		} catch (IOException exception) {
			if (this.exceptionHandler != null) {
				this.exceptionHandler.handle(exception);
			}
		}
		if (this.sendBuffer.position() == this.sendBuffer.limit()) {
			this.sendBuffer = null;
		}
	}

	/**
	 * This method adds a response handler which will be called when a response is
	 * obtained.
	 * 
	 * @param The handler to register
	 * @throws If the given handler is null
	 */
	public void addResponseHandler(IResponseHandler handler) throws ClientException {
		synchronized (mutex) {
			if (handler == null) {
				throw new ClientException("The given handler cannot be null!");
			}
			this.responseHandlerList.add(handler);
		}
	}

	/**
	 * This method returns whether the client is currently writing to the channel.
	 * 
	 * @return Whether the client is writing to the server
	 */
	public boolean isWriting() {
		synchronized (this.mutex) {
			return requests.size() != 0 || sendBuffer != null;
		}
	}
	
	/**
	 * This method reads the data sent from the server
	 */
	private void read() {
		if (this.readBuffer == null) {
			this.readBuffer = ByteBuffer.allocate(this.readStage.getFunction().apply(this));
		}
		this.buffer.position(this.bufferSize - Math.min(this.readBuffer.remaining(), this.bufferSize));
		try {
			this.channel.read(buffer);
		} catch (IOException exception) {
			if (this.exceptionHandler != null) {
				this.exceptionHandler.handle(exception);
			}
			return;
		}
		this.buffer.position(this.bufferSize - Math.min(this.readBuffer.remaining(), this.bufferSize));
		this.readBuffer.put(buffer);
		if (this.readBuffer.position() == this.readBuffer.limit()) {
			byte[] bytes = this.readBuffer.array();
			this.readBuffer.position(0);
			switch (this.readStage) {
			case IDLE:
				if (this.readBuffer.hasRemaining() && this.readBuffer.get() == Client.IDLE_BREAKER) {
					this.readStage = EClientRead.SIZE;
				}
				break;
			case SIZE:
				try {
					this.packetSize = (int) ConversionHandler.toObject(bytes, Integer.class);
				} catch (ConversionException exception) {
					if (this.exceptionHandler != null) {
						this.exceptionHandler.handle(exception);
					}
					return;
				}
				this.readStage = EClientRead.PACKET;
				break;
			case PACKET:
				ResponsePacket packet = null;
				try {
					packet = new ResponsePacket(bytes, this.compress);
				} catch (CommunicationException exception) {
					if (this.exceptionHandler != null) {
						this.exceptionHandler.handle(exception);
					}
					return;
				}
				AbstractResponse response = packet.getSendable();
				Pair<Long, AbstractResponse> handlerPair = new Pair<>(response.getConversationId(), response);
				for (IResponseHandler handler : this.responseHandlerList) {
					handler.handle(handlerPair);
				}
				this.readStage = EClientRead.IDLE;
				break;
			}
			this.readBuffer = null;
		}
	}
	
	/**
	 * This method returns whether the client is currently reading from the channel.
	 * 
	 * @return Whether the client is reading from the server
	 */
	public boolean isReading() {
		synchronized (this.mutex) {
			return this.readStage == EClientRead.IDLE;
		}
	}
	
	/**
	 * This enum represents the current reading stage of the client.
	 * 
	 * @author Aggelowe
	 * @since 0.1
	 *
	 */
	private enum EClientRead {
		
		IDLE((client) -> 1), SIZE((client) -> 4), PACKET((client) -> client.packetSize);
		
		private final Function<Client, Integer> function;
		
		private EClientRead(Function<Client, Integer> function) {
			this.function = function;
		}

		/**
		 * This method returns the function used to obtain the size of the data equivalent to each stage
		 * 
		 * @return The size function
		 */
		public Function<Client, Integer> getFunction() {
			return function;
		}
		
	}
	
}