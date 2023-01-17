package net.minebit.networking.exceptions;

/**
 * This exception is a {@link NetworkingException} which is thrown whenever an
 * {@link Object} contained in the library which shouldn't be constructible is
 * constructed.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public final class IllegalConstructionException extends NetworkingException {

	/**
	 * This constructor constructs a new {@link IllegalConstructionException} that
	 * is thrown whenever an <i>illegal</i> construction of a library object occurs.
	 * 
	 * @param message The message description describing the exception
	 */
	public IllegalConstructionException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
