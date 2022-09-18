package net.minebit.networking.converting;

import net.minebit.networking.exceptions.conversions.ConversionException;

/**
 * This interface defines how an object can be converted to a byte array and
 * vice versa.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public interface IConverter<ConversionType> {

	/**
	 * This method converts the given object to a byte array.
	 * 
	 * @param input The object to convert to bytes.
	 * @return The result of the conversion
	 * @throws ConversionException If an error occurs during the conversion.
	 */
	public byte[] toBytes(ConversionType input) throws ConversionException;

	/**
	 * This method returns the given byte array as an object.
	 * 
	 * @param input       The byte array to convert to an object
	 * @param objectClass The class of the output object
	 * @return The result of the conversion
	 * @throws ConversionException If an error occurs while converting the byte
	 *                             array
	 */
	public ConversionType toObject(byte[] input) throws ConversionException;

}
