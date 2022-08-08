package net.minebit.networking.exceptions.coders;

/**
 * {@link EncodingCoderException} is a {@link CoderException} that is
 * thrown whenever an error occurs concerning the encoding of an object.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class EncodingCoderException extends CoderException {

	/**
	 * Constructs a new {@link EncodingCoderException} without an exception
	 * message and a cause.
	 */
	public EncodingCoderException() {
		super();
	}

	/**
	 * Constructs a new {@link EncodingCoderException} with a specified
	 * exception message but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public EncodingCoderException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link EncodingCoderException} without a message but
	 * with the specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public EncodingCoderException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link EncodingCoderException} with a specified
	 * exception message and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public EncodingCoderException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -7262328484071614000L;
	
}
