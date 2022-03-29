package net.minebit.networking.common;

/**
 * This class contains various mandatory utilities for the library to work.
 * These utilities may be used for other purposes as well.
 * 
 * @author Aggelowe
 *
 */
public final class NetworkUtils {

	/**
	 * This value represents the size of a kilobyte in bytes.
	 */
	public static final int KILOBYTE = 1024;

	/**
	 * This value represents the size of a megabyte in bytes.
	 */
	public static final int MEGABYTE = 1048576;

	/**
	 * This value represents the size of a gigabyte in bytes.
	 */
	public static final int GIGABYTE = 1073741824;

	/**
	 * This method merges the given byte arrays and returns the result of the
	 * merging.
	 * 
	 * @param byteArrays The byte arrays to merge
	 * @return The merged arrays
	 */
	public static byte[] mergeArrays(byte[]... byteArrays) {
		int resultSize = 0;
		for (byte[] byteArray : byteArrays) {
			resultSize += byteArray.length;
		}
		byte[] result = new byte[resultSize];
		int counter = 0;
		for (byte[] byteArray : byteArrays) {
			for (byte element : byteArray) {
				result[counter] = element;
				counter++;
			}
		}
		return result;
	}
	
}
