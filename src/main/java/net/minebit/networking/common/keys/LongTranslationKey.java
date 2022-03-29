package net.minebit.networking.common.keys;

import net.minebit.networking.exceptions.InvalidDataException;

/**
 * This class is an {@link ITranslationKey} that defines how long values can be
 * translated to byte arrays and vice versa.
 * 
 * @author Aggelowe
 *
 */
public class LongTranslationKey implements ITranslationKey<Long> {

	public static final LongTranslationKey INSTANCE = new LongTranslationKey();

	/**
	 * This constructor constructs a new {@link LongTranslationKey} that translates
	 * a long value from and to a byte array.
	 */
	private LongTranslationKey() {
	}

	/**
	 * This method translates the given long value to a byte array.
	 * 
	 * @param The long value to be translated.
	 * @return The translated bytes.
	 */
	@Override
	public byte[] translateToBytes(Long input) {
		final long origin = input;
		return new byte[] { (byte) (origin >> 56), (byte) (origin >> 48), (byte) (origin >> 40), (byte) (origin >> 32), (byte) (origin >> 24), (byte) (origin >> 16), (byte) (origin >> 8), (byte) origin };
	}

	/**
	 * This method translates the given byte array to a long value.
	 * 
	 * @param The byte array to be translated.
	 * @return The translated long value.
	 */
	@Override
	public Long translateFromBytes(byte... input) {
		if (input.length < 8) {
			throw new InvalidDataException("The given bytes are not enough to decode to a long!");
		}
		return ((long) (input[input.length - 8] & 0xFF) << 56) | ((long) (input[input.length - 7] & 0xFF) << 48) | ((long) (input[input.length - 6] & 0xFF) << 40) | ((long) (input[input.length - 5] & 0xFF) << 32) | ((long) (input[input.length - 4] & 0xFF) << 24) | ((long) (input[input.length - 3] & 0xFF) << 16) | ((long) (input[input.length - 2] & 0xFF) << 8) | ((long) (input[input.length - 1] & 0xFF) << 0);
	}

}
