package net.minebit.networking.common.keys;

import net.minebit.networking.exceptions.InvalidDataException;

/**
 * This class is an {@link ITranslationKey} that defines how boolean values can
 * be translated to byte arrays and vice versa.
 * 
 * @author Aggelowe
 *
 */
public class BooleanTranslationKey implements ITranslationKey<Boolean> {

	public static final BooleanTranslationKey INSTANCE = new BooleanTranslationKey();

	/**
	 * This constructor constructs a new {@link BooleanTranslationKey} that
	 * translates a boolean value from and to a byte array.
	 */
	private BooleanTranslationKey() {
	}

	/**
	 * This method translates the given boolean value to a byte array.
	 * 
	 * @param The boolean value to be translated.
	 * @return The translated bytes.
	 */
	@Override
	public byte[] translateToBytes(Boolean input) {
		return new byte[] { (byte) (input == true ? 1 : 0) };
	}

	/**
	 * This method translates the given byte array to a boolean value.
	 * 
	 * @param The byte array to be translated.
	 * @return The translated boolean value.
	 */
	@Override
	public Boolean translateFromBytes(byte... input) {
		if (input.length < 1) {
			throw new InvalidDataException("The given bytes are not enough to decode to a boolean!");
		}
		switch (input[input.length - 1]) {
		case 1:
			return true;
		default:
			return false;
		}
	}

}
