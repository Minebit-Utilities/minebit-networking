package net.minebit.networking.exceptions.coders;

import net.minebit.networking.exceptions.LibraryException;

/**
 * {@link CoderException} is a {@link LibraryException} that is thrown whenever
 * an error occurs concerning the encoding/decoding of an object.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class CoderException extends LibraryException {

	/**
	 * Constructs a new {@link CoderException} without an exception message and a
	 * cause.
	 */
	public CoderException() {
		super();
	}

	/**
	 * Constructs a new {@link CoderException} with a specified exception message
	 * but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public CoderException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link CoderException} without a message but with the
	 * specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public CoderException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link CoderException} with a specified exception message
	 * and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public CoderException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -6255405719277448864L;

}
