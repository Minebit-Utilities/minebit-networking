package net.minebit.networking.communication;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import net.minebit.networking.exceptions.communication.CommunicationException;

/**
 * This class contains several utilities necessary for the client-server
 * communication to function.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public final class CommunicationUtils {

	public static final byte CLIENT_CONNECT = 0x01;
	public static final	byte CLIENT_REQUEST = 0x02;
	public static final byte CLIENT_UPDATE = 0x03;

	public static final byte SERVER_LOGIN = 0x11;
	public static final byte SERVER_ERROR = 0x12;
	public static final byte SERVER_DATA = 0x13;
	
	private CommunicationUtils() {
	}

	/**
	 * This method reads a byte array of the given length from the given channel and
	 * returns it.
	 * 
	 * @param channel The channel to read from
	 * @param length  The size of the array to read
	 * @return The byte read from the given channel
	 * @throws CommunicationException If an error occurs while reading the bytes.
	 */
	public static byte[] read(SocketChannel channel, int length) throws CommunicationException {
		if (channel == null) {
			throw new CommunicationException("The given channel cannot be NULL");
		}
		if (length < 0) {
			throw new CommunicationException("The given length cannot be smaller than 0!");
		}
		ByteBuffer buffer = ByteBuffer.allocate(length);
		try {
			channel.read(buffer);
		} catch (IOException exception) {
			throw new CommunicationException("An error occured while reading the requested bytes!", exception);
		}
		return buffer.array();
	}

	/**
	 * This method writes the given byte array to the given channel.
	 * 
	 * @param channel The channel to write the given bytes to
	 * @param bytes   The bytes to write to the given channel
	 * @throws CommunicationException If an error occurs while writing the bytes.
	 */
	public static void write(SocketChannel channel, byte[] bytes) throws CommunicationException {
		if (channel == null) {
			throw new CommunicationException("The given channel cannot be NULL");
		}
		if (bytes == null) {
			throw new CommunicationException("The given byte array cannot be NULL!");
		}
		ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
		buffer.put(bytes);
		buffer.position(0);
		try {
			channel.write(buffer);
		} catch (IOException exception) {
			throw new CommunicationException("An error occured while writing the given bytes!", exception);
		}
	}

}
