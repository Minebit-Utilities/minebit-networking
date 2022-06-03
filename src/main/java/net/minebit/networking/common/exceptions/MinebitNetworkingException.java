package net.minebit.networking.common.exceptions;

/**
 * <i>MinebitNetworkingException</i> is an {@link Exception} that is the
 * superclass of all the exceptions of all exceptions concerning Minebit
 * Networking.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class MinebitNetworkingException extends Exception {

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
	 * Constructs a new {@link MinebitNetworkingException} with <i>null</i> as the
	 * exception message and a specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public MinebitNetworkingException(Throwable cause) {
		super(cause);
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

	private static final long serialVersionUID = -8880766421012018247L;

}
