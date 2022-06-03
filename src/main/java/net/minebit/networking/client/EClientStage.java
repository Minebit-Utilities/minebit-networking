package net.minebit.networking.client;

/**
 * {@link EClientStage} is an enum that defines the current stage of a
 * {@link Client}.
 * 
 * @author Aggelowe
 * @see Client
 * @since 0.1
 */
public enum EClientStage {

	/**
	 * The {@link Client} has just been created and doesn't contain any data.
	 */
	NEW,

	/**
	 * The {@link Client} is attempting to connect to the server.
	 */
	CONNECTING,

	/**
	 * The {@link Client} is currently connected to the server.
	 */
	ACTIVE,

	/**
	 * The connection between the {@link Client} and the server has ended.
	 */
	DEAD;

}
