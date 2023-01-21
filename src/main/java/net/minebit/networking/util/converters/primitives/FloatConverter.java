package net.minebit.networking.util.converters.primitives;

import java.util.Optional;

import net.minebit.networking.util.converters.IConverter;

/**
 * This class contains the means to convert single float values into byte arrays
 * by getting the float's primitive data and vice versa to convert byte arrays
 * into single float values.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class FloatConverter implements IConverter<Float> {

	/**
	 * The only instance of the {@link FloatConverter} object.
	 */
	public static final FloatConverter INSTANCE = new FloatConverter();

	/**
	 * This constructor constructs a new {@link FloatConverter} that to converts
	 * single float values into byte arrays and vice versa.
	 */
	private FloatConverter() {
	}

	/**
	 * This method converts the given float value by utilizing the
	 * {@link IntegerConverter}'s methods and returns the resultant byte array in an
	 * {@link Optional}. If the given object is NULL then an empty optional will be
	 * returned.
	 */
	@Override
	public Optional<byte[]> sourceToBytes(Float input) {
		if (input == null) {
			return Optional.empty();
		}
		int changed = Float.floatToIntBits(input);
		return IntegerConverter.INSTANCE.sourceToBytes(changed);
	}

	/**
	 * This method converts the given byte array by utilizing the
	 * {@link IntegerConverter}'s methods and returns the resultant float value in
	 * an {@link Optional}. If the given byte array is NULL then an empty optional
	 * will be returned. If the given byte array is empty then an optional
	 * containing a zero will be returned.
	 */
	@Override
	public Optional<Float> bytesToSource(byte[] input) {
		Optional<Integer> converted = IntegerConverter.INSTANCE.bytesToSource(input);
		if (converted.isPresent()) {
			return Optional.of(Float.intBitsToFloat(converted.get()));
		} else {
			return Optional.empty();
		}
	}

}
