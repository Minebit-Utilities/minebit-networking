package net.minebit.networking.common.keys;

import net.minebit.networking.exceptions.DataTooLargeException;

/**
 * This class is an {@link ITranslationKey} that defines how short arrays can
 * be translated to byte arrays and vice versa.
 * 
 * @author Aggelowe
 *
 */
public class ShortArrayTranslationKey implements ITranslationKey<Short[]> {

	public static final ShortArrayTranslationKey INSTANCE = new ShortArrayTranslationKey();

	/**
	 * This constructor constructs a new {@link ShortArrayTranslationKey} that
	 * translates a short array from and to a byte array.
	 */
	private ShortArrayTranslationKey() {
	}

	/**
	 * This method translates the given short array to a byte array.
	 * 
	 * @param The short array to be translated.
	 * @return The translated bytes.
	 */
	@Override
	public byte[] translateToBytes(Short[] input) {
		if (input.length > 1073741824) {
			throw new DataTooLargeException("The given short array must not contain more than 1073741824 values.");
		}
		byte[] result = new byte[input.length * 2];
		int counter = 0;
		while (counter < input.length) {
			final short origin = input[counter];
			result[2 * counter] = (byte) (origin >> 8);
			result[2 * counter + 1] = (byte) origin;
			counter++;
		}
		return result;
	}

	/**
	 * This method translates the given byte array to a short array.
	 * 
	 * @param The byte array to be translated.
	 * @return The translated short array.
	 */
	@Override
	public Short[] translateFromBytes(byte... input) {
		final int translatableSize = (input.length - (input.length % 2)) / 2;
		Short[] result = new Short[translatableSize];
		int counter = translatableSize;
		while (counter > 0) {
			byte firstByte = input[input.length - 2 * counter];
			byte secondByte = input[input.length - 2 * counter + 1];
			result[translatableSize - counter] = (short) (((firstByte & 0xFF) << 8) | ((secondByte & 0xFF) << 0));
			counter--;
		}
		return result;
	}

}
