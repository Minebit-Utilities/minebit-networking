package net.minebit.networking.exceptions;

/**
 * <i>InvalidFileException</i> is a {@link MinebitNetworkingException} that is
 * thrown to indicate that something went wrong with a file.
 * 
 * @author Aggelowe
 *
 */
public class InvalidFileException extends MinebitNetworkingException {

	/**
	 * Constructs a new {@link InvalidFileException} with <i>null</i> as the
	 * exception message.
	 */
	public InvalidFileException() {
		super();
	}

	/**
	 * Constructs a new {@link InvalidFileException} with a specified exception
	 * message.
	 * 
	 * @param message The message of the exception.
	 */
	public InvalidFileException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link InvalidFileException} with a specified exception
	 * message and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public InvalidFileException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new {@link InvalidFileException} with <i>null</i> as the
	 * exception message and a specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public InvalidFileException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 6431285055381919129L;

}
