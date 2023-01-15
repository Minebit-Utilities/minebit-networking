package net.minebit.networking.communication.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import net.minebit.networking.communication.CommunicationReference;
import net.minebit.networking.communication.packets.PacketHandlerDyad;
import net.minebit.networking.communication.packets.RequestPacketHandler;
import net.minebit.networking.communication.packets.ResponsePacketHandler;
import net.minebit.networking.conversations.requests.AbstractRequest;
import net.minebit.networking.conversations.responses.AbstractResponse;
import net.minebit.networking.conversions.primitives.IntegerConverter;
import net.minebit.networking.conversions.primitives.LongConverter;
import net.minebit.networking.exceptions.ConversionException;
import net.minebit.networking.exceptions.communication.ClientException;
import net.minebit.networking.exceptions.communication.PacketException;
import net.minebit.networking.exceptions.communication.ServerException;
import net.minebit.networking.miscellaneous.Pair;
import net.minebit.networking.wrappers.CompressionWrapper;

/**
 * This class represents a server taking part in a client-server web
 * communication
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class Server {

	private final Object mutex = new Object();
	private final Runnable loop = () -> loop();
	private final HashMap<Long, Long> requests = new HashMap<>();
	private final HashMap<Long, Set<Pair<Long, AbstractResponse>>> responses = new HashMap<>();
	private final HashMap<Long, List<byte[]>> cache = new HashMap<>();
	private final InetSocketAddress address;
	private final PacketHandlerDyad handlers;
	private final IServerListener listener;

	private boolean running = false;
	private ServerSocketChannel channel;
	private Selector selector;
	private Executor executor = Executors.newFixedThreadPool(1);
	private long lastClientId;
	private long lastConversationId;

	/**
	 * This constructor constructs a new server that takes part in a web
	 * communication.
	 * 
	 * @param address  The address to bind the server to.
	 * @param listener The listener used to handle server events.
	 * @throws ServerException If an error occurs while initializing the server.
	 */
	public Server(InetSocketAddress address, IServerListener listener) throws ServerException {
		this(address, listener, new PacketHandlerDyad(CompressionWrapper.getInstance()));
	}

	/**
	 * This constructor constructs a new server that takes part in a web
	 * communication.
	 * 
	 * @param address  The address to bind the server to.
	 * @param listener The listener used to handle server events.
	 * @param handlers The pair of handlers used to convert sendables to bytes and
	 *                 vice versa.
	 * @throws ServerException If an error occurs while initializing the server.
	 */
	public Server(InetSocketAddress address, IServerListener listener, PacketHandlerDyad handlers) throws ServerException {
		if (address == null) {
			throw new ServerException("The given address cannot be NULL!");
		}
		if (listener == null) {
			throw new ServerException("The given listener cannot be NULL!");
		}
		if (handlers == null) {
			throw new ServerException("The given handler dyad cannot be NULL!");
		}
		this.address = address;
		this.listener = listener;
		this.handlers = handlers;
	}

	/**
	 * This method starts the main server loop that handles incoming connections and
	 * data.
	 */
	private void loop() {
		while (true) {
			synchronized (this.mutex) {
				if (!this.running) {
					break;
				}
				try {
					this.selector.select();
				} catch (IOException exception) {
					this.listener.onException(new ServerException("An error occured selecting the channels!", exception));
				}
				Set<SelectionKey> keys = this.selector.selectedKeys();
				Iterator<SelectionKey> keysAsIterator = keys.iterator();
				while (keysAsIterator.hasNext()) {
					SelectionKey key = keysAsIterator.next();
					if (key.isAcceptable()) {
						try {
							SocketChannel client = this.channel.accept();
							client.configureBlocking(false);
							client.register(this.selector, SelectionKey.OP_READ);
						} catch (IOException exception) {
							this.listener.onException(new ServerException("An error occured while accepting a client!", exception));
						}
					}
					if (key.isReadable()) {
						try {
							this.readData(key);
						} catch (ServerException exception) {
							this.listener.onException(new ServerException("An error occured while reading a client's data!", exception));
						}
					}
					keysAsIterator.remove();
				}
			}
		}
	}

	/**
	 * This method starts the server and opens the connection for more clients.
	 * 
	 * @throws ServerException If an error occurs while starting the server
	 */
	public void start() throws ServerException {
		synchronized (this.mutex) {
			if (this.running) {
				throw new ServerException("The client is already running!");
			}
			this.running = true;
			try {
				this.channel = ServerSocketChannel.open();
				this.channel.bind(address);
				this.channel.configureBlocking(false);
				this.selector = Selector.open();
				this.channel.register(this.selector, SelectionKey.OP_ACCEPT);
			} catch (IOException exception) {
				throw new ServerException("An error occured while starting the server!", exception);
			}
			this.executor.execute(this.loop);
		}
	}

	/**
	 * This method stops the stops.
	 * 
	 * @throws ServerException If an error occurs while stopping the server.
	 */
	public void stop() throws ServerException {
		synchronized (this.mutex) {
			if (!this.running) {
				throw new ServerException("The client is already stopped!");
			}
			this.running = false;
			try {
				this.channel.close();
				this.selector.close();
			} catch (IOException exception) {
				throw new ServerException("An error occured while stopping the server!", exception);
			}
			this.channel = null;
			this.selector = null;
			this.cache.clear();
			this.requests.clear();
			this.responses.clear();
		}
	}

	/**
	 * This method adds the given response to be sent when the client that sent the
	 * request updates.
	 * 
	 * @param id       The id of the original request.
	 * @param response The response to reply with
	 * @throws ServerException If an error occurs while registering the response.
	 */
	public void replyAsynchronous(long id, AbstractResponse response) throws ServerException {
		synchronized (this.mutex) {
			if (response == null) {
				throw new ServerException("The given response cannot be null!");
			}
			if (!requests.containsKey(id)) {
				throw new ServerException("The given id isn't respective to any request send by any client!");
			}
			long clientId = requests.get(id);
			if (!this.responses.containsKey(clientId) || this.responses.get(clientId) == null) {
				this.responses.put(clientId, new HashSet<>());
			}
			this.responses.get(clientId).add(new Pair<>(id, response));
		}
	}

	/**
	 * This method handles the data received from a client.
	 * 
	 * @param key The key that contains the client sending the data.
	 * @throws ServerException If an error occurs while reading the data
	 */
	protected void readData(SelectionKey key) throws ServerException {
		if (key.attachment() == null) {
			while (this.cache.containsKey(this.lastClientId)) {
				this.lastClientId++;
				System.out.println("Id: " + lastClientId);
			}
			key.attach(Long.valueOf(this.lastClientId));
			this.cache.put(this.lastClientId, new ArrayList<>());
		}
		long id = (Long) key.attachment();
		SocketChannel channel = (SocketChannel) key.channel();
		List<byte[]> cache = this.cache.get(id);
		if (cache == null) {
			return;
		}
		switch (cache.size()) {
			case 0: {
				Pair<byte[], Boolean> data = this.readRaw(channel, 1);
				byte code = data.getFirstObject()[0];
				if (data.getSecondObject() == false) {
					key.cancel();
					return;
				}
				System.out.println(code);
				conditionIn: switch (code) {
					case CommunicationReference.CLIENT_CREATE_SESSION:
						byte[] isBytes;
						try {
							isBytes = LongConverter.getInstance().toBytes(id);
						} catch (ConversionException exception) {
							throw new ServerException("An error occured while reading the client id!", exception);
						}
						this.sendRaw(channel, CommunicationReference.SERVER_SUCCESS);
						this.sendRaw(channel, isBytes);
						try {
							this.listener.onClientConnected(channel.getRemoteAddress(), id);
						} catch (IOException exception) {
							throw new ServerException("An error occured while getting the client address!", exception);
						}
						break conditionIn;
					case CommunicationReference.CLIENT_END_SESSION:
						this.cache.remove(id);
						this.responses.remove(id);
						this.listener.onClientLoggedOut(id);
						break conditionIn;
					case CommunicationReference.CLIENT_REQUEST_UPDATE:
						try {
							byte[] countBytes = LongConverter.getInstance().toBytes((long) this.responses.get(id).size());
							this.sendRaw(channel, CommunicationReference.SERVER_SUCCESS);
							this.sendRaw(channel, countBytes);
							for (Pair<Long, AbstractResponse> responsePair : this.responses.get(id)) {
								byte[] idBytes = LongConverter.getInstance().toBytes((long) responsePair.getFirstObject());
								this.sendRaw(channel, idBytes);
								this.sendResponse(channel, responsePair.getSecondObject());
							}
							break conditionIn;
						} catch (ConversionException exception) {
							throw new ServerException("An error occured while converting the response data!", exception);
						}
					case CommunicationReference.CLIENT_CONTINUE_SESSION:
					case CommunicationReference.CLIENT_SEND_ASYNCHRONOUS:
					case CommunicationReference.CLIENT_SEND_SYNCHRONOUS:
						cache.add(new byte[] { code });
						break conditionIn;
					default:
						this.sendRaw(channel, CommunicationReference.SERVER_FAILURE);
						byte[] errorBytes = "The sent communication code is invalid!".getBytes();
						byte[] lengthBytes;
						try {
							lengthBytes = IntegerConverter.getInstance().toBytes(errorBytes.length);
						} catch (ConversionException exception) {
							throw new ServerException("An error occured while converting the response data!", exception);
						}
						this.sendRaw(channel, lengthBytes);
						this.sendRaw(channel, errorBytes);
						break conditionIn;
				}
				break;
			}
			case 1: {
				byte code = cache.get(0)[0];
				conditionIn: switch (code) {
					case CommunicationReference.CLIENT_CONTINUE_SESSION:
						Pair<byte[], Boolean> data = this.readRaw(channel, 8);
						long newId;
						try {
							newId = LongConverter.getInstance().toObject(data.getFirstObject());
						} catch (ConversionException exception) {
							throw new ServerException("An error occured while converting new client id!", exception);
						}
						key.attach(Long.valueOf(newId));
						this.sendRaw(channel, CommunicationReference.SERVER_SUCCESS);
					default:
						this.cache.get(key.attachment()).clear();
						break conditionIn;
					case CommunicationReference.CLIENT_SEND_SYNCHRONOUS:
					case CommunicationReference.CLIENT_SEND_ASYNCHRONOUS:
						Pair<byte[], Boolean> lengthBytes = this.readRaw(channel, 4);
						cache.add(lengthBytes.getFirstObject());
						break conditionIn;
				}
			}
			case 2: {
				byte code = cache.get(0)[0];
				if (code != CommunicationReference.CLIENT_SEND_SYNCHRONOUS && code != CommunicationReference.CLIENT_SEND_ASYNCHRONOUS) {
					this.cache.get(key.attachment()).clear();
					break;
				}
				byte[] lengthBytes = cache.get(1);
				int length;
				try {
					length = IntegerConverter.getInstance().toObject(lengthBytes);
				} catch (ConversionException exception) {
					throw new ServerException("An error occured while converting the request length!", exception);
				}
				Pair<byte[], Boolean> data = this.readRaw(channel, length);
				this.sendRaw(channel, CommunicationReference.SERVER_SUCCESS);
				RequestPacketHandler handler = this.handlers.getRequestPacketHandler();
				AbstractRequest request;
				try {
					request = handler.asSendable(data.getFirstObject());
				} catch (PacketException exception) {
					throw new ServerException("An error occured while converting the request data!", exception);
				}
				if (code == CommunicationReference.CLIENT_SEND_SYNCHRONOUS) {
					AbstractResponse response = this.listener.onSynchronousRequestRecieved(request);
					if (response == null) {
						throw new ServerException("The response provided by the listener cannot be NULL!");
					}
					this.sendResponse(channel, response);
				}
				if (code == CommunicationReference.CLIENT_SEND_ASYNCHRONOUS) {
					while (this.requests.containsKey(this.lastConversationId)) {
						this.lastConversationId++;
					}
					long conversationId = lastConversationId;
					byte[] conversationBytes;
					try {
						conversationBytes = LongConverter.getInstance().toBytes(conversationId);
					} catch (ConversionException exception) {
						throw new ServerException("An error occured while converting the conversation id!", exception);
					}
					this.sendRaw(channel, conversationBytes);
					this.requests.put(conversationId, id);
					this.listener.onAsynchronousRequestRecieved(request, conversationId);
				}
			}
			default:
				this.cache.get(key.attachment()).clear();
				break;
		}

	}

	/**
	 * This method sends the given response to the given client (channel)
	 * 
	 * @param channel  The channel representing the client to send the response to.
	 * @param response The request to send to the client.
	 * @throws ServerException If an error occurs while sending the response.
	 */
	protected void sendResponse(SocketChannel channel, AbstractResponse response) throws ServerException {
		ResponsePacketHandler handler = this.handlers.getResponsePacketHandler();
		try {
			byte[] responseBytes = handler.asBytes(response);
			byte[] lengthBytes = IntegerConverter.getInstance().toBytes(responseBytes.length);
			this.sendRaw(channel, lengthBytes);
			this.sendRaw(channel, responseBytes);
		} catch (ServerException exception) {
			throw new ServerException("An error occured while transmitting the response!", exception);
		} catch (PacketException exception) {
			throw new ServerException("An error occured while getting the response as a byte array!", exception);
		} catch (ConversionException exception) {
			throw new ServerException("An error occured while converting the byte array's length!", exception);
		}
	}

	/**
	 * This method returns the address of the server that is set.
	 * 
	 * @return The server's address.
	 */
	public InetSocketAddress getAddress() {
		return this.address;
	}

	/**
	 * This method returns whether the server is currently running.
	 * 
	 * @return The server's status
	 */
	public boolean isRunning() {
		synchronized (this.mutex) {
			return this.running;
		}
	}

	/**
	 * This method sets whether the server is currently running.
	 * 
	 * @param running The new status
	 */
	protected void setRunning(boolean running) {
		synchronized (this.mutex) {
			this.running = running;
		}
	}

	/**
	 * This method returns the {@link PacketHandlerDyad} used by the server.
	 * 
	 * @return The server's packet handlers.
	 */
	public PacketHandlerDyad getHandlers() {
		return this.handlers;
	}

	/**
	 * This method returns the server's listener
	 * 
	 * @return The server's listener
	 */
	public IServerListener getListener() {
		return this.listener;
	}

	/**
	 * This method set's the executor that starts the server loop.
	 * 
	 * @param executor The new executor
	 * @throws ServerException If an error occurs while changing the server's
	 *                         executor.
	 */
	public void setExecutor(Executor executor) throws ServerException {
		synchronized (this.mutex) {
			if (executor == null) {
				throw new ServerException("The given executor cannot be NULL!");
			}
			if (this.running) {
				throw new ServerException("The server is currently running!");
			}
			this.executor = executor;
		}
	}

	/**
	 * This method writes the given byte array to the given client.
	 * 
	 * @param channel The channel representing the client to write the bytes to
	 * @param data    The data to write to the server
	 * @throws ServerException If an error occurs while writing the bytes
	 */
	protected final void sendRaw(SocketChannel channel, byte... data) throws ServerException {
		synchronized (this.mutex) {
			if (data == null) {
				throw new ServerException("The given byte array cannot be NULL!");
			}
			ByteBuffer buffer = ByteBuffer.wrap(data);
			try {
				channel.write(buffer);
			} catch (IOException exception) {
				throw new ServerException("An error occured while writing the given bytes!", exception);
			}
		}
	}

	/**
	 * This method reads the next <i>length</i> amount of bytes from the given
	 * client.
	 * 
	 * @param channel The channel representing the client to read the bytes from
	 * @param length  The number of bytes to read from the client
	 * @return The bytes read from the client
	 * @throws ClientException If an error occurs while reading the bytes
	 */
	protected final Pair<byte[], Boolean> readRaw(SocketChannel channel, int length) throws ServerException {
		synchronized (this.mutex) {
			if (length < 0) {
				throw new ServerException("The given length cannot be smaller than 0!");
			}
			ByteBuffer buffer = ByteBuffer.allocate(length);
			boolean connected;
			try {
				connected = channel.read(buffer) != -1;
			} catch (IOException exception) {
				throw new ServerException("An error occured while reading the requested bytes!", exception);
			}
			return new Pair<>(buffer.array(), connected);
		}
	}

}