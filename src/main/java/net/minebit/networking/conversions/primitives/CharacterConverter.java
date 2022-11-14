package net.minebit.networking.conversions.primitives;

import net.minebit.networking.conversions.IConverter;
import net.minebit.networking.exceptions.ByteException;
import net.minebit.networking.exceptions.ConversionException;
import net.minebit.networking.miscellaneous.ByteUtils;

/**
 * This class handles the conversion of characters to byte arrays and vice
 * versa.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class CharacterConverter implements IConverter<Character> {

	private static final CharacterConverter INSTANCE = new CharacterConverter();

	/**
	 * This constructor constructs a new {@link IConverter} used to convert
	 * {@link Character} objects.
	 */
	private CharacterConverter() {
	}

	public static CharacterConverter getInstance() {
		return CharacterConverter.INSTANCE;
	}

	@Override
	public byte[] toBytes(Character input) throws ConversionException {
		char primitive = input.charValue();
		return new byte[] { (byte) (primitive >> 8), (byte) primitive };
	}

	@Override
	public Character toObject(byte[] input) throws ConversionException {
		byte[] bytes;
		try {
			bytes = ByteUtils.resize(input, 2);
		} catch (ByteException exception) {
			throw new ConversionException("An error occured while resizing the given byte array!", exception);
		}
		return (char) ((bytes[0] & 0xFF) << 8 | (bytes[1] & 0xFF));
	}

}
