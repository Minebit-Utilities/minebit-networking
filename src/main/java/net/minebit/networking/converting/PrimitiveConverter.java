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
		byte[] result = new byte[1];
		if (input == true) {
			result[0] = 1;
		} else {
			result[0] = 0;
		}
		return result;
	}

	/**
	 * This method converts the given byte to an array of bytes.
	 * 
	 * @param input The byte to convert
	 * @return The result of the conversion
	 */
	private static byte[] byteToBytes(byte input) {
		byte[] result = new byte[1];
		result[0] = input;
		return result;
	}

	/**
	 * This method converts the given short to an array of bytes.
	 * 
	 * @param input The short to convert
	 * @return The result of the conversion
	 */
	private static byte[] shortToBytes(short input) {
		byte[] result = new byte[2];
		result[0] = (byte) (input >> 8);
		result[1] = (byte) input;
		return result;
	}

	/**
	 * This method converts the given int to an array of bytes.
	 * 
	 * @param input The int to convert
	 * @return The result of the conversion
	 */
	private static byte[] integerToBytes(int input) {
		byte[] result = new byte[4];
		result[0] = (byte) (input >> 24);
		result[1] = (byte) (input >> 16);
		result[2] = (byte) (input >> 8);
		result[3] = (byte) input;
		return result;
	}

	/**
	 * This method converts the given long to an array of bytes.
	 * 
	 * @param input The long to convert
	 * @return The result of the conversion
	 */
	private static byte[] longToBytes(long input) {
		byte[] result = new byte[8];
		result[0] = (byte) (input >> 56);
		result[1] = (byte) (input >> 48);
		result[2] = (byte) (input >> 40);
		result[3] = (byte) (input >> 32);
		result[4] = (byte) (input >> 24);
		result[5] = (byte) (input >> 16);
		result[6] = (byte) (input >> 8);
		result[7] = (byte) input;
		return result;
	}

	/**
	 * This method converts the given float to an array of bytes.
	 * 
	 * @param input The float to convert
	 * @return The result of the conversion
	 */
	private static byte[] floatToBytes(float input) {
		int asInteger = Float.floatToIntBits(input);
		byte[] result = integerToBytes(asInteger);
		return result;
	}

	/**
	 * This method converts the given double to an array of bytes.
	 * 
	 * @param input The double to convert
	 * @return The result of the conversion
	 */
	private static byte[] doubleToBytes(double input) {
		long asLong = Double.doubleToLongBits(input);
		byte[] result = longToBytes(asLong);
		return result;
	}

	/**
	 * This method converts the given char to an array of bytes.
	 * 
	 * @param input The char to convert
	 * @return The result of the conversion
	 */
	private static byte[] characterToBytes(char input) {
		byte[] result = new byte[2];
		result[0] = (byte) (input >> 8);
		result[1] = (byte) input;
		return result;
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
	public static Object convertToObject(byte[] input, Class<?> objectClass) throws PrimitiveConversionException {
		if (input == null) {
			throw new PrimitiveConversionException("The given input cannot be NULL!");
		}
		if (objectClass == null) {
			throw new PrimitiveConversionException("The given class cannot be NULL!");
		}
		if (objectClass.equals(Boolean.class)) {
			boolean result = bytesToBoolean(input);
			return new Boolean(result);
		}
		if (objectClass.equals(Byte.class)) {
			byte result = bytesToByte(input);
			return new Byte(result);
		}
		if (objectClass.equals(Short.class)) {
			short result = bytesToShort(input);
			return new Short(result);
		}
		if (objectClass.equals(Integer.class)) {
			int result = bytesToInteger(input);
			return new Integer(result);
		}
		if (objectClass.equals(Long.class)) {
			long result = bytesToLong(input);
			return new Long(result);
		}
		if (objectClass.equals(Float.class)) {
			float result = bytesToFloat(input);
			return new Float(result);
		}
		if (objectClass.equals(Double.class)) {
			double result = bytesToDouble(input);
			return new Double(result);
		}
		if (objectClass.equals(Character.class)) {
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
		byte data = (byte) (bytes[0] & 0xFF);
		return data;
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
		short result = (short) ((bytes[0] & 0xFF) << 8 | (bytes[1] & 0xFF));
		return result;
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
		int result = (bytes[0] & 0xFF) << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);
		return result;
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
		long result = ((long) (bytes[0] & 0xFF) << 56) | ((long) (bytes[1] & 0xFF) << 48) | ((long) (bytes[2] & 0xFF) << 40) | ((long) (bytes[3] & 0xFF) << 32) | ((long) (bytes[4] & 0xFF) << 24) | ((long) (bytes[5] & 0xFF) << 16) | ((long) (bytes[6] & 0xFF) << 8) | ((long) bytes[7] & 0xFF);
		return result;
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
		float result = Float.intBitsToFloat(asInteger);
		return result;
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
		double result = Double.longBitsToDouble(asLong);
		return result;
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
		char result = (char) ((bytes[0] & 0xFF) << 8 | (bytes[1] & 0xFF));
		return result;
	}
}
