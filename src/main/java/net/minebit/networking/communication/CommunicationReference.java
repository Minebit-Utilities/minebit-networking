package net.minebit.networking.communication;

/**
 * This class contains several utilities necessary for the client-server
 * communication to function.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public final class CommunicationReference {

	public static final byte CLIENT_CREATE_SESSION = 0x01;
	public static final byte CLIENT_CONTINUE_SESSION = 0x02;
	public static final byte CLIENT_SEND_REQUEST = 0x03;
	public static final byte CLIENT_REQUEST_UPDATE = 0x04;
	public static final byte CLIENT_SEND_REQUEST_AND_AWAIT = 0x05;
	public static final byte CLIENT_END_SESSION = 0x06;

	public static final byte SERVER_SUCCESS = 0x11;
	public static final byte SERVER_FAILURE = 0x12;

	private CommunicationReference() {
	}

}
