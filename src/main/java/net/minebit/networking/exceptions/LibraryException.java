package net.minebit.networking.exceptions;

/**
 * {@link LibraryException} is the core exception of the library and all other
 * exceptions included in the library extend it.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class LibraryException extends Exception {

	/**
	 * Constructs a new {@link MinebitNetworkingException} without an exception
	 * message and a cause.
	 */
	public LibraryException() {
		super();
	}

	/**
	 * Constructs a new {@link MinebitNetworkingException} with a specified
	 * exception message but without a specific cause.
	 * 
	 * @param message The message of the exception.
	 */
	public LibraryException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link MinebitNetworkingException} without a message but
	 * with the specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public LibraryException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new {@link MinebitNetworkingException} with a specified
	 * exception message and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public LibraryException(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -7552946254692968550L;

}
