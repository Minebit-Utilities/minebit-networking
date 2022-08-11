package net.minebit.networking.exceptions.communication;

/**
 * {@link SendableFactoryException} is a {@link SendableException} that is
 * thrown whenever an error occurs concerning a sendable factory.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class SendableFactoryException extends SendableException {

	/**
	 * Constructs a new {@link SendableFactoryException} without an exception
	 * message and a cause.
	 */
	public SendableFactoryException() {
		super();
	}

	/**
	 * Constructs a new {@link SendableFactoryException} with a specified exception
	 * message but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public SendableFactoryException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link SendableFactoryException} without a message but with
	 * the specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public SendableFactoryException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link SendableFactoryException} with a specified exception
	 * message and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public SendableFactoryException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -3426536330465782000L;

}
