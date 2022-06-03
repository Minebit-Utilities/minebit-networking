package net.minebit.networking.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import net.minebit.networking.common.Pair;
import net.minebit.networking.common.exceptions.InvalidInputException;

/**
 * This class represents a network client that used the <i>Minebit Networking
 * Communication Protocol</i>.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class Client {

	public static final long INTERVAL_DEFAULT = 1000L;
	public static final int BUFFER_SIZE_DEFAULT = 32768;

	private EClientStage stage = EClientStage.NEW;

	private final InetSocketAddress serverAddress;
	private final ByteBuffer buffer;
	private final ExecutorService executor;
	private final SocketChannel channel;
	private final IClientAdapter adapter;

	private final Object mutex = new Object();
	private final Runnable clientLoop = () -> this.loop();
	private final Map<String, Object> objectMap = new HashMap<String, Object>();
	private final Deque<Pair<String, Object>> sendQueue = new LinkedBlockingDeque<Pair<String, Object>>();

	/**
	 * This constructor constructs a new {@link Client} that connects to the given
	 * hostname and port using the <i>Minebit Networking Communication Protocol</i>.
	 * The buffer size, the executor and the adapter are the default values.
	 * 
	 * @param hostname The hostname of the wanted server.
	 * @param port     The port of the wanted server.
	 * @throws IOException If an I/0 error occurs.
	 */
	public Client(String hostname, int port) throws IOException {
		this(hostname, port, BUFFER_SIZE_DEFAULT, Executors.newFixedThreadPool(1), new DullClientAdapter());
	}

	/**
	 * This constructor constructs a new {@link Client} that connects to the given
	 * hostname and port using the <i>Minebit Networking Communication Protocol</i>.
	 * 
	 * @param hostname   The hostname of the wanted server.
	 * @param port       The port of the wanted server.
	 * @param bufferSize The size of the buffer used to transfer data for
	 *                   transmission and from reception.
	 * @param executor   The executor on which the client loop is going to be
	 *                   executed.
	 * @param adapter    The adapter that will be used to recieve the client's
	 *                   events.
	 * @throws IOException If an I/0 error occurs.
	 */
	public Client(String hostname, int port, int bufferSize, ExecutorService executor, IClientAdapter adapter) throws IOException {
		this.serverAddress = new InetSocketAddress(hostname, port);
		this.buffer = ByteBuffer.allocate(bufferSize);
		this.executor = executor;
		this.channel = SocketChannel.open();
		this.adapter = adapter != null ? new DullClientAdapter() : adapter;
	}

	/**
	 * This method starts the client by connecting to the server and starting the
	 * loop. The interval between retries is the default one.
	 * 
	 * @throws InvalidInputException If this client is already working or is dead.
	 * @throws IOException           If an I/0 error occurs.
	 * @throws InterruptedException  If the thread is interrupted during the
	 *                               connection retry interval.
	 */
	public void start() throws InvalidInputException, IOException, InterruptedException {
		this.start(INTERVAL_DEFAULT);
	}

	/**
	 * This method starts the client by connecting to the server and starting the
	 * loop.
	 * 
	 * @param interval The time interval between connection retries.
	 * @throws InvalidInputException If this client is already working or is dead.
	 * @throws IOException           If an I/0 error occurs.
	 * @throws InterruptedException  If the thread is interrupted during the
	 *                               connection retry interval.
	 */
	public void start(long interval) throws InvalidInputException, IOException, InterruptedException {
		synchronized (this.mutex) {
			if (this.stage != EClientStage.NEW) {
				throw new InvalidInputException("This client is already working or is dead!");
			}
			this.channel.configureBlocking(false);
			this.stage = EClientStage.CONNECTING;
			this.channel.connect(serverAddress);
			while (!this.channel.finishConnect()) {
				Thread.sleep(interval < 0 ? INTERVAL_DEFAULT : interval);
			}
			this.stage = EClientStage.ACTIVE;
			this.adapter.onClientConnected(this);
			this.executor.execute(clientLoop);
		}
	}

	/**
	 * This stop stops the client by closing the channel and ending the loop.
	 * 
	 * @throws InvalidInputException If the client is not currently running.
	 * @throws IOException           If an I/0 error occurs.
	 */
	public void stop() throws InvalidInputException, IOException {
		synchronized (this.mutex) {
			if (this.stage != EClientStage.ACTIVE) {
				throw new InvalidInputException("This client is not currently running!");
			}
			this.stage = EClientStage.DEAD;
			this.channel.close();
		}
	}

	/**
	 * This method runs the loop that manages the communication between the client
	 * and the server.
	 */
	private void loop() {
		while (true) {
			synchronized (this.mutex) {
				try {
					if (this.stage != EClientStage.ACTIVE) {
						break;
					}
					this.read();
					this.write();
				} catch (Exception exception) {
					this.stage = EClientStage.DEAD;
					this.adapter.onLoopException(this, exception);
					break;
				}
			}
		}
		this.adapter.onClientDied(this);
	}

	/**
	 * This method puts the given object in the queue for transmission to the
	 * connected server along with the given key.
	 * 
	 * @param key   The key of the given object.
	 * @param value The object to be transmitted.
	 * @throws InvalidInputException If the client is not active or the key or value
	 *                               is NULL.
	 */
	public void setObject(String key, Object value) throws InvalidInputException {
		if (this.stage != EClientStage.ACTIVE) {
			throw new InvalidInputException("This client is not currently active!");
		}
		if (key == null) {
			throw new InvalidInputException("The given key cannot be NULL!");
		}
		if (value == null) {
			throw new InvalidInputException("The given value cannot be NULL!");
		}
		Pair<String, Object> dataPair = new Pair<String, Object>(key, value);
		this.sendQueue.addLast(dataPair);
	}

	/**
	 * This method returns the object received from the server that is connected the
	 * given key.
	 * 
	 * @param key The key of the wanted object.
	 * @return The received object linked to the given key.
	 * @throws InvalidInputException If the given key is NULL!
	 */
	public Object getObject(String key) throws InvalidInputException {
		if (key == null) {
			throw new InvalidInputException("The given key cannot be NULL!");
		}
		return this.objectMap.get(key);
	}

	private void read() {

	}

	private void write() {

	}

	/**
	 * This method returns the current stage of the client.
	 * 
	 * @return The stage of the client.
	 */
	public EClientStage getStage() {
		return this.stage;
	}

	/**
	 * This method returns a read-only map that contains all the object received
	 * along with their keys.
	 * 
	 * @return A map of the received objects.
	 */
	public Map<String, Object> getObjectsMap() {
		return Collections.unmodifiableMap(this.objectMap);
	}

	/**
	 * This class represents an {@link IClientAdapter} that does absolutely nothing.
	 * 
	 * @author Aggelowe
	 * @since 0.1
	 *
	 */
	private static class DullClientAdapter implements IClientAdapter {

		/**
		 * This method does nothing.
		 */
		@Override
		public void onClientConnected(Client client) {
			return;
		}

		/**
		 * This method does nothing.
		 */
		@Override
		public void onClientDied(Client client) {
			return;
		}

		/**
		 * This method does nothing.
		 */
		@Override
		public void onLoopException(Client client, Exception exception) {
			return;
		}

	}

}
