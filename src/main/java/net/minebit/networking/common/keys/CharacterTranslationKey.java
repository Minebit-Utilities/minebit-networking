package net.minebit.networking.common.keys;

import net.minebit.networking.exceptions.InvalidDataException;

/**
 * This class is an {@link ITranslationKey} that defines how char values can be
 * translated to byte arrays and vice versa.
 * 
 * @author Aggelowe
 *
 */
public class CharacterTranslationKey implements ITranslationKey<Character> {

	public static final CharacterTranslationKey INSTANCE = new CharacterTranslationKey();

	/**
	 * This constructor constructs a new {@link CharacterTranslationKey} that
	 * translates a char value from and to a byte array.
	 */
	private CharacterTranslationKey() {
	}

	/**
	 * This method translates the given char value to a byte array.
	 * 
	 * @param The char value to be translated.
	 * @return The translated bytes.
	 */
	@Override
	public byte[] translateToBytes(Character input) {
		final char origin = input;
		if ((byte) (origin >> 8) == 0) {
			return new byte[] { (byte) origin };
		}
		return new byte[] { (byte) (origin >> 8), (byte) origin };
	}

	/**
	 * This method translates the given char array to a boolean value.
	 * 
	 * @param The char array to be translated.
	 * @return The translated boolean value.
	 */
	@Override
	public Character translateFromBytes(byte... input) {
		if (input.length < 1) {
			throw new InvalidDataException("The given bytes are not enough to decode to a char!");
		}
		if (input.length >= 2) {
			return (char) (((input[input.length - 2] & 0xFF) << 8) | ((input[input.length - 1] & 0xFF) << 0));
		} else {
			return (char) input[0];
		}
	}

}
