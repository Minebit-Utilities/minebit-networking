package net.minebit.networking.communication.client;

import static net.minebit.networking.communication.client.EClientStatus.*;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import net.minebit.networking.exceptions.communication.client.ClientException;

/**
 * This class represents a client taking part in a client server web
 * communication.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public abstract class AbstractClient {

	private EClientStatus status = EClientStatus.DISABLED;
	protected SocketChannel channel;
	protected final Object mutex = new Object();
	protected final IClientListener listener;

	public AbstractClient(IClientListener listener) throws ClientException {
		if (listener == null) {
			throw new ClientException("The given listener cannot be NULL!");
		}
		this.listener = listener;
	}

	/**
	 * This method starts the client and connects it to the server with the given
	 * address.
	 * 
	 * @param address The address of the server
	 * @throws ClientException If an error occurs while starting the client
	 */
	public abstract void start(InetSocketAddress address);

	/**
	 * This method stops the client.
	 * 
	 * @throws ClientException If an error occurs while stopping the client.
	 */
	public abstract void stop() throws ClientException;

	/**
	 * This method sends the given request to the connected server.
	 * 
	 * @throws ClientException If an error occurs while sending the request.
	 */
	public abstract void sendRequest() throws ClientException;

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
	 * @param status The status to set the client's status to
	 */
	protected final void setStatus(EClientStatus status) {
		synchronized (this.mutex) {
			final EClientStatus previousStatus = this.getStatus();
			this.status = status != null ? status : DISABLED;
			this.listener.onStatusChange(previousStatus, this.getStatus());
		}
	}

}