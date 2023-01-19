package net.minebit.networking.exceptions;

/**
 * This exception is a {@link NetworkingException} which is thrown whenever an
 * parameter is inputed to a method or constructor that belongs in the library
 * and is the wrong type and/or contains an error.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public final class IllegalParameterException extends NetworkingException {

	/**
	 * This constructor constructs a new {@link IllegalParameterException} that is
	 * thrown whenever an <i>illegal</i> parameter is provided to a method or
	 * constructor.
	 * 
	 * @param message The message description describing the exception
	 */
	public IllegalParameterException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
