package net.minebit.networking.exceptions;

/**
 * <i>DataTooLargeException</i> is a {@link MinebitNetworkingException} that is
 * thrown to indicate that the given data is too big too fit inside an array. 
 * 
 * @author Aggelowe
 *
 */
public class DataTooLargeException extends MinebitNetworkingException {

	/**
	 * Constructs a new {@link DataTooLargeException} with <i>null</i> as the
	 * exception message.
	 */
	public DataTooLargeException() {
		super();
	}

	/**
	 * Constructs a new {@link DataTooLargeException} with a specified exception
	 * message.
	 * 
	 * @param message The message of the exception.
	 */
	public DataTooLargeException(String message) {
		super(message);
	}

	/**
	 * Constructs a new {@link DataTooLargeException} with a specified exception
	 * message and cause.
	 * 
	 * @param message The message of the exception.
	 * @param cause   The cause of the exception.
	 */
	public DataTooLargeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new {@link DataTooLargeException} with <i>null</i> as the
	 * exception message and a specified cause.
	 * 
	 * @param cause The cause of the exception.
	 */

	public DataTooLargeException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = -1240974309569508163L;

}
