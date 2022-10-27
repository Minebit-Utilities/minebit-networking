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

	public static final byte CLIENT_INITIATE_LOGIN = 0x01;
	public static final	byte CLIENT_SEND_REQUEST = 0x02;
	public static final byte CLIENT_REQUEST_UPDATE = 0x03;
	public static final	byte CLIENT_SEND_REQUEST_AND_AWAIT = 0x04;
	public static final byte CLIENT_END_SESSION = 0x05;
	
	public static final byte SERVER_COMPLETE_LOGIN = 0x11;
	public static final byte SERVER_SEND_ERROR = 0x12;
	public static final byte SERVER_SEND_DATA = 0x13;
	public static final byte SERVER_CONFIRM_REQUEST = 0x14;
	public static final byte SERVER_DISCONNECT = 0x15;
	
	private CommunicationReference() {
	}

}
