package net.minebit.networking.util.converters.primitives;

import java.util.Optional;

import net.minebit.networking.util.ByteUtils;
import net.minebit.networking.util.converters.IConverter;

/**
 * This class contains the means to convert single short values into byte arrays
 * by getting the short's primitive data and vice versa to convert byte arrays
 * into single short values.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class ShortConverter implements IConverter<Short> {

	/**
	 * The only instance of the {@link ShortConverter} object.
	 */
	public static final ShortConverter INSTANCE = new ShortConverter();

	/**
	 * This constructor constructs a new {@link ShortConverter} that to converts
	 * single short values into byte arrays and vice versa.
	 */
	private ShortConverter() {
	}

	/**
	 * This method converts the given short value and returns the resultant byte
	 * array in an {@link Optional}. If the given object is NULL then an empty
	 * optional will be returned.
	 */
	@Override
	public Optional<byte[]> sourceToBytes(Short input) {
		if (input == null) {
			return Optional.empty();
		}
		byte[] result = new byte[] { (byte) (input >> 8), (byte) input.shortValue() };
		return Optional.of(result);
	}

	/**
	 * This method converts the given byte array and returns the resultant short
	 * value in an {@link Optional}. If the given byte array is NULL then an empty
	 * optional will be returned. If the given byte array is empty then an optional
	 * containing a zero will be returned.
	 */
	@Override
	public Optional<Short> bytesToSource(byte[] input) {
		if (input == null) {
			return Optional.empty();
		}
		byte[] clone = ByteUtils.clone(input).orElse(new byte[2]);
		if (clone.length < 2) {
			clone = ByteUtils.enlarge(clone, 2 - clone.length, 0).orElse(new byte[2]);
		}
		short result = (short) ((clone[clone.length - 2] & 0xFF) << 8 | (clone[clone.length - 1] & 0xFF));
		return Optional.of(result);
	}

}
