package net.minebit.networking.exceptions.communication;

import net.minebit.networking.exceptions.LibraryException;

/**
 * {@link CommunicationException} is a {@link LibraryException} that is thrown
 * whenever an error occurs concerning the communication between a client and a
 * server.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class CommunicationException extends LibraryException {

	/**
	 * Constructs a new {@link CommunicationException} without an exception message
	 * and a cause.
	 */
	public CommunicationException() {
		super();
	}

	/**
	 * Constructs a new {@link CommunicationException} with a specified exception
	 * message but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public CommunicationException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link CommunicationException} without a message but with
	 * the specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public CommunicationException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link CommunicationException} with a specified exception
	 * message and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public CommunicationException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = 6209646961638087000L;

}
