package net.minebit.networking.common.exceptions;

/**
 * {@link FailedConnectionException} is a {@link MinebitNetworkingException}
 * that is thrown to indicate that an error occurred when a connection between a
 * client and a server has failed.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class FailedConnectionException extends MinebitNetworkingException {

	/**
	 * Constructs a new {@link InvalidClassException} with <i>null</i> as the
	 * exception message.
	 */
	public FailedConnectionException() {
		super();
	}

	/**
	 * Constructs a new {@link InvalidClassException} with a specified exception
	 * message.
	 * 
	 * @param message The message of the exception.
	 */
	public FailedConnectionException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link InvalidClassException} with a specified exception
	 * message and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public FailedConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new {@link InvalidClassException} with <i>null</i> as the
	 * exception message and a specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public FailedConnectionException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = -8474697166522822938L;

}
