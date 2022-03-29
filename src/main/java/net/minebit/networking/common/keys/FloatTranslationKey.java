package net.minebit.networking.common.keys;

import net.minebit.networking.exceptions.InvalidDataException;

/**
 * This class is an {@link ITranslationKey} that defines how float values can be
 * translated to byte arrays and vice versa.
 * 
 * @author Aggelowe
 *
 */
public class FloatTranslationKey implements ITranslationKey<Float> {

	public static final FloatTranslationKey INSTANCE = new FloatTranslationKey();

	/**
	 * This constructor constructs a new {@link FloatTranslationKey} that translates
	 * a float value from and to a byte array.
	 */
	private FloatTranslationKey() {
	}

	/**
	 * This method translates the given float value to a byte array.
	 * 
	 * @param The float value to be translated.
	 * @return The translated bytes.
	 */
	@Override
	public byte[] translateToBytes(Float input) {
		final int origin = Float.floatToIntBits(input);
		return new byte[] { (byte) (origin >> 24), (byte) (origin >> 16), (byte) (origin >> 8), (byte) origin };
	}

	/**
	 * This method translates the given byte array to a float value.
	 * 
	 * @param The byte array to be translated.
	 * @return The translated float value.
	 */
	@Override
	public Float translateFromBytes(byte... input) {
		if (input.length < 4) {
			throw new InvalidDataException("The given bytes are not enough to decode to a float!");
		}
		Integer intData = ((input[input.length - 4] & 0xFF) << 24) | ((input[input.length - 3] & 0xFF) << 16) | ((input[input.length - 2] & 0xFF) << 8) | ((input[input.length - 1] & 0xFF) << 0);
		return Float.intBitsToFloat(intData);
	}

}
