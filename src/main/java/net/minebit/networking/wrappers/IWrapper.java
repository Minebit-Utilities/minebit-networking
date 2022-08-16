package net.minebit.networking.wrappers;

import net.minebit.networking.exceptions.wrappers.WrapperException;

/**
 * This interface represents a wrapper that can be wrapped over byte arrays in
 * order to modify the result.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public interface IWrapper {

	/**
	 * This method wraps the given byte array and modifies it in order to get the
	 * wanted result.
	 * 
	 * @param bytes The bytes to wrap
	 * @return The wrapped result
	 * @param If an error occurs while wrapping the given bytes
	 */
	public byte[] wrap(byte[] bytes) throws WrapperException;

	/**
	 * This method unwraps the given byte array in order to get the original byte
	 * array
	 * 
	 * @param bytes The wrapped byte array
	 * @return The original byte array
	 * @param If an error occurs while unwrapping the given bytes
	 */
	public byte[] unwrap(byte[] bytes) throws WrapperException;

}
