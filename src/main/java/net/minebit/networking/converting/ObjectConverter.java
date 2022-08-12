package net.minebit.networking.converting;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.minebit.networking.exceptions.conversions.ObjectConversionException;

/**
 * {@link PrimitiveConverter} handles the conversion of primitives to byte
 * arrays and vice versa.
 * 
 * @author Aggelowe
 * @since 0.1
 */
public final class ObjectConverter {

	private ObjectConverter() {
	}

	/**
	 * This method returns the given primitive as a byte array.
	 * 
	 * @param input The primitive to convert to bytes
	 * @return The result of the conversion
	 * @throws ObjectConversionException If an error occurs while converting the
	 *                                   object.
	 */
	public static byte[] convertToBytes(Object input) throws ObjectConversionException {
		if (input == null) {
			throw new ObjectConversionException("The given object cannot be NULL!");
		}
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream;
		try {
			objectOutputStream = new ObjectOutputStream(byteOutputStream);
			objectOutputStream.writeObject(input);
		} catch (IOException exception) {
			throw new ObjectConversionException("An error occured while serialising the given object.", exception);
		}
		return byteOutputStream.toByteArray();

	}

	/**
	 * This method returns the given byte array as an object.
	 * 
	 * @param input       The bytes to convert to an object
	 * @param objectClass The class of the output object
	 * @return The result of the conversion
	 * @throws ObjectConversionExceptionv If an error occurs while converting the
	 *                                    bytes
	 */
	public static Object convertToObject(byte[] input) throws ObjectConversionException {
		if (input == null) {
			throw new ObjectConversionException("The given bytes cannot be NULL!");
		}
		ByteArrayInputStream byteInputStream = new ByteArrayInputStream(input);
		try {		
			ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);
			return objectInputStream.readObject();
		} catch (ClassNotFoundException | IOException exception) {
			throw new ObjectConversionException("An error occured while deserialising the given bytes.", exception);
		}
	}

}
