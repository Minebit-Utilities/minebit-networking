package net.minebit.networking.util.converters.primitives;

import java.util.Optional;

import net.minebit.networking.util.ByteUtils;
import net.minebit.networking.util.converters.IConverter;

/**
 * This class contains the means to convert single character values into byte
 * arrays by getting the byte's primitive data and vice versa to convert byte
 * arrays into single byte values.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class CharacterConverter implements IConverter<Character> {

	/**
	 * The unique id of the converter's container
	 */
	public static final byte ID = 0x09;
	
	/**
	 * The only instance of the {@link CharacterConverter} object.
	 */
	public static final CharacterConverter INSTANCE = new CharacterConverter();

	/**
	 * This constructor constructs a new {@link CharacterConverter} that to converts
	 * single character values into byte arrays and vice versa.
	 */
	private CharacterConverter() {
	}

	/**
	 * This method converts the given character value and returns the resultant byte
	 * array in an {@link Optional}. If the given object is NULL then an empty
	 * optional will be returned.
	 */
	@Override
	public Optional<byte[]> sourceToBytes(Character input) {
		if (input == null) {
			return Optional.empty();
		}
		byte[] result = new byte[] { (byte) (input >> 8), (byte) input.charValue() };
		return Optional.of(result);
	}

	/**
	 * This method converts the given byte array and returns the resultant character
	 * value in an {@link Optional}. If the given byte array is NULL then an empty
	 * optional will be returned. If the given byte array is empty then an optional
	 * containing a zero will be returned.
	 */
	@Override
	public Optional<Character> bytesToSource(byte[] input) {
		if (input == null) {
			return Optional.empty();
		}
		byte[] clone = ByteUtils.clone(input).orElse(new byte[2]);
		if (clone.length < 2) {
			clone = ByteUtils.enlarge(clone, 2 - clone.length, 0).orElse(new byte[2]);
		}
		char result = (char) ((clone[clone.length - 2] & 0xFF) << 8 | (clone[clone.length - 1] & 0xFF));
		return Optional.of(result);
	}

}
