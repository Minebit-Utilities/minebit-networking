package net.minebit.networking.exceptions.conversions;

/**
 * {@link PrimitiveConversionException} is a {@link ConversionException} that is
 * thrown whenever an error occurs concerning the conversion of a primitive.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class PrimitiveConversionException extends ConversionException {

	/**
	 * Constructs a new {@link PrimitiveConversionException} without an exception
	 * message and a cause.
	 */
	public PrimitiveConversionException() {
		super();
	}

	/**
	 * Constructs a new {@link PrimitiveConversionException} with a specified
	 * exception message but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public PrimitiveConversionException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link PrimitiveConversionException} without a message but
	 * with the specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public PrimitiveConversionException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link PrimitiveConversionException} with a specified
	 * exception message and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public PrimitiveConversionException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -7552946254692968550L;

}
