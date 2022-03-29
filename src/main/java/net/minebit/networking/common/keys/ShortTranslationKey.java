package net.minebit.networking.common.keys;

import net.minebit.networking.exceptions.InvalidDataException;

/**
 * This class is an {@link ITranslationKey} that defines how short values can be
 * translated to byte arrays and vice versa.
 * 
 * @author Aggelowe
 *
 */
public class ShortTranslationKey implements ITranslationKey<Short> {

	public static final ShortTranslationKey INSTANCE = new ShortTranslationKey();

	/**
	 * This constructor constructs a new {@link ShortTranslationKey} that translates
	 * a short value from and to a byte array.
	 */
	private ShortTranslationKey() {
	}

	/**
	 * This method translates the given short value to a byte array.
	 * 
	 * @param The short value to be translated.
	 * @return The translated bytes.
	 */
	@Override
	public byte[] translateToBytes(Short input) {
		final short origin = input;
		return new byte[] { (byte) (origin >> 8), (byte) origin };
	}

	/**
	 * This method translates the given byte array to a short value.
	 * 
	 * @param The byte array to be translated.
	 * @return The translated short value.
	 */
	@Override
	public Short translateFromBytes(byte... input) {
		if (input.length < 2) {
			throw new InvalidDataException("The given bytes are not enough to decode to a short!");
		}
		return (short) (((input[input.length - 2] & 0xFF) << 8) | ((input[input.length - 1] & 0xFF) << 0));
	}

}
