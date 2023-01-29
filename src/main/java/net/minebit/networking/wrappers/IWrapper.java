package net.minebit.networking.wrappers;

import java.util.Optional;

/**
 * Classes implementing this interface define how an array of bytes may be
 * wrapped into an new array of bytes or unwrapped into the original.
 * 
 * TODO Connection data will be given as input for both methods
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 */
public interface IWrapper {

	/**
	 * This method wraps the given byte array using the defined algorithm and
	 * returns the wrapped result in an {@link Optional}. If the an error occurs
	 * while wrapping the bytes the {@link Optional} will be empty.
	 * 
	 * @param bytes The byte array to wrap
	 * @return The resultant byte array
	 */
	public Optional<byte[]> wrap(byte[] bytes);

	/**
	 * This method unwraps the given byte array using the defined algorithm and
	 * returns the unwrapped result in an {@link Optional}. If the an error occurs
	 * while unwrapping the bytes the {@link Optional} will be empty.
	 * 
	 * @param bytes The byte array to unwrap
	 * @return The resultant byte array
	 */
	public Optional<byte[]> unwrap(byte[] bytes);

}
