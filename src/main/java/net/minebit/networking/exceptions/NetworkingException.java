package net.minebit.networking.exceptions;

/**
 * This exception is a {@link RuntimeException} that is extended by all other
 * exceptions of this library. It's main purpose is to distinguish which
 * exceptions belong to the library. Also it contains a timestamp value of the
 * Unix time (in milliseconds) it was created.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 */
public class NetworkingException extends RuntimeException {

	private final long timestamp;

	/**
	 * This constructor constructs a new {@link NetworkingException} that is thrown
	 * whenever a networking application doesn't function as intended.
	 * 
	 * @param message The message description describing the exception
	 */
	public NetworkingException(String message) {
		this(message, null);
	}

	/**
	 * This constructor constructs a new {@link NetworkingException} that is thrown
	 * whenever a networking application doesn't function as intended.
	 * 
	 * @param message The message description describing the exception
	 * @param cause   The exception that caused this exception to be thrown.
	 */
	public NetworkingException(String message, Exception cause) {
		super(message, cause);
		this.timestamp = System.currentTimeMillis();
	}

	/**
	 * This method returns the Unix time (in milliseconds) in which the
	 * {@link NetworkingException} was constructed.
	 * 
	 * @return The exception's construction timestamp.
	 */
	public final long getTimestamp() {
		return this.timestamp;
	}

	private static final long serialVersionUID = 1L;

}
