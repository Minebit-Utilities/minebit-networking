package net.minebit.networking.exceptions.coders;

/**
 * {@link RequestCoderException} is a {@link CoderException} that is thrown whenever
 * an error occurs concerning the encoding/decoding of a request.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class RequestCoderException extends CoderException {

	/**
	 * Constructs a new {@link RequestCoderException} without an exception message and a
	 * cause.
	 */
	public RequestCoderException() {
		super();
	}

	/**
	 * Constructs a new {@link RequestCoderException} with a specified exception message
	 * but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public RequestCoderException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link RequestCoderException} without a message but with the
	 * specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public RequestCoderException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link RequestCoderException} with a specified exception message
	 * and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public RequestCoderException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -7552946254692968550L;

}
