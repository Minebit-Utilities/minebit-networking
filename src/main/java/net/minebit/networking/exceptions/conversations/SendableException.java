package net.minebit.networking.exceptions.conversations;

import net.minebit.networking.conversations.AbstractSendable;
import net.minebit.networking.exceptions.LibraryException;

/**
 * {@link SendableException} is a {@link LibraryException} that is thrown
 * whenever an error occurs concerning an {@link AbstractSendable}.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class SendableException extends LibraryException {

	/**
	 * Constructs a new {@link SendableException} without an exception message and a
	 * cause.
	 */
	public SendableException() {
		super();
	}

	/**
	 * Constructs a new {@link SendableException} with a specified exception message
	 * but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public SendableException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link SendableException} without a message but with the
	 * specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public SendableException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link SendableException} with a specified exception message
	 * and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public SendableException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -1505254588962668500L;

}
