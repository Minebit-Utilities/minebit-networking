package net.minebit.networking.common.keys;

import java.nio.charset.StandardCharsets;

/**
 * This class is an {@link ITranslationKey} that defines how string values can be
 * translated to byte arrays and vice versa.
 * 
 * @author Aggelowe
 *
 */
public class StringTranslationKey implements ITranslationKey<String> {

	public static final StringTranslationKey INSTANCE = new StringTranslationKey();

	/**
	 * This constructor constructs a new {@link StringTranslationKey} that translates
	 * a string value from and to a byte array.
	 */
	private StringTranslationKey() {
	}

	/**
	 * This method translates the given string value to a byte array.
	 * 
	 * @param The string value to be translated.
	 * @return The translated bytes.
	 */
	@Override
	public byte[] translateToBytes(String input) {
		return input.getBytes(StandardCharsets.UTF_8);
	}

	/**
	 * This method translates the given byte array to a string value.
	 * 
	 * @param The byte array to be translated.
	 * @return The translated string value.
	 */
	@Override
	public String translateFromBytes(byte... input) {
		return new String(input, StandardCharsets.UTF_8);
	}

}
