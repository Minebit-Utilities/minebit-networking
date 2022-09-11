package net.minebit.networking.communication.client;

/**
 * This enum represents the current status of a client in client-server
 * communication.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public enum EClientStatus {

	/**
	 * The client is currently disabled and not connected to any server.
	 */
	DISABLED,

	/**
	 * The client is currently doing nothing but is enabled.
	 * 
	 */
	IDLE,

	/**
	 * The client is currently reading from the connected server.
	 */
	READING,

	/**
	 * The client is currently writing to the connected server.
	 */
	WRITING;

}
