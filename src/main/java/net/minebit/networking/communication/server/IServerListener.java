package net.minebit.networking.communication.server;

import java.net.SocketAddress;

import net.minebit.networking.conversations.requests.AbstractRequest;
import net.minebit.networking.conversations.responses.AbstractResponse;

/**
 * This interface contains several methods that define what should happen when a
 * server event occurs.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public interface IServerListener {

	/**
	 * This method is invoked whenever a synchronous request is received from a
	 * client.
	 * 
	 * @param request The request send by the client
	 * @return The response to reply with.
	 */
	public AbstractResponse onSynchronousRequestRecieved(AbstractRequest request);

	/**
	 * This method is invoked whenever an asynchronous request is received from a
	 * client.
	 * 
	 * @param request The request send by the client
	 * @param id      The id of the request
	 */
	public void onAsynchronousRequestRecieved(AbstractRequest request, long id);

	/**
	 * This method is invoked whenever a loop exception is thrown.
	 * 
	 * @param exception The exception thrown
	 */
	public void onException(Exception exception);

	/**
	 * This method is invoked whenever a client connects to the server.
	 * 
	 * @param address The address of the client
	 * @param id      The id of the client
	 */
	public void onClientConnected(SocketAddress address, long id);

	/**
	 * This method is invoked whenever a client logs out of the server.
	 * 
	 * @param id The id of the client
	 */
	public void onClientLoggedOut(long id);

}
