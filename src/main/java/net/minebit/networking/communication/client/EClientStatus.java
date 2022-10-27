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
	 * There is no active connection but there is an active session.
	 */
	STANDBY,
	
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
