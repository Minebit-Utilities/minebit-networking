package net.minebit.networking.exceptions.communication;

/**
 * {@link ServerException} is a {@link CommunicationException} that is thrown whenever
 * an error occurs concerning a server.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class ServerException extends CommunicationException {

	/**
	 * Constructs a new {@link ServerException} without an exception message and a
	 * cause.
	 */
	public ServerException() {
		super();
	}

	/**
	 * Constructs a new {@link ServerException} with a specified exception message
	 * but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public ServerException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link ServerException} without a message but with the
	 * specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public ServerException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link ServerException} with a specified exception message
	 * and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public ServerException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -1030930849914216400L;

}
