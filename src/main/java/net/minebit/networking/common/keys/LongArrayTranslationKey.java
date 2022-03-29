package net.minebit.networking.common.keys;

import net.minebit.networking.exceptions.DataTooLargeException;

/**
 * This class is an {@link ITranslationKey} that defines how long arrays can be
 * translated to byte arrays and vice versa.
 * 
 * @author Aggelowe
 *
 */
public class LongArrayTranslationKey implements ITranslationKey<Long[]> {

	public static final LongArrayTranslationKey INSTANCE = new LongArrayTranslationKey();

	/**
	 * This constructor constructs a new {@link LongArrayTranslationKey} that
	 * translates a long array from and to a byte array.
	 */
	private LongArrayTranslationKey() {
	}

	/**
	 * This method translates the given long array to a byte array.
	 * 
	 * @param The long array to be translated.
	 * @return The translated bytes.
	 */
	@Override
	public byte[] translateToBytes(Long[] input) {
		if (input.length > 268435456) {
			throw new DataTooLargeException("The given long array must not contain more than 268435456 values.");
		}
		byte[] result = new byte[input.length * 8];
		int counter = 0;
		while (counter < input.length) {
			final long origin = input[counter];
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
	 * This method translates the given byte array to a long array.
	 * 
	 * @param The byte array to be translated.
	 * @return The translated long array.
	 */
	@Override
	public Long[] translateFromBytes(byte... input) {
		final int translatableSize = (input.length - (input.length % 8)) / 8;
		Long[] result = new Long[translatableSize];
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
			result[translatableSize - counter] = ((long) (firstByte & 0xFF) << 56) | ((long) (secondByte & 0xFF) << 48) | ((long) (thirdByte & 0xFF) << 40) | ((long) (forthByte & 0xFF) << 32) | ((long) (fifthByte & 0xFF) << 24) | ((long) (sixthByte & 0xFF) << 16) | ((long) (seventhByte & 0xFF) << 8) | ((long) (eighthByte & 0xFF) << 0);
			counter--;
		}
		return result;
	}

}
