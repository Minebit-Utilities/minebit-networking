package net.minebit.networking.exceptions.communication;

import net.minebit.networking.exceptions.LibraryException;

/**
 * {@link PacketException} is a {@link LibraryException} that is thrown whenever
 * an error occurs concerning a packet.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class PacketException extends LibraryException {

	/**
	 * Constructs a new {@link PacketException} without an exception message and a
	 * cause.
	 */
	public PacketException() {
		super();
	}

	/**
	 * Constructs a new {@link PacketException} with a specified exception message
	 * but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public PacketException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link PacketException} without a message but with the
	 * specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public PacketException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link PacketException} with a specified exception message
	 * and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public PacketException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = 3908161392480258000L;

}
