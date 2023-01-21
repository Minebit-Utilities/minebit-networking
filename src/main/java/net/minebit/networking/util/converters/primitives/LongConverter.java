package net.minebit.networking.util.converters.primitives;

import java.util.Optional;

import net.minebit.networking.util.ByteUtils;
import net.minebit.networking.util.converters.IConverter;

/**
 * This class contains the means to convert single long values into byte arrays
 * by getting the long's primitive data and vice versa to convert byte arrays
 * into single long values.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class LongConverter implements IConverter<Long> {

	/**
	 * The only instance of the {@link LongConverter} object.
	 */
	public static final LongConverter INSTANCE = new LongConverter();

	/**
	 * This constructor constructs a new {@link LongConverter} that to converts
	 * single long values into byte arrays and vice versa.
	 */
	private LongConverter() {
	}

	/**
	 * This method converts the given long value and returns the resultant byte
	 * array in an {@link Optional}. If the given object is NULL then an empty
	 * optional will be returned.
	 */
	@Override
	public Optional<byte[]> sourceToBytes(Long input) {
		if (input == null) {
			return Optional.empty();
		}
		byte[] result = new byte[] { (byte) (input >> 56), (byte) (input >> 48), (byte) (input >> 40), (byte) (input >> 32), (byte) (input >> 24), (byte) (input >> 16), (byte) (input >> 8), (byte) input.longValue() };
		return Optional.of(result);
	}

	/**
	 * This method converts the given byte array and returns the resultant long
	 * value in an {@link Optional}. If the given byte array is NULL then an empty
	 * optional will be returned. If the given byte array is empty then an optional
	 * containing a zero will be returned.
	 */
	@Override
	public Optional<Long> bytesToSource(byte[] input) {
		if (input == null) {
			return Optional.empty();
		}
		byte[] clone = ByteUtils.clone(input).orElse(new byte[8]);
		if (clone.length < 8) {
			clone = ByteUtils.enlarge(clone, 8 - clone.length, 0).orElse(new byte[8]);
		}
		long result = ((long) (clone[clone.length - 8] & 0xFF) << 56) | ((long) (clone[clone.length - 7] & 0xFF) << 48) | ((long) (clone[clone.length - 6] & 0xFF) << 40) | ((long) (clone[clone.length - 5] & 0xFF) << 32) | ((long) (clone[clone.length - 4] & 0xFF) << 24) | ((long) (clone[clone.length - 3] & 0xFF) << 16) | ((long) (clone[clone.length - 2] & 0xFF) << 8) | ((long) clone[clone.length - 1] & 0xFF);
		return Optional.of(result);
	}

}
