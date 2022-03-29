package net.minebit.networking.common.keys;

import net.minebit.networking.exceptions.DataTooLargeException;

/**
 * This class is an {@link ITranslationKey} that defines how double arrays can be
 * translated to byte arrays and vice versa.
 * 
 * @author Aggelowe
 *
 */
public class DoubleArrayTranslationKey implements ITranslationKey<Double[]> {

	public static final DoubleArrayTranslationKey INSTANCE = new DoubleArrayTranslationKey();

	/**
	 * This constructor constructs a new {@link DoubleArrayTranslationKey} that
	 * translates a double array from and to a byte array.
	 */
	private DoubleArrayTranslationKey() {
	}

	/**
	 * This method translates the given double array to a byte array.
	 * 
	 * @param The double array to be translated.
	 * @return The translated bytes.
	 */
	@Override
	public byte[] translateToBytes(Double[] input) {
		if (input.length > 268435456) {
			throw new DataTooLargeException("The given double array must not contain more than 268435456 values.");
		}
		byte[] result = new byte[input.length * 8];
		int counter = 0;
		while (counter < input.length) {
			final long origin = Double.doubleToLongBits(input[counter]);
			result[8 * counter] = (byte) (origin >> 56);
			result[8 * counter + 1] = (byte) (origin >> 48);
			result[8 * counter + 2] = (byte) (origin >> 40);
			result[8 * counter + 3] = (byte) (origin >> 32);
			result[8 * counter + 4] = (byte) (origin >> 24);
			result[8 * counter + 5] = (byte) (origin >> 16);
			result[8 * counter + 6] = (byte) (origin >> 8);
			result[8 * counter + 7] = (byte) origin;
			counter++;
		}
		return result;
	}

	/**
	 * This method translates the given byte array to a double array.
	 * 
	 * @param The byte array to be translated.
	 * @return The translated double array.
	 */
	@Override
	public Double[] translateFromBytes(byte... input) {
		final int translatableSize = (input.length - (input.length % 8)) / 8;
		Double[] result = new Double[translatableSize];
		int counter = translatableSize;
		while (counter > 0) {
			byte firstByte = input[input.length - 8 * counter];
			byte secondByte = input[input.length - 8 * counter + 1];
			byte thirdByte = input[input.length - 8 * counter + 2];
			byte forthByte = input[input.length - 8 * counter + 3];
			byte fifthByte = input[input.length - 8 * counter + 4];
			byte sixthByte = input[input.length - 8 * counter + 5];
			byte seventhByte = input[input.length - 8 * counter + 6];
			byte eighthByte = input[input.length - 8 * counter + 7]; 
			Long longData = ((long) (firstByte & 0xFF) << 56) | ((long) (secondByte & 0xFF) << 48) | ((long) (thirdByte & 0xFF) << 40) | ((long) (forthByte & 0xFF) << 32) | ((long) (fifthByte & 0xFF) << 24) | ((long) (sixthByte & 0xFF) << 16) | ((long) (seventhByte & 0xFF) << 8) | ((long) (eighthByte & 0xFF) << 0);
			result[translatableSize - counter] = Double.longBitsToDouble(longData);
			counter--;
		}
		return result;
	}

}
