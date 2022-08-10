package net.minebit.networking.exceptions.communication;

/**
 * {@link SendableRegistryException} is a {@link SendableException} that is
 * thrown whenever an error occurs concerning a sendable registry.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class SendableRegistryException extends SendableException {

	/**
	 * Constructs a new {@link SendableRegistryException} without an exception
	 * message and a cause.
	 */
	public SendableRegistryException() {
		super();
	}

	/**
	 * Constructs a new {@link SendableRegistryException} with a specified exception
	 * message but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public SendableRegistryException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link SendableRegistryException} without a message but with
	 * the specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public SendableRegistryException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link SendableRegistryException} with a specified exception
	 * message and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public SendableRegistryException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -7399345645207003000L;

}
