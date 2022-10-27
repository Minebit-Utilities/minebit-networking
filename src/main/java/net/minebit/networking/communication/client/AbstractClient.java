package net.minebit.networking.communication.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import net.minebit.networking.conversations.requests.AbstractRequest;
import net.minebit.networking.exceptions.communication.client.ClientException;

/**
 * This class represents a client taking part in a client-server web
 * communication
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public abstract class AbstractClient {

	private EClientStatus status = EClientStatus.DISABLED;
	private SocketChannel channel;

	private final Object mutex = new Object();
	private final boolean toggling;

	/**
	 * This constructor constructs a new client that takes part in a web
	 * communication.
	 * 
	 * @param toggling Whether to disconnect from the server after each transmission
	 *                 or reception of data.
	 */
	public AbstractClient(boolean toggling) {
		this.toggling = toggling;
	}

	/**
	 * This method starts the client and connects it to the server with the given
	 * address.
	 * 
	 * @throws ClientException If an error occurs while starting the client
	 */
	public abstract void start(InetSocketAddress address) throws ClientException;

	/**
	 * This method stops the client.
	 * 
	 * @throws ClientException If an error occurs while stopping the client.
	 */
	public abstract void stop() throws ClientException;

	/**
	 * This method sends the given request to the connected server.
	 * 
	 * @param request The request to send to the connected server.
	 * @throws ClientException If an error occurs while sending the request.
	 */
	public abstract void sendRequest(AbstractRequest request) throws ClientException;
	
	/**
	 * This method closes the current connection with the server but keeps the
	 * current session open to be used later.
	 * 
	 * @throws ClientException If an error occurs while putting the client in
	 *                         standby mode.
	 */
	public abstract void standby() throws ClientException;

	/**
	 * This method connects the client to the server without starting a new session.
	 * 
	 * @throws ClientException If an error occurs while the client exits standby
	 *                         mode.
	 */
	public abstract void wake() throws ClientException;

	/**
	 * This method returns the current status of the client.
	 * 
	 * @return The client's status
	 */
	public EClientStatus getStatus() {
		synchronized (this.mutex) {
			return this.status;
		}
	}
	
	/**
	 * This method sets the current status of the client.
	 * 
	 * @param status The changed status
	 * @throws ClientException If an error occurs while changing the status
	 */
	protected void setStatus(EClientStatus status) throws ClientException {
		if (status == null) {
			throw new ClientException("The given status cannot be NULL!");
		}
		this.status = status;
	}

	/**
	 * This method returns the lock used to ensure client thread safety.
	 * 
	 * @return The client's mutex
	 */
	protected Object getMutex() {
		return this.mutex;
	}

	/**
	 * This method returns whether the client's communication is set in toggling
	 * mode or not.
	 * 
	 * @return Whether the client toggles
	 */
	protected boolean isToggling() {
		return this.toggling;
	}

	/**
	 * This method initializes the socket channel and connects it to the server with
	 * the given address.
	 * 
	 * @param address The server's address
	 * @throws ClientException If an error occurs while the client is connecting.
	 */
	protected void connect(InetSocketAddress address) throws ClientException {
		try {
			this.channel = SocketChannel.open();
			this.channel.configureBlocking(true);
			this.channel.connect(address);
			while (!this.channel.finishConnect()) {
			}
		} catch (IOException exception) {
			throw new ClientException("An error occured while connecting the client to the server!", exception);
		}
	}

	/**
	 * This method ends the connection with the server.
	 * 
	 * @throws ClientException If an error occurs while the client is disconnecting.
	 */
	protected void disconnect() throws ClientException {
		try {
			this.channel.close();
		} catch (IOException exception) {
			throw new ClientException("An error occured while closing the channel!", exception);
		}
		this.channel = null;
	}

	/**
	 * This method writes the given byte array to the server.
	 * 
	 * @param data The data to write to the server
	 * @throws ClientException If an error occurs while writing the bytes
	 */
	protected void sendRaw(byte[] data) throws ClientException {
		if (data == null) {
			throw new ClientException("The given byte array cannot be NULL!");
		}
		ByteBuffer buffer = ByteBuffer.wrap(data);
		try {
			this.channel.write(buffer);
		} catch (IOException exception) {
			throw new ClientException("An error occured while writing the given bytes!", exception);
		}
	}

	/**
	 * This method reads the next <i>length</i> amount of bytes from the server.
	 * 
	 * @param length The number of bytes to read from the server
	 * @return The bytes read from the server
	 * @throws ClientException If an error occurs while reading the bytes
	 */
	protected byte[] readRaw(int length) throws ClientException {
		if (length < 0) {
			throw new ClientException("The given length cannot be smaller than 0!");
		}
		ByteBuffer buffer = ByteBuffer.allocate(length);
		try {
			this.channel.read(buffer);
		} catch (IOException exception) {
			throw new ClientException("An error occured while reading the requested bytes!", exception);
		}
		return buffer.array();
	}

}
