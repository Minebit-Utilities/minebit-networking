package net.minebit.networking.communication.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import net.minebit.networking.communication.CommunicationReference;
import net.minebit.networking.communication.PacketHandlerDyad;
import net.minebit.networking.conversations.requests.AbstractRequest;
import net.minebit.networking.conversions.primitives.IntegerConverter;
import net.minebit.networking.conversions.primitives.LongConverter;
import net.minebit.networking.exceptions.ConversionException;
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

	private final Object mutex = new Object();
	private final InetSocketAddress address;
	private final boolean toggling;
	private final PacketHandlerDyad handlers;

	private EClientStatus status = EClientStatus.DISABLED;
	private SocketChannel channel;
	private IClientListener listener;
	private long clientID;

	/**
	 * This constructor constructs a new client that takes part in a web
	 * communication.
	 * 
	 * @param address The address of the server to connect the client to.
	 * @throws ClientException If an error occurs while initializing the client.
	 */
	public AbstractClient(InetSocketAddress address) throws ClientException {
		this(address, true, new PacketHandlerDyad());
	}

	/**
	 * This constructor constructs a new client that takes part in a web
	 * communication.
	 * 
	 * @param address  The address of the server to connect the client to.
	 * @param toggling Whether to disconnect from the server after each transmission
	 *                 or reception of data.
	 * @throws ClientException If an error occurs while initializing the client.
	 */
	public AbstractClient(InetSocketAddress address, boolean toggling, PacketHandlerDyad handlers) throws ClientException {
		if (address == null) {
			throw new ClientException("The given address cannot be NULL!");
		}
		if (handlers == null) {
			throw new ClientException("The given handler dyad cannot be NULL!");
		}
		this.address = address;
		this.toggling = toggling;
		this.handlers = handlers;
	}

	/**
	 * This method starts the client and connects it to the server with the given
	 * address.
	 * 
	 * @throws ClientException If an error occurs while starting the client
	 */
	public void start() throws ClientException {
		synchronized (this.mutex) {
			if (this.status != EClientStatus.DISABLED) {
				throw new ClientException("There is already an open session!");
			}
			this.setStatus(EClientStatus.ENABLED);
			try {
				this.connect();
				this.login();
			} catch (ClientException exception) {
				throw new ClientException("An error occured while connecting and logging in!", exception);
			}
			if (this.toggling) {
				try {
					this.standby();
				} catch (ClientException exception) {
					throw new ClientException("An error occured while standing by!", exception);
				}
			}
		}
	}

	/**
	 * This method stops the client.
	 * 
	 * @throws ClientException If an error occurs while stopping the client.
	 */
	public void stop() throws ClientException {
		synchronized (this.mutex) {
			if (this.status == EClientStatus.DISABLED) {
				throw new ClientException("The client is already stopped!");
			}
			if (this.getStatus() == EClientStatus.STANDBY) {
				try {
					this.wake();
				} catch (ClientException exception) {
					throw new ClientException("An error occured while waking the client!", exception);
				}
			}
			try {
				this.logout();
				this.disconnect();
			} catch (ClientException exception) {
				throw new ClientException("An error occured while logging out and disconnecting!", exception);
			}
		}
	}

	/**
	 * This method closes the current connection with the server but keeps the
	 * current session open to be used later.
	 * 
	 * @throws ClientException If an error occurs while putting the client in
	 *                         standby mode.
	 */
	public void standby() throws ClientException {
		synchronized (this.mutex) {
			if (this.status != EClientStatus.ENABLED) {
				throw new ClientException("The client is not enabled!");
			}
			this.disconnect();
			this.status = EClientStatus.STANDBY;
		}
	}

	/**
	 * This method connects the client to the server without starting a new session.
	 * 
	 * @throws ClientException If an error occurs while the client exits standby
	 *                         mode.
	 */
	public void wake() throws ClientException {
		synchronized (this.mutex) {
			if (this.status != EClientStatus.STANDBY) {
				throw new ClientException("The client is not standing-by!");
			}
			this.connect();
			this.relogin();
			this.status = EClientStatus.ENABLED;
		}
	}

	/**
	 * This method sends the given request to the connected server.
	 * 
	 * @param request The request to send to the connected server.
	 * @throws ClientException If an error occurs while sending the request.
	 */
	public abstract void sendRequest(AbstractRequest request) throws ClientException;

	/**
	 * This method logins to the given server for the first time.
	 * 
	 * @throws ClientException If an error occurs while logging in the server.
	 */
	protected void login() throws ClientException {
		synchronized (this.mutex) {
			try {
				this.sendRaw(CommunicationReference.CLIENT_CREATE_SESSION);
			} catch (ClientException exception) {
				throw new ClientException("An error occured while sending the login code!", exception);
			}
			boolean success;
			try {
				success = this.checkMarker();
			} catch (ClientException exception) {
				throw new ClientException("An error occured while checking the login success!", exception);
			}
			if (success) {
				try {
					byte[] idBytes = this.readRaw(8);
					clientID = LongConverter.getInstance().toObject(idBytes);
				} catch (ClientException exception) {
					throw new ClientException("An error occured while reading the client id!", exception);
				} catch (ConversionException exception) {
					throw new ClientException("An error occured while converting the client id bytes!", exception);
				}
			} else {
				try {
					this.handleError();
				} catch (ClientException exception) {
					throw new ClientException("An error occured while handling the server error!", exception);
				}
			}
		}
	}

	/**
	 * This method logins to the given server and continuing the previously open
	 * session.
	 * 
	 * @throws ClientException If an error occurs while logging in the server.
	 */
	protected void relogin() throws ClientException {
		synchronized (this.mutex) {
			try {
				this.sendRaw(CommunicationReference.CLIENT_CONTINUE_SESSION);
				byte[] idBytes = LongConverter.getInstance().toBytes(this.clientID);
				this.sendRaw(idBytes);
			} catch (ClientException exception) {
				throw new ClientException("An error occured while sending the re-login data!", exception);
			} catch (ConversionException exception) {
				throw new ClientException("An error occured while converting the client id!", exception);
			}
			boolean success;
			try {
				success = this.checkMarker();
			} catch (ClientException exception) {
				throw new ClientException("An error occured while checking the re-login success!", exception);
			}
			if (!success) {
				try {
					this.handleError();
				} catch (ClientException exception) {
					throw new ClientException("An error occured while handling the server error!", exception);
				}
			}
		}
	}

	/**
	 * This method ends the current session with the server.
	 * 
	 * @throws ClientException If an error occurs while logging out of the server.
	 */
	protected void logout() throws ClientException {
		synchronized (this.mutex) {
			try {
				this.sendRaw(CommunicationReference.CLIENT_END_SESSION);
			} catch (ClientException exception) {
				throw new ClientException("An error occured while sending the logout code!", exception);
			}
		}
	}

	/**
	 * This method reads the next byte from the server and returns whether it marks
	 * success or failure.
	 * 
	 * @throws ClientException If an error occurs while checking the marker.
	 *
	 */
	protected boolean checkMarker() throws ClientException {
		synchronized (this.mutex) {
			byte[] next;
			try {
				next = this.readRaw(1);
			} catch (ClientException exception) {
				throw new ClientException("An error occured while reading the marker byte!", exception);
			}
			byte marker = next[0];
			switch (marker) {
				case CommunicationReference.SERVER_SUCCESS:
					return true;
				case CommunicationReference.SERVER_FAILURE:
					return false;
				default:
					throw new ClientException("An invalid code has been received from the server while checking!");
			}
		}
	}

	/**
	 * This method reads the error sent by the server and invokes the respective
	 * method on the listener.
	 * 
	 * @throws ClientException If an error occurs while handling the error.
	 */
	protected void handleError() throws ClientException {
		synchronized (this.mutex) {
			byte[] descriptionBytes;
			try {
				byte[] lengthBytes = this.readRaw(4);
				int length = IntegerConverter.getInstance().toObject(lengthBytes);
				descriptionBytes = this.readRaw(length);
			} catch (ClientException exception) {
				throw new ClientException("An error occured while reading the error bytes!", exception);
			} catch (ConversionException exception) {
				throw new ClientException("An error occured while converting the error messages length!", exception);
			}
			String description = new String(descriptionBytes);
			if (this.listener != null) {
				this.listener.onServerErrorOccured(description);
			}
		}
	}

	/**
	 * This method returns the current listener
	 * 
	 * @return The current listener
	 */
	public final IClientListener getListener() {
		synchronized (this.mutex) {
			return this.listener;
		}
	}

	/**
	 * This method sets the current listener to the given one.
	 * 
	 * @param listener The new client's listener
	 */
	protected final void setListener(IClientListener listener) {
		synchronized (this.mutex) {
			this.listener = listener;
		}
	}

	/**
	 * This method returns the address of the server that is set.
	 * 
	 * @return The remote server's address.
	 */
	public final InetSocketAddress getAddress() {
		return this.address;
	}

	/**
	 * This method returns whether the client's communication is set in toggling
	 * mode or not.
	 * 
	 * @return Whether the client is in toggling mode
	 */
	public final boolean isToggling() {
		return this.toggling;
	}

	/**
	 * This method returns the {@link PacketHandlerDyad} used by the client.
	 * 
	 * @return The client's packet handlers.
	 */
	public final PacketHandlerDyad getHandlers() {
		return this.handlers;
	}

	/**
	 * This method returns the current status of the client.
	 * 
	 * @return The client's status
	 */
	public final EClientStatus getStatus() {
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
	protected final void setStatus(EClientStatus status) throws ClientException {
		synchronized (this.mutex) {
			if (status == null) {
				throw new ClientException("The given status cannot be NULL!");
			}
			if (this.listener != null) {
				this.listener.onStatusChanged(this.getStatus(), status);
			}
			this.status = status;
		}
	}

	/**
	 * This method returns the current id of the client.
	 * 
	 * @return The client's id
	 */
	public final long getClientID() {
		synchronized (this.mutex) {
			return this.clientID;
		}
	}

	/**
	 * This methods sets the current client id to the given one.
	 * 
	 * @param clientID The new id of the client
	 */
	protected final void setClientID(long clientID) {
		synchronized (this.mutex) {
			this.clientID = clientID;
		}
	}

	/**
	 * This method returns the lock used to ensure client thread safety.
	 * 
	 * @return The client's mutex
	 */
	protected final Object getMutex() {
		return this.mutex;
	}

	/**
	 * This method initializes the socket channel and connects it to the server with
	 * the given address.
	 * 
	 * @param address The server's address
	 * @throws ClientException If an error occurs while the client is connecting.
	 */
	protected final void connect() throws ClientException {
		synchronized (this.mutex) {
			try {
				this.channel = SocketChannel.open();
				this.channel.configureBlocking(true);
				this.channel.connect(this.address);
				while (!this.channel.finishConnect()) {
				}
			} catch (IOException exception) {
				throw new ClientException("An error occured while connecting the client to the server!", exception);
			}
		}
	}

	/**
	 * This method ends the connection with the server.
	 * 
	 * @throws ClientException If an error occurs while the client is disconnecting.
	 */
	protected final void disconnect() throws ClientException {
		synchronized (this.mutex) {
			try {
				this.channel.close();
			} catch (IOException exception) {
				throw new ClientException("An error occured while closing the channel!", exception);
			}
			this.channel = null;
		}
	}

	/**
	 * This method writes the given byte array to the server.
	 * 
	 * @param data The data to write to the server
	 * @throws ClientException If an error occurs while writing the bytes
	 */
	protected final void sendRaw(byte... data) throws ClientException {
		synchronized (this.mutex) {
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
	}

	/**
	 * This method reads the next <i>length</i> amount of bytes from the server.
	 * 
	 * @param length The number of bytes to read from the server
	 * @return The bytes read from the server
	 * @throws ClientException If an error occurs while reading the bytes
	 */
	protected final byte[] readRaw(int length) throws ClientException {
		synchronized (this.mutex) {
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

}