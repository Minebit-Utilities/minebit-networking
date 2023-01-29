package net.minebit.networking.util.converters.primitives;

import java.util.Optional;

import net.minebit.networking.util.ByteUtils;
import net.minebit.networking.util.converters.IConverter;

/**
 * This class contains the means to convert single integer values into byte
 * arrays by getting the integer's primitive data and vice versa to convert byte
 * arrays into single integer values.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class IntegerConverter implements IConverter<Integer> {

	/**
	 * The unique id of the converter's container
	 */
	public static final byte ID = 0x05;
	
	/**
	 * The only instance of the {@link IntegerConverter} object.
	 */
	public static final IntegerConverter INSTANCE = new IntegerConverter();

	/**
	 * This constructor constructs a new {@link IntegerConverter} that to converts
	 * single integer values into byte arrays and vice versa.
	 */
	private IntegerConverter() {
	}

	/**
	 * This method converts the given integer value and returns the resultant byte
	 * array in an {@link Optional}. If the given object is NULL then an empty
	 * optional will be returned.
	 */
	@Override
	public Optional<byte[]> sourceToBytes(Integer input) {
		if (input == null) {
			return Optional.empty();
		}
		byte[] result = new byte[] { (byte) (input >> 24), (byte) (input >> 16), (byte) (input >> 8), (byte) input.intValue() };
		return Optional.of(result);
	}

	/**
	 * This method converts the given byte array and returns the resultant integer
	 * value in an {@link Optional}. If the given byte array is NULL then an empty
	 * optional will be returned. If the given byte array is empty then an optional
	 * containing a zero will be returned.
	 */
	@Override
	public Optional<Integer> bytesToSource(byte[] input) {
		if (input == null) {
			return Optional.empty();
		}
		byte[] clone = ByteUtils.clone(input).orElse(new byte[4]);
		if (clone.length < 4) {
			clone = ByteUtils.enlarge(clone, 4 - clone.length, 0).orElse(new byte[4]);
		}
		int result = (clone[clone.length - 4] & 0xFF) << 24 | (clone[clone.length - 3] & 0xFF) << 16 | (clone[clone.length - 2] & 0xFF) << 8 | (clone[clone.length - 1] & 0xFF);
		return Optional.of(result);
	}

}
