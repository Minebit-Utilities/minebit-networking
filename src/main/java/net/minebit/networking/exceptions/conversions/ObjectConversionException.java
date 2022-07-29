package net.minebit.networking.exceptions.conversions;

/**
 * {@link ObjectConversionException} is a {@link ConversionException} that is
 * thrown whenever an error occurs concerning the conversion of an object.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class ObjectConversionException extends ConversionException {

	/**
	 * Constructs a new {@link ObjectConversionException} without an exception
	 * message and a cause.
	 */
	public ObjectConversionException() {
		super();
	}

	/**
	 * Constructs a new {@link ObjectConversionException} with a specified
	 * exception message but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public ObjectConversionException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link ObjectConversionException} without a message but
	 * with the specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public ObjectConversionException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link ObjectConversionException} with a specified
	 * exception message and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public ObjectConversionException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -734664176270081348L;
	
}
