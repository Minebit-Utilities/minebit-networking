package net.minebit.networking.client;

/**
 * This interface contains a set of methods that are invoked when their
 * respective event occurs.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public interface IClientAdapter {

	/**
	 * This method is invoked when the client successfully connects to the server.
	 * 
	 * @param client The client on which the event occurred.
	 */
	void onClientConnected(Client client);

	/**
	 * This method is invoked when the client disconnects from the server and can no
	 * longer be used.
	 * 
	 * @param client The client on which the event occurred.
	 */
	void onClientDied(Client client);

	/**
	 * This method is invoked when the client's loop throws an exception.
	 * 
	 * @param client    The client on which the event occurred.
	 * @param exception The exception that was thrown from the client's loop.
	 */
	void onLoopException(Client client, Exception exception);

}
