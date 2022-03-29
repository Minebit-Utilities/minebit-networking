package net.minebit.networking.common.keys;

import java.security.InvalidParameterException;

/**
 * This class is an {@link ITranslationKey} that defines how boolean arrays can
 * be translated to byte arrays and vice versa.
 * 
 * @author Aggelowe
 *
 */
public class BooleanArrayTranslationKey implements ITranslationKey<Boolean[]> {

	public static final BooleanArrayTranslationKey INSTANCE = new BooleanArrayTranslationKey();

	/**
	 * This constructor constructs a new {@link BooleanArrayTranslationKey} that
	 * translates a boolean array from and to a byte array.
	 */
	private BooleanArrayTranslationKey() {
	}

	/**
	 * This method translates the given boolean array to a byte array.
	 * 
	 * @param The boolean array to be translated.
	 * @return The translated bytes.
	 */
	@Override
	public byte[] translateToBytes(Boolean[] input) {
		byte[] result = new byte[input.length];
		int counter = 0;
		while (counter < input.length) {
			result[counter] = (byte) (input[counter] == true ? 1 : 0);
			counter++;
		}
		return result;
	}

	/**
	 * This method translates the given byte array to a boolean array.
	 * 
	 * @param The byte array to be translated.
	 * @return The translated boolean array.
	 */
	@Override
	public Boolean[] translateFromBytes(byte... input) {
		Boolean[] result = new Boolean[input.length];
		int counter = input.length;
		while (counter > 0) {
			boolean value = false;
			switch (input[input.length - counter]) {
			case 0:
				break;
			case 1:
				value = true;
				break;
			default: 
				throw new InvalidParameterException("The input byte must either be 1 or 0!");
			}
			result[input.length - counter] = value;
			counter--;
		}
		return result;
	}

}
