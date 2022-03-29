package net.minebit.networking.common.keys;

import net.minebit.networking.exceptions.InvalidDataException;

/**
 * This class is an {@link ITranslationKey} that defines how double values can
 * be translated to byte arrays and vice versa.
 * 
 * @author Aggelowe
 *
 */
public class DoubleTranslationKey implements ITranslationKey<Double> {

	public static final DoubleTranslationKey INSTANCE = new DoubleTranslationKey();

	/**
	 * This constructor constructs a new {@link DoubleTranslationKey} that
	 * translates a double value from and to a byte array.
	 */
	private DoubleTranslationKey() {
	}

	/**
	 * This method translates the given double value to a byte array.
	 * 
	 * @param The double value to be translated.
	 * @return The translated bytes.
	 */
	@Override
	public byte[] translateToBytes(Double input) {
		final long origin = Double.doubleToLongBits(input);
		return new byte[] { (byte) (origin >> 56), (byte) (origin >> 48), (byte) (origin >> 40), (byte) (origin >> 32), (byte) (origin >> 24), (byte) (origin >> 16), (byte) (origin >> 8), (byte) origin };

	}

	/**
	 * This method translates the given byte array to a double value.
	 * 
	 * @param The byte array to be translated.
	 * @return The translated double value.
	 */
	@Override
	public Double translateFromBytes(byte... input) {
		if (input.length < 8) {
			throw new InvalidDataException("The given bytes are not enough to decode to a double!");
		}
		Long longData = ((long) (input[input.length - 8] & 0xFF) << 56) | ((long) (input[input.length - 7] & 0xFF) << 48) | ((long) (input[input.length - 6] & 0xFF) << 40) | ((long) (input[input.length - 5] & 0xFF) << 32) | ((long) (input[input.length - 4] & 0xFF) << 24) | ((long) (input[input.length - 3] & 0xFF) << 16) | ((long) (input[input.length - 2] & 0xFF) << 8) | ((long) (input[input.length - 1] & 0xFF) << 0);
		return Double.longBitsToDouble(longData);
	}

}
