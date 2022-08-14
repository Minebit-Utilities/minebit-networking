package net.minebit.networking.exceptions.general;

import net.minebit.networking.exceptions.LibraryException;

/**
 * {@link ByteException} is a {@link LibraryException} that is thrown whenever
 * an error occurs concerning a byte operation.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class ByteException extends LibraryException {

	/**
	 * Constructs a new {@link ByteException} without an exception message and a
	 * cause.
	 */
	public ByteException() {
		super();
	}

	/**
	 * Constructs a new {@link ByteException} with a specified exception message but
	 * without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public ByteException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link ByteException} without a message but with the
	 * specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public ByteException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link ByteException} with a specified exception message and
	 * cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public ByteException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -7552946254692968550L;

}
