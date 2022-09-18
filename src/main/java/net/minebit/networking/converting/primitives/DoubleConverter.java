package net.minebit.networking.converting.primitives;

import net.minebit.networking.converting.IConverter;
import net.minebit.networking.exceptions.conversions.ConversionException;

/**
 * This class handles the conversion of doubles to byte arrays and vice versa.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class DoubleConverter implements IConverter<Double> {

	private static final DoubleConverter INSTANCE = new DoubleConverter();

	/**
	 * This constructor constructs a new {@link IConverter} used to convert
	 * {@link Double} objects.
	 */
	private DoubleConverter() {
	}

	public static DoubleConverter getInstance() {
		return DoubleConverter.INSTANCE;
	}

	@Override
	public byte[] toBytes(Double input) throws ConversionException {
		long asLong = Double.doubleToLongBits(input);
		return LongConverter.getInstance().toBytes(asLong);
	}

	@Override
	public Double toObject(byte[] input) throws ConversionException {
		long asLong = LongConverter.getInstance().toObject(input);
		return Double.longBitsToDouble(asLong);
	}

}
