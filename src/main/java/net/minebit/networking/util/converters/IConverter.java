package net.minebit.networking.util.converters;

import java.util.Optional;

/**
 * Classes implementing this interface define how a primitive or an
 * {@link Object} may be converted to a byte array and vice versa.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 * @param <ConvertibleType> The type of object that may be converted from and to
 *                          byte arrays
 */
public interface IConverter<ConvertibleType> {

	/**
	 * This method converts the input {@link Object} of the given type to an new
	 * byte array and returns it in an {@link Optional}.
	 * 
	 * @param input The object to convert to a byte array
	 * @return The given object converted into a byte array
	 * @see IConverter
	 */
	public Optional<byte[]> sourceToBytes(ConvertibleType input);

	/**
	 * This method converts the input byte array to an instance of an {@link Object}
	 * of the given type and returns it in an {@link Optional}.
	 * 
	 * @param input The byte array to convert to an abject
	 * @return The given byte array converted into an object
	 * @see IConverter
	 */
	public Optional<ConvertibleType> bytesToSource(byte[] input);

}
