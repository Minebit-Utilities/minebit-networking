package net.minebit.networking.conversions.primitives;

import net.minebit.networking.conversions.IConverter;
import net.minebit.networking.exceptions.ByteException;
import net.minebit.networking.exceptions.ConversionException;
import net.minebit.networking.miscellaneous.ByteUtils;

/**
 * This class handles the conversion of integers to byte arrays and vice versa.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class IntegerConverter implements IConverter<Integer> {

	private static final IntegerConverter INSTANCE = new IntegerConverter();

	/**
	 * This constructor constructs a new {@link IConverter} used to convert
	 * {@link Integer} objects.
	 */
	private IntegerConverter() {
	}

	public static IntegerConverter getInstance() {
		return IntegerConverter.INSTANCE;
	}

	@Override
	public byte[] toBytes(Integer input) throws ConversionException {
		int primitive = input.intValue();
		return new byte[] { (byte) (primitive >> 24), (byte) (primitive >> 16), (byte) (primitive >> 8), (byte) primitive };
	}

	@Override
	public Integer toObject(byte[] input) throws ConversionException {
		byte[] bytes;
		try {
			bytes = ByteUtils.resize(input, 4);
		} catch (ByteException exception) {
			throw new ConversionException("An error occured while resizing the given byte array!", exception);
		}
		return (bytes[0] & 0xFF) << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);
	}

}
