package net.minebit.networking.exceptions;

/**
 * <i>InvalidStateException</i> is a {@link MinebitNetworkingException}
 * that is thrown to indicate that something tried to start while already
 * running.
 * 
 * @author Aggelowe
 *
 */
public class InvalidStateException extends MinebitNetworkingException {

	/**
	 * Constructs a new {@link InvalidStateException} with <i>null</i> as the
	 * exception message.
	 */
	public InvalidStateException() {
		super();
	}

	/**
	 * Constructs a new {@link InvalidStateException} with a specified
	 * exception message.
	 * 
	 * @param message The message of the exception.
	 */
	public InvalidStateException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link InvalidStateException} with a specified exception
	 * message and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public InvalidStateException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new {@link InvalidStateException} with <i>null</i> as the
	 * exception message and a specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public InvalidStateException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = -7995357696816793084L;

}
