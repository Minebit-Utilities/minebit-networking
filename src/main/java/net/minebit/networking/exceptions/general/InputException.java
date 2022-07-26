package net.minebit.networking.exceptions.general;

import net.minebit.networking.exceptions.LibraryException;

/**
 * {@link InputException} is a {@link LibraryException} that is thrown whenever
 * invalid input is given to a method.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class InputException extends LibraryException {

	/**
	 * Constructs a new {@link InputException} without an exception message and a
	 * cause.
	 */
	public InputException() {
		super();
	}

	/**
	 * Constructs a new {@link InputException} with a specified exception message
	 * but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public InputException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link InputException} without a message but with the
	 * specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public InputException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link InputException} with a specified exception message
	 * and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public InputException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -7552946254692968550L;

}
