package net.minebit.networking.common.keys;

import net.minebit.networking.exceptions.DataTooLargeException;

/**
 * This class is an {@link ITranslationKey} that defines how float arrays can be
 * translated to byte arrays and vice versa.
 * 
 * @author Aggelowe
 *
 */
public class FloatArrayTranslationKey implements ITranslationKey<Float[]> {

	public static final FloatArrayTranslationKey INSTANCE = new FloatArrayTranslationKey();

	/**
	 * This constructor constructs a new {@link FloatArrayTranslationKey} that
	 * translates a float array from and to a byte array.
	 */
	private FloatArrayTranslationKey() {
	}

	/**
	 * This method translates the given float array to a byte array.
	 * 
	 * @param The float array to be translated.
	 * @return The translated bytes.
	 */
	@Override
	public byte[] translateToBytes(Float[] input) {
		if (input.length > 536870912) {
			throw new DataTooLargeException("The given float array must not contain more than 536870912 values.");
		}
		byte[] result = new byte[input.length * 4];
		int counter = 0;
		while (counter < input.length) {
			final int origin = Float.floatToIntBits(input[counter]);
			result[4 * counter] = (byte) (origin >> 24);
			result[4 * counter + 1] = (byte) (origin >> 16);
			result[4 * counter + 2] = (byte) (origin >> 8);
			result[4 * counter + 3] = (byte) origin;
			counter++;
		}
		return result;
	}

	/**
	 * This method translates the given byte array to a float array.
	 * 
	 * @param The byte array to be translated.
	 * @return The translated float array.
	 */
	@Override
	public Float[] translateFromBytes(byte... input) {
		final int translatableSize = (input.length - (input.length % 4)) / 4;
		Float[] result = new Float[translatableSize];
		int counter = translatableSize;
		while (counter > 0) {
			byte firstByte = input[input.length - 4 * counter];
			byte secondByte = input[input.length - 4 * counter + 1];
			byte thirdByte = input[input.length - 4 * counter + 2];
			byte forthByte = input[input.length - 4 * counter + 3];
			Integer intData = ((firstByte & 0xFF) << 24) | ((secondByte & 0xFF) << 16) | ((thirdByte & 0xFF) << 8) | ((forthByte & 0xFF) << 0);
			result[translatableSize - counter] = Float.intBitsToFloat(intData);
			counter--;
		}
		return result;
	}

}
