package net.minebit.networking.common.keys;

import net.minebit.networking.exceptions.InvalidDataException;

/**
 * This class is an {@link ITranslationKey} that defines how int values can be
 * translated to byte arrays and vice versa.
 * 
 * @author Aggelowe
 *
 */
public class IntegerTranslationKey implements ITranslationKey<Integer> {

	public static final IntegerTranslationKey INSTANCE = new IntegerTranslationKey();

	/**
	 * This constructor constructs a new {@link IntegerTranslationKey} that translates
	 * a int value from and to a byte array.
	 */
	private IntegerTranslationKey() {
	}

	/**
	 * This method translates the given int value to a byte array.
	 * 
	 * @param The int value to be translated.
	 * @return The translated bytes.
	 */
	@Override
	public byte[] translateToBytes(Integer input) {
		final int origin = input;
		return new byte[] { (byte) (origin >> 24), (byte) (origin >> 16), (byte) (origin >> 8), (byte) origin };
	}

	/**
	 * This method translates the given byte array to a int value.
	 * 
	 * @param The byte array to be translated.
	 * @return The translated int value.
	 */
	@Override
	public Integer translateFromBytes(byte... input) {
		if (input.length < 4) {
			throw new InvalidDataException("The given bytes are not enough to decode to a int!");
		}
		return ((input[input.length - 4] & 0xFF) << 24) | ((input[input.length - 3] & 0xFF) << 16) | ((input[input.length - 2] & 0xFF) << 8) | ((input[input.length - 1] & 0xFF) << 0);
	}

}
