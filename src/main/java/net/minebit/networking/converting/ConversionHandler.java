package net.minebit.networking.converting;

import java.nio.charset.StandardCharsets;

import net.minebit.networking.exceptions.conversions.ConversionException;
import net.minebit.networking.exceptions.general.InputException;

/**
 * {@link ConversionHandler} handles the conversion of objects, primitives and
 * raw data to bytes and vice versa.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public final class ConversionHandler {

	/**
	 * This method converts the given object or primitive to a byte array.
	 * 
	 * @param input The object/primitive to convert to bytes.
	 * @return The result of the conversion
	 * @throws ConversionException If an error occurs during the conversion.
	 */
	public static byte[] toBytes(Object input) throws ConversionException {
		if (input == null) {
			throw new ConversionException("The given object cannot be NULL!");
		}
		Class<?> inputClass = input.getClass();
		if (inputClass.equals(byte[].class)) {
			return (byte[]) input;
		}
		if (inputClass.equals(String.class)) {
			String asString = (String) input;
			return asString.getBytes(StandardCharsets.UTF_8);
		}
		boolean representsPrimitive = false;
		try {
			representsPrimitive = PrimitiveConverter.representsPrimitive(inputClass);
		} catch (InputException exception) {
			throw new ConversionException("An error occured while checking whether the given class is a primitive!", exception);
		}
		if (representsPrimitive) {
			return PrimitiveConverter.convertToBytes(input);
		}
		return ObjectConverter.convertToBytes(input);
	}
	
	public static Object toObject(byte[] input, Class<?> objectClass) throws ConversionException {
		if (input == null) {
			throw new ConversionException("The given input cannot be NULL!");
		}
		if (objectClass == null) {
			throw new ConversionException("The given class cannot be NULL!");
		}
		if (objectClass.equals(byte[].class)) {
			return input;
		}
		if (objectClass.equals(String.class)) {
			return new String(input, StandardCharsets.UTF_8);
		}
		boolean representsPrimitive = false;
		try {
			representsPrimitive = PrimitiveConverter.representsPrimitive(objectClass);
		} catch (InputException exception) {
			throw new ConversionException("An error occured while checking whether the given class is a primitive!", exception);
		}
		if (representsPrimitive) {
			return PrimitiveConverter.convertToPrimitive(input, objectClass);
		}
		return ObjectConverter.convertToObject(input, objectClass);
	}

}
