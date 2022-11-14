package net.minebit.networking.conversions.primitives;

import net.minebit.networking.conversions.IConverter;
import net.minebit.networking.exceptions.ConversionException;

/**
 * This class handles the conversion of floats to byte arrays and vice versa.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class FloatConverter implements IConverter<Float> {

	private static final FloatConverter INSTANCE = new FloatConverter();

	/**
	 * This constructor constructs a new {@link IConverter} used to convert
	 * {@link Float} objects.
	 */
	private FloatConverter() {
	}

	public static FloatConverter getInstance() {
		return FloatConverter.INSTANCE;
	}

	@Override
	public byte[] toBytes(Float input) throws ConversionException {
		int asInteger = Float.floatToIntBits(input);
		return IntegerConverter.getInstance().toBytes(asInteger);
	}

	@Override
	public Float toObject(byte[] input) throws ConversionException {
		int asInteger = IntegerConverter.getInstance().toObject(input);
		return Float.intBitsToFloat(asInteger);
	}

}
