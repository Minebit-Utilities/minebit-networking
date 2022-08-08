package net.minebit.networking.exceptions.communication.requests;

import net.minebit.networking.exceptions.LibraryException;

/**
 * {@link RequestException} is a {@link LibraryException} that is thrown whenever
 * an error occurs concerning the encoding/decoding of a request/response.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class RequestException extends LibraryException {

	/**
	 * Constructs a new {@link RequestException} without an exception message and a
	 * cause.
	 */
	public RequestException() {
		super();
	}

	/**
	 * Constructs a new {@link RequestException} with a specified exception message
	 * but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public RequestException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link RequestException} without a message but with the
	 * specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public RequestException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link RequestException} with a specified exception message
	 * and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public RequestException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -7552946254692968550L;

}
