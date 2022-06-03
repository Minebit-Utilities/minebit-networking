package net.minebit.networking.common.exceptions;

/**
 * {@link ConversionException} is a {@link MinebitNetworkingException} that is
 * thrown to indicate that there was an error concerning conversions.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class ConversionException extends MinebitNetworkingException {

	/**
	 * Constructs a new {@link InvalidClassException} with <i>null</i> as the
	 * exception message.
	 */
	public ConversionException() {
		super();
	}

	/**
	 * Constructs a new {@link InvalidClassException} with a specified exception
	 * message.
	 * 
	 * @param message The message of the exception.
	 */
	public ConversionException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link InvalidClassException} with a specified exception
	 * message and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public ConversionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new {@link InvalidClassException} with <i>null</i> as the
	 * exception message and a specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public ConversionException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = -8474697166522822938L;

}
