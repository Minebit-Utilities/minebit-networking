package net.minebit.networking.converting;

import net.minebit.networking.exceptions.conversions.PrimitiveConversionException;
import net.minebit.networking.exceptions.general.InputException;

/**
 * {@link PrimitiveConverter} handles the conversion of primitives to byte
 * arrays and vice versa.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public final class PrimitiveConverter {

	/**
	 * This method returns the given primitive as a byte array.
	 * 
	 * @param input The primitive to convert to bytes
	 * @return The result of the conversion
	 * @throws PrimitiveConversionException If an error occurs while converting the
	 *                                      primitive.
	 */
	public static byte[] convertToBytes(Object input) throws PrimitiveConversionException {
		if (input == null) {
			throw new PrimitiveConversionException("The given input cannot be NULL!");
		}
		Class<?> inputClass = input.getClass();
		if (inputClass.equals(Boolean.class)) {
			boolean asBoolean = (boolean) input;
			return booleanToBytes(asBoolean);
		}
		if (inputClass.equals(Byte.class)) {
			byte asByte = (byte) input;
			return byteToBytes(asByte);
		}
		if (inputClass.equals(Short.class)) {
			short asShort = (short) input;
			return shortToBytes(asShort);
		}
		if (inputClass.equals(Integer.class)) {
			int asInteger = (int) input;
			return integerToBytes(asInteger);
		}
		if (inputClass.equals(Long.class)) {
			long asLong = (long) input;
			return longToBytes(asLong);
		}
		if (inputClass.equals(Float.class)) {
			float asFloat = (float) input;
			return floatToBytes(asFloat);
		}
		if (inputClass.equals(Double.class)) {
			double asDouble = (double) input;
			return doubleToBytes(asDouble);
		}
		if (inputClass.equals(Character.class)) {
			char asCharacter = (char) input;
			return characterToBytes(asCharacter);
		}
		throw new PrimitiveConversionException("The given object is not a primitive!");
	}

	/**
	 * This method converts the given boolean to an array of bytes.
	 * 
	 * @param input The boolean to convert
	 * @return The result of the conversion
	 */
	private static byte[] booleanToBytes(boolean input) {
		return input ? new byte[] { 1 } : new byte[] { 0 };
	}

	/**
	 * This method converts the given byte to an array of bytes.
	 * 
	 * @param input The byte to convert
	 * @return The result of the conversion
	 */
	private static byte[] byteToBytes(byte input) {
		return new byte[] { input };
	}

	/**
	 * This method converts the given short to an array of bytes.
	 * 
	 * @param input The short to convert
	 * @return The result of the conversion
	 */
	private static byte[] shortToBytes(short input) {
		return new byte[] { (byte) (input >> 8), (byte) input };
	}

	/**
	 * This method converts the given int to an array of bytes.
	 * 
	 * @param input The int to convert
	 * @return The result of the conversion
	 */
	private static byte[] integerToBytes(int input) {
		return new byte[] { (byte) (input >> 24), (byte) (input >> 16), (byte) (input >> 8), (byte) input };
	}

	/**
	 * This method converts the given long to an array of bytes.
	 * 
	 * @param input The long to convert
	 * @return The result of the conversion
	 */
	private static byte[] longToBytes(long input) {
		return new byte[] { (byte) (input >> 56), (byte) (input >> 48), (byte) (input >> 40), (byte) (input >> 32), (byte) (input >> 24), (byte) (input >> 16), (byte) (input >> 8), (byte) input };
	}

	/**
	 * This method converts the given float to an array of bytes.
	 * 
	 * @param input The float to convert
	 * @return The result of the conversion
	 */
	private static byte[] floatToBytes(float input) {
		int asInteger = Float.floatToIntBits(input);
		return integerToBytes(asInteger);
	}

	/**
	 * This method converts the given double to an array of bytes.
	 * 
	 * @param input The double to convert
	 * @return The result of the conversion
	 */
	private static byte[] doubleToBytes(double input) {
		long asLong = Double.doubleToLongBits(input);
		return longToBytes(asLong);
	}

	/**
	 * This method converts the given char to an array of bytes.
	 * 
	 * @param input The char to convert
	 * @return The result of the conversion
	 */
	private static byte[] characterToBytes(char input) {
		return new byte[] { (byte) (input >> 8), (byte) input };
	}

	/**
	 * This method returns the given byte array as a primitive.
	 * 
	 * @param input       The bytes to convert to a primitive
	 * @param objectClass The class of the output object
	 * @return The result of the conversion
	 * @throws PrimitiveConversionException If an error occurs while converting the
	 *                                      bytes
	 */
	public static Object convertToPrimitive(byte[] input, Class<?> objectClass) throws PrimitiveConversionException {
		if (input == null) {
			throw new PrimitiveConversionException("The given input cannot be NULL!");
		}
		if (objectClass == null) {
			throw new PrimitiveConversionException("The given class cannot be NULL!");
		}
		if (objectClass.equals(Boolean.class) || objectClass.equals(Boolean.TYPE)) {
			boolean result = bytesToBoolean(input);
			return new Boolean(result);
		}
		if (objectClass.equals(Byte.class) || objectClass.equals(Byte.TYPE)) {
			byte result = bytesToByte(input);
			return new Byte(result);
		}
		if (objectClass.equals(Short.class) || objectClass.equals(Short.TYPE)) {
			short result = bytesToShort(input);
			return new Short(result);
		}
		if (objectClass.equals(Integer.class) || objectClass.equals(Integer.TYPE)) {
			int result = bytesToInteger(input);
			return new Integer(result);
		}
		if (objectClass.equals(Long.class) || objectClass.equals(Long.TYPE)) {
			long result = bytesToLong(input);
			return new Long(result);
		}
		if (objectClass.equals(Float.class) || objectClass.equals(Float.TYPE)) {
			float result = bytesToFloat(input);
			return new Float(result);
		}
		if (objectClass.equals(Double.class) || objectClass.equals(Double.TYPE)) {
			double result = bytesToDouble(input);
			return new Double(result);
		}
		if (objectClass.equals(Character.class) || objectClass.equals(Character.TYPE)) {
			char result = bytesToCharacter(input);
			return new Character(result);
		}
		throw new PrimitiveConversionException("The given object is not a primitive!");
	}

	/**
	 * This method converts the given byte arrays to a boolean.
	 * 
	 * @param input The bytes to convert
	 * @return The result of the conversion
	 * @throws PrimitiveConversionException If an error occurs while converting the
	 *                                      bytes
	 */
	private static boolean bytesToBoolean(byte[] input) throws PrimitiveConversionException {
		byte[] bytes;
		try {
			bytes = ByteUtils.resize(input, 1);
		} catch (InputException exception) {
			throw new PrimitiveConversionException("An error occured while resizing the given byte array!", exception);
		}
		byte data = bytes[0];
		switch (data & 0x1) {
			case 1:
				return true;
			default:
				return false;
		}
	}

	/**
	 * This method converts the given byte arrays to a byte.
	 * 
	 * @param input The bytes to convert
	 * @return The result of the conversion
	 * @throws PrimitiveConversionException If an error occurs while converting the
	 *                                      bytes
	 */
	private static byte bytesToByte(byte[] input) throws PrimitiveConversionException {
		byte[] bytes;
		try {
			bytes = ByteUtils.resize(input, 1);
		} catch (InputException exception) {
			throw new PrimitiveConversionException("An error occured while resizing the given byte array!", exception);
		}
		return (byte) (bytes[0] & 0xFF);
	}

	/**
	 * This method converts the given byte arrays to a short.
	 * 
	 * @param input The bytes to convert
	 * @return The result of the conversion
	 * @throws PrimitiveConversionException If an error occurs while converting the
	 *                                      bytes
	 */
	private static short bytesToShort(byte[] input) throws PrimitiveConversionException {
		byte[] bytes;
		try {
			bytes = ByteUtils.resize(input, 2);
		} catch (InputException exception) {
			throw new PrimitiveConversionException("An error occured while resizing the given byte array!", exception);
		}
		return (short) ((bytes[0] & 0xFF) << 8 | (bytes[1] & 0xFF));
	}

	/**
	 * This method converts the given byte arrays to an integer.
	 * 
	 * @param input The bytes to convert
	 * @return The result of the conversion
	 * @throws PrimitiveConversionException If an error occurs while converting the
	 *                                      bytes
	 */
	private static int bytesToInteger(byte[] input) throws PrimitiveConversionException {
		byte[] bytes;
		try {
			bytes = ByteUtils.resize(input, 4);
		} catch (InputException exception) {
			throw new PrimitiveConversionException("An error occured while resizing the given byte array!", exception);
		}
		return (bytes[0] & 0xFF) << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);
	}

	/**
	 * This method converts the given byte arrays to a long.
	 * 
	 * @param input The bytes to convert
	 * @return The result of the conversion
	 * @throws PrimitiveConversionException If an error occurs while converting the
	 *                                      bytes
	 */
	private static long bytesToLong(byte[] input) throws PrimitiveConversionException {
		byte[] bytes;
		try {
			bytes = ByteUtils.resize(input, 8);
		} catch (InputException exception) {
			throw new PrimitiveConversionException("An error occured while resizing the given byte array!", exception);
		}
		return ((long) (bytes[0] & 0xFF) << 56) | ((long) (bytes[1] & 0xFF) << 48) | ((long) (bytes[2] & 0xFF) << 40) | ((long) (bytes[3] & 0xFF) << 32) | ((long) (bytes[4] & 0xFF) << 24) | ((long) (bytes[5] & 0xFF) << 16) | ((long) (bytes[6] & 0xFF) << 8) | ((long) bytes[7] & 0xFF);
	}

	/**
	 * This method converts the given byte arrays to a float.
	 * 
	 * @param input The bytes to convert
	 * @return The result of the conversion
	 * @throws PrimitiveConversionException If an error occurs while converting the
	 *                                      bytes
	 */
	private static float bytesToFloat(byte[] input) throws PrimitiveConversionException {
		int asInteger = bytesToInteger(input);
		return Float.intBitsToFloat(asInteger);
	}

	/**
	 * This method converts the given byte arrays to a double.
	 * 
	 * @param input The bytes to convert
	 * @return The result of the conversion
	 * @throws PrimitiveConversionException If an error occurs while converting the
	 *                                      bytes
	 */
	private static double bytesToDouble(byte[] input) throws PrimitiveConversionException {
		long asLong = bytesToLong(input);
		return Double.longBitsToDouble(asLong);
	}

	/**
	 * This method converts the given byte arrays to a char.
	 * 
	 * @param input The bytes to convert
	 * @return The result of the conversion
	 * @throws PrimitiveConversionException If an error occurs while converting the
	 *                                      bytes
	 */
	private static char bytesToCharacter(byte[] input) throws PrimitiveConversionException {
		byte[] bytes;
		try {
			bytes = ByteUtils.resize(input, 2);
		} catch (InputException exception) {
			throw new PrimitiveConversionException("An error occured while resizing the given byte array!", exception);
		}
		return (char) ((bytes[0] & 0xFF) << 8 | (bytes[1] & 0xFF));
	}

	/**
	 * This method returns whether the given class represents a primitive type.
	 * 
	 * @param inputClass The class to check
	 * @return Whether the given class represents a primitive type
	 * @throws If the given class is null
	 */
	public static boolean representsPrimitive(Class<?> inputClass) throws InputException {
		if (inputClass == null) {
			throw new InputException("The given class cannot be NULL!");
		}
		if (inputClass.isPrimitive()) {
			return true;
		}
		return inputClass.equals(Boolean.class) || inputClass.equals(Byte.class) || inputClass.equals(Short.class) || inputClass.equals(Integer.class) || inputClass.equals(Long.class) || inputClass.equals(Float.class) || inputClass.equals(Double.class) || inputClass.equals(Character.class);
	}
}
