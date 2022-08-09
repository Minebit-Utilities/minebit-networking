package net.minebit.networking.exceptions.communication.responses;

import net.minebit.networking.exceptions.communication.SendableException;

/**
 * {@link ResponseException} is a {@link SendableException} that is thrown
 * whenever an error occurs concerning a response.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class ResponseException extends SendableException {

	/**
	 * Constructs a new {@link ResponseException} without an exception message and a
	 * cause.
	 */
	public ResponseException() {
		super();
	}

	/**
	 * Constructs a new {@link ResponseException} with a specified exception message
	 * but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public ResponseException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link ResponseException} without a message but with the
	 * specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public ResponseException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link ResponseException} with a specified exception message
	 * and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public ResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = 3047248348764596000L;

}
