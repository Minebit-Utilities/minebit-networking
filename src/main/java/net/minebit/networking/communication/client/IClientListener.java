package net.minebit.networking.communication.client;

/**
 * This interface contains several methods that define what should happen when a
 * client event occurs.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public interface IClientListener {

	/**
	 * This method is invoked when an exception is thrown inside the client loop.
	 * 
	 * @param exception The exception thrown
	 */
	public void onException(Exception exception);

	/**
	 * This method is invoked when the client's status changes.
	 * 
	 * @param previous The client's status before the change
	 * @param current  The client's status after the change
	 */
	public void onStatusChange(EClientStatus previous, EClientStatus current);

}
