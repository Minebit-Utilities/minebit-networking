package net.minebit.networking.exceptions.coders;

/**
 * {@link DecodingCoderException} is a {@link CoderException} that is
 * thrown whenever an error occurs concerning the decoding of an object.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class DecodingCoderException extends CoderException {

	/**
	 * Constructs a new {@link DecodingCoderException} without an exception
	 * message and a cause.
	 */
	public DecodingCoderException() {
		super();
	}

	/**
	 * Constructs a new {@link DecodingCoderException} with a specified
	 * exception message but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public DecodingCoderException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link DecodingCoderException} without a message but
	 * with the specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public DecodingCoderException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link DecodingCoderException} with a specified
	 * exception message and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public DecodingCoderException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -8280995542800589000L;
	
}
