package net.minebit.networking.exceptions;

/**
 * {@link InvalidInputException} is a {@link MinebitNetworkingException} that is
 * thrown to indicate that there was an error with the input given or received.
 * 
 * @author Aggelowe
 *
 */
public class InvalidInputException extends MinebitNetworkingException {

	/**
	 * Constructs a new {@link InvalidInputException} with <i>null</i> as the
	 * exception message.
	 */
	public InvalidInputException() {
		super();
	}

	/**
	 * Constructs a new {@link InvalidInputException} with a specified exception
	 * message.
	 * 
	 * @param message The message of the exception.
	 */
	public InvalidInputException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link InvalidInputException} with a specified exception
	 * message and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public InvalidInputException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new {@link InvalidInputException} with <i>null</i> as the
	 * exception message and a specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public InvalidInputException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 5082715552529131247L;
}
