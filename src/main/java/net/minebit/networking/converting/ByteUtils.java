package net.minebit.networking.converting;

import net.minebit.networking.exceptions.general.InputException;

/**
 * The {@link ByteUtils} class contains several methods which are used
 * throughout the project.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public final class ByteUtils {

	/**
	 * This method converts the given byte array to another byte array of the given
	 * length.
	 * <p>
	 * <b>Warning:</b> There may be data loss if the given length is smaller than
	 * the length of the given array.
	 * </p>
	 * 
	 * @param input  The byte array to resize
	 * @param length The length to resize the array to
	 * @return The resized byte array
	 * @throws InputException If the given array is null or the given length is
	 *                        smaller than 0
	 */
	public static byte[] resize(byte[] input, int length) throws InputException {
		if (input == null) {
			throw new InputException("The given bytes cannot be NULL!");
		}
		if (length < 0) {
			throw new InputException("The given length cannot be smaller than 0!");
		}
		final int inputLength = input.length;
		byte[] result = new byte[length];
		int counter = length - 1;
		while (counter >= 0) {
			int outputPosition = length - counter - 1;
			int inputPosition = inputLength - counter - 1;
			if (counter < inputLength) {
				result[outputPosition] = input[inputPosition];
			} else {
				result[outputPosition] = 0x0;
			}
			counter--;
		}
		return result;
	}

}
