package net.minebit.networking.exceptions;

/**
 * <i>MinebitNetworkingException</i> is a {@link RuntimeException} that is the
 * superclass of all the exceptions of all exceptions concerning Minebit
 * Networking.
 * 
 * @author Aggelowe
 *
 */
public class MinebitNetworkingException extends RuntimeException {

	/**
	 * Constructs a new {@link MinebitNetworkingException} with <i>null</i> as the
	 * exception message.
	 */
	public MinebitNetworkingException() {
		super();
	}

	/**
	 * Constructs a new {@link MinebitNetworkingException} with a specified
	 * exception message.
	 * 
	 * @param message The message of the exception.
	 */
	public MinebitNetworkingException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link MinebitNetworkingException} with a specified
	 * exception message and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public MinebitNetworkingException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new {@link MinebitNetworkingException} with <i>null</i> as the
	 * exception message and a specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public MinebitNetworkingException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = -8880766421012018247L;

}
