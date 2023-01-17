package net.minebit.networking.exceptions;

import net.minebit.networking.util.Reference;

/**
 * This exception is a {@link NetworkingException} which is thrown if the
 * {@link Reference} values could not be loaded from the <i>info</i> properties file at
 * startup.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public final class ReferenceLoadingException extends NetworkingException {

	/**
	 * This constructor constructs a new {@link ReferenceLoadingException} that is
	 * thrown at startup if loading the {@link Reference} values fails.
	 * 
	 * @param message The message description describing the exception
	 */
	public ReferenceLoadingException(String message) {
		super(message);
	}

	/**
	 * This constructor constructs a new {@link ReferenceLoadingException} that is
	 * thrown at startup if loading the {@link Reference} values fails.
	 * 
	 * @param message The message description describing the exception
	 * @param cause   The exception that caused this exception to be thrown.
	 */
	public ReferenceLoadingException(String message, Exception cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = 1L;

}
