package net.minebit.networking.exceptions.wrappers;

import net.minebit.networking.exceptions.LibraryException;

/**
 * {@link WrapperException} is a {@link LibraryException} that is thrown whenever
 * an error occurs concerning a wrapper.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class WrapperException extends LibraryException {

	/**
	 * Constructs a new {@link WrapperException} without an exception message and a
	 * cause.
	 */
	public WrapperException() {
		super();
	}

	/**
	 * Constructs a new {@link WrapperException} with a specified exception message but
	 * without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public WrapperException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link WrapperException} without a message but with the
	 * specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public WrapperException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link WrapperException} with a specified exception message and
	 * cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public WrapperException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -3527572501599107000L;

}
