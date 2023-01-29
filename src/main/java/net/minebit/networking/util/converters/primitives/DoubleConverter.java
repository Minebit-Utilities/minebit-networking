package net.minebit.networking.util.converters.primitives;

import java.util.Optional;

import net.minebit.networking.util.converters.IConverter;

/**
 * This class contains the means to convert single double values into byte
 * arrays by getting the double's primitive data and vice versa to convert byte
 * arrays into single double values.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class DoubleConverter implements IConverter<Double> {

	/**
	 * The unique id of the converter's container
	 */
	public static final byte ID = 0x08;
	
	/**
	 * The only instance of the {@link DoubleConverter} object.
	 */
	public static final DoubleConverter INSTANCE = new DoubleConverter();

	/**
	 * This constructor constructs a new {@link DoubleConverter} that to converts
	 * single double values into byte arrays and vice versa.
	 */
	private DoubleConverter() {
	}

	/**
	 * This method converts the given double value by utilizing the
	 * {@link LongConverter}'s methods and returns the resultant byte array in an
	 * {@link Optional}. If the given object is NULL then an empty optional will be
	 * returned.
	 */
	@Override
	public Optional<byte[]> sourceToBytes(Double input) {
		if (input == null) {
			return Optional.empty();
		}
		long changed = Double.doubleToLongBits(input);
		return LongConverter.INSTANCE.sourceToBytes(changed);
	}

	/**
	 * This method converts the given byte array by utilizing the
	 * {@link LongConverter}'s methods and returns the resultant double value in an
	 * {@link Optional}. If the given byte array is NULL then an empty optional will
	 * be returned. If the given byte array is empty then an optional containing a
	 * zero will be returned.
	 */
	@Override
	public Optional<Double> bytesToSource(byte[] input) {
		Optional<Long> converted = LongConverter.INSTANCE.bytesToSource(input);
		if (converted.isPresent()) {
			return Optional.of(Double.longBitsToDouble(converted.get()));
		} else {
			return Optional.empty();
		}
	}

}
