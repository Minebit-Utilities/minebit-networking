package net.minebit.networking.converting;

import net.minebit.networking.exceptions.conversions.PrimitiveConversionException;

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
	 * @param input The primitive to convert to bytes.
	 * @return The result of the conversion.
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

}
