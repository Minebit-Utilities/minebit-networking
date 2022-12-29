package net.minebit.networking.exceptions.communication;

/**
 * {@link ClientException} is a {@link CommunicationException} that is thrown whenever
 * an error occurs concerning a client.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class ClientException extends CommunicationException {

	/**
	 * Constructs a new {@link ClientException} without an exception message and a
	 * cause.
	 */
	public ClientException() {
		super();
	}

	/**
	 * Constructs a new {@link ClientException} with a specified exception message
	 * but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public ClientException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link ClientException} without a message but with the
	 * specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public ClientException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link ClientException} with a specified exception message
	 * and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public ClientException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -1030930849914216400L;

}
