package net.minebit.networking.common.keys;

import net.minebit.networking.exceptions.DataTooLargeException;

/**
 * This class is an {@link ITranslationKey} that defines how int arrays can be
 * translated to byte arrays and vice versa.
 * 
 * @author Aggelowe
 *
 */
public class IntegerArrayTranslationKey implements ITranslationKey<Integer[]> {

	public static final IntegerArrayTranslationKey INSTANCE = new IntegerArrayTranslationKey();

	/**
	 * This constructor constructs a new {@link IntegerArrayTranslationKey} that
	 * translates a int array from and to a byte array.
	 */
	private IntegerArrayTranslationKey() {
	}

	/**
	 * This method translates the given int array to a byte array.
	 * 
	 * @param The int array to be translated.
	 * @return The translated bytes.
	 */
	@Override
	public byte[] translateToBytes(Integer[] input) {
		if (input.length > 536870912) {
			throw new DataTooLargeException("The given int array must not contain more than 536870912 values.");
		}
		byte[] result = new byte[input.length * 4];
		int counter = 0;
		while (counter < input.length) {
			final int origin = input[counter];
			result[4 * counter] = (byte) (origin >> 24);
			result[4 * counter + 1] = (byte) (origin >> 16);
			result[4 * counter + 2] = (byte) (origin >> 8);
			result[4 * counter + 3] = (byte) origin;
			counter++;
		}
		return result;
	}

	/**
	 * This method translates the given byte array to a int array.
	 * 
	 * @param The byte array to be translated.
	 * @return The translated int array.
	 */
	@Override
	public Integer[] translateFromBytes(byte... input) {
		final int translatableSize = (input.length - (input.length % 4)) / 4;
		Integer[] result = new Integer[translatableSize];
		int counter = translatableSize;
		while (counter > 0) {
			byte firstByte = input[input.length - 4 * counter];
			byte secondByte = input[input.length - 4 * counter + 1];
			byte thirdByte = input[input.length - 4 * counter + 2];
			byte forthByte = input[input.length - 4 * counter + 3];
			result[translatableSize - counter] = ((firstByte & 0xFF) << 24) | ((secondByte & 0xFF) << 16) | ((thirdByte & 0xFF) << 8) | ((forthByte & 0xFF) << 0);
			counter--;
		}
		return result;

	}

}
