package net.minebit.networking.common.keys;

import net.minebit.networking.exceptions.InvalidDataException;

/**
 * This class is an {@link ITranslationKey} that defines how byte values can be
 * translated to byte arrays and vice versa.
 * 
 * @author Aggelowe
 *
 */
public class ByteTranslationKey implements ITranslationKey<Byte> {

	public static final ByteTranslationKey INSTANCE = new ByteTranslationKey();

	/**
	 * This constructor constructs a new {@link ByteTranslationKey} that translates
	 * a byte value from and to a byte array.
	 */
	private ByteTranslationKey() {
	}

	/**
	 * This method translates the given byte value to a byte array.
	 * 
	 * @param The byte value to be translated.
	 * @return The translated bytes.
	 */
	@Override
	public byte[] translateToBytes(Byte input) {
		return new byte[] { input };
	}

	/**
	 * This method translates the given byte array to a byte value.
	 * 
	 * @param The byte array to be translated.
	 * @return The translated byte value.
	 */
	@Override
	public Byte translateFromBytes(byte... input) {
		if (input.length < 1) {
			throw new InvalidDataException("The given bytes are not enough to decode to a byte!");
		}
		return input[input.length - 1];
	}

}
