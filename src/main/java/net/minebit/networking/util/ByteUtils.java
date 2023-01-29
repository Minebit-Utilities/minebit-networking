package net.minebit.networking.util;

import java.util.Optional;

import net.minebit.networking.exceptions.IllegalConstructionException;

/**
 * This class contains several methods that perform byte operations and are used
 * throughout the project.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public final class ByteUtils {

	/**
	 * This constructor throws an {@link IllegalConstructionException} as
	 * {@link ByteUtils} objects can't and shouldn't be constructed
	 */
	private ByteUtils() {
		throw new IllegalConstructionException(ByteUtils.class.getSimpleName() + " objects cannot be constructed!");
	}

	/**
	 * This method creates and returns a new byte array contained in an
	 * {@link Optional}, with length equal to the given end value minus the given
	 * start value plus one and places the elements of the given source with indexed
	 * between the given start and end inside the new array. If the given array is
	 * NULL, the given start is bigger than the given end, the given start is
	 * negative or the given end is bigger or equal to the length of the given array
	 * an empty {@link Optional} will be returned.
	 * 
	 * @param source The array to get the elements from
	 * @param start  The index to start copying elements from
	 * @param end    The index of the last element to copy
	 * @return The resultant byte array
	 */
	public static Optional<byte[]> rip(byte[] source, int start, int end) {
		if (source == null || start > end || start < 0 || end >= source.length) {
			return Optional.empty();
		}
		byte[] result = new byte[end - start + 1];
		for (int counter = start; counter <= end; counter++) {
			result[counter - start] = source[counter];
		}
		return Optional.of(result);
	}

	/**
	 * This method creates and returns a new byte array contained in an
	 * {@link Optional}, with length equal to the length of the given source plus
	 * the given left and right values and places the elements of the given source
	 * inside the new array starting from the index with the given left value. If
	 * the given array is NULL or the count is negative an empty {@link Optional}
	 * will be returned.
	 * 
	 * @param source The byte array to enlarge
	 * @param left   The number of bytes to enlarge the array by at the start of the
	 *               given array
	 * @param right  The number of bytes to enlarge the array by at the end of the
	 *               given array
	 * @return The resultant byte array
	 */
	public static Optional<byte[]> enlarge(byte[] source, int left, int right) {
		if (source == null || left < 0 || right < 0) {
			return Optional.empty();
		}
		byte[] result = new byte[source.length + left + right];
		for (int counter = 0; counter < source.length; counter++) {
			result[counter + left] = source[counter];
		}
		return Optional.of(result);
	}

	/**
	 * This method creates and returns a new byte array contained in an
	 * {@link Optional}, with length equal to the sum of the lengths of the given
	 * arrays and places the elements of each array one after the other thus merging
	 * the two arrays. If either of the arrays is NULL an empty {@link Optional}
	 * will be returned.
	 * 
	 * @param partA The first component of the merge
	 * @param partB The second component of the merge
	 * @see #enlarge(byte[], int, int)
	 * @return The resultant byte array
	 */
	public static Optional<byte[]> merge(byte[] partA, byte[] partB) {
		if (partA == null || partB == null) {
			return Optional.empty();
		}
		Optional<byte[]> enlarged = enlarge(partA, 0, partB.length);
		if (!enlarged.isPresent()) {
			return Optional.empty();
		}
		byte[] result = enlarged.get();
		for (int counter = 0; counter < partB.length; counter++) {
			result[counter + partA.length] = partB[counter];
		}
		return Optional.of(result);
	}

	/**
	 * This method creates and returns a new byte array contained in an
	 * {@link Optional}, with length equal to the length of the given source and
	 * places the elements of the given source to the newly initialized array. If
	 * the given source is NULL an empty {@link Optional} will be returned.
	 * 
	 * @param source The byte array to clone
	 * @return The resultant byte array
	 */
	public static Optional<byte[]> clone(byte[] source) {
		if (source == null) {
			return Optional.empty();
		}
		byte[] result = new byte[source.length];
		for (int count = 0; count < source.length; count++) {
			result[count] = source[count];
		}
		return Optional.of(result);
	}

	/**
	 * This method places the elements of the given place array at the start
	 * position of the given source array until they end or the end of the source
	 * array has been reached. If the either of the arrays is NULL, the position is
	 * larger or equal to the length of the source array or the position is negative
	 * the operation will be marked as failed.
	 * 
	 * @param source   The source array to place the new array on
	 * @param place    The array to place on the source array
	 * @param position The position to start placing the elements
	 * @return The success of the operation
	 */
	public static boolean overwrite(byte[] source, byte[] place, int position) {
		if (source == null || place == null || position >= source.length || position < 0) {
			return false;
		}
		for (int count = position; count < source.length; count++) {
			if (count >= place.length + position) {
				break;
			}
			source[count] = place[count - position];
		}
		return true;
	}

}
