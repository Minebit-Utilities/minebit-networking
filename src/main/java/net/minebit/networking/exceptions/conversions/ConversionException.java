package net.minebit.networking.exceptions.conversions;

import net.minebit.networking.exceptions.LibraryException;

/**
 * {@link ConversionException} is a {@link LibraryException} that is thrown
 * whenever an error occurs concerning the conversion of an object or a
 * primitive.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class ConversionException extends LibraryException {

	/**
	 * Constructs a new {@link ConversionException} without an exception message and
	 * a cause.
	 */
	public ConversionException() {
		super();
	}

	/**
	 * Constructs a new {@link ConversionException} with a specified exception
	 * message but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public ConversionException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link ConversionException} without a message but with the
	 * specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public ConversionException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link ConversionException} with a specified exception
	 * message and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public ConversionException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -7552946254692968550L;

}
