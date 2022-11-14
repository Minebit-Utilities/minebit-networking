package net.minebit.networking.miscellaneous;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import net.minebit.networking.exceptions.ByteException;

/**
 * The {@link ByteUtils} class contains several methods which are used
 * throughout the project.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public final class ByteUtils {

	private ByteUtils() {
	}

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
	 * @throws ByteException If the given array is null or the given length is
	 *                       smaller than 0
	 */
	public static byte[] resize(byte[] input, int length) throws ByteException {
		if (input == null) {
			throw new ByteException("The given bytes cannot be NULL!");
		}
		if (length < 0) {
			throw new ByteException("The given length cannot be smaller than 0!");
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

	/**
	 * This method compresses the given byte array using GZip.
	 * 
	 * @param input The byte array to compress
	 * @return The compressed byte array
	 * @throws ByteException If an error occurs while compressing the given bytes
	 */
	public static byte[] compress(byte[] input, int bufferSize) throws ByteException {
		if (input == null) {
			throw new ByteException("The given byte array must not be NULL!");
		}
		if (bufferSize <= 0) {
			throw new ByteException("The given buffer size must be larger than 0!");
		}
		Deflater deflater = new Deflater(Deflater.BEST_SPEED, true);
		deflater.setInput(input);
		deflater.finish();
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[bufferSize];
		while (!deflater.finished()) {
			int remaining = deflater.deflate(buffer);
			if (remaining > 0) {
				byteOutputStream.write(buffer, 0, remaining);
			}
		}
		deflater.end();
		return byteOutputStream.toByteArray();
	}

	/**
	 * This method decompresses the given byte array using GZip.
	 * 
	 * @param input The byte array to decompress
	 * @return The decompressed byte array
	 * @throws ByteException If an error occurs while decompressing the given bytes
	 */
	public static byte[] decompress(byte[] input, int bufferSize) throws ByteException {
		if (input == null) {
			throw new ByteException("The given byte array must not be NULL!");
		}
		if (bufferSize <= 0) {
			throw new ByteException("The given buffer size must be larger than 0!");
		}
		Inflater inflater = new Inflater(true);
		inflater.setInput(input);
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[bufferSize];
		while (!inflater.finished()) {
			int remaining;
			try {
				remaining = inflater.inflate(buffer);
			} catch (DataFormatException exception) {
				throw new ByteException("An error occured while decompressing the given data!", exception);
			}
			if (remaining > 0) {
				byteOutputStream.write(buffer, 0, remaining);
			}
		}
		inflater.end();
		return byteOutputStream.toByteArray();
	}

	/**
	 * This method merges the two given byte arrays.
	 * 
	 * @param array1 THe first byte array
	 * @param array2 The second byte array
	 * @return The result of the merge
	 */
	public static byte[] merge(byte[] array1, byte[] array2) {
		byte[] result = new byte[array1.length + array2.length];
		System.arraycopy(array1, 0, result, 0, array1.length);
		System.arraycopy(array2, 0, result, array1.length, array2.length);
		return result;
	}

}
