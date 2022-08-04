package net.minebit.networking.exceptions.coders;

/**
 * {@link ResponseCoderException} is a {@link CoderException} that is thrown whenever
 * an error occurs concerning the encoding/decoding of a response.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class ResponseCoderException extends CoderException {

	/**
	 * Constructs a new {@link ResponseCoderException} without an exception message and a
	 * cause.
	 */
	public ResponseCoderException() {
		super();
	}

	/**
	 * Constructs a new {@link ResponseCoderException} with a specified exception message
	 * but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public ResponseCoderException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link ResponseCoderException} without a message but with the
	 * specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public ResponseCoderException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link ResponseCoderException} with a specified exception message
	 * and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public ResponseCoderException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -7552946254692968550L;

}
