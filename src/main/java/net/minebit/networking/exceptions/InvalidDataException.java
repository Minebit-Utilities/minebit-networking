package net.minebit.networking.exceptions;

/**
 * <i>InvalidDataException</i> is a {@link MinebitNetworkingException} that is
 * thrown to indicate that of data received is not readable by the decoder.
 * 
 * @author Aggelowe
 *
 */
public class InvalidDataException extends MinebitNetworkingException {

	/**
	 * Constructs a new {@link InvalidDataException} with <i>null</i> as the
	 * exception message.
	 */
	public InvalidDataException() {
		super();
	}

	/**
	 * Constructs a new {@link InvalidDataException} with a specified exception
	 * message.
	 * 
	 * @param message The message of the exception.
	 */
	public InvalidDataException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link InvalidDataException} with a specified exception
	 * message and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public InvalidDataException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new {@link InvalidDataException} with <i>null</i> as the
	 * exception message and a specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public InvalidDataException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = -7995357696816793084L;

}
