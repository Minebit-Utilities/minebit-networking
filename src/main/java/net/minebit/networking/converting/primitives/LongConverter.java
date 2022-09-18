package net.minebit.networking.converting.primitives;

import net.minebit.networking.converting.IConverter;
import net.minebit.networking.exceptions.conversions.ConversionException;
import net.minebit.networking.exceptions.general.ByteException;
import net.minebit.networking.miscellaneous.ByteUtils;

/**
 * This class handles the conversion of longs to byte arrays and vice versa.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class LongConverter implements IConverter<Long> {

	private static final LongConverter INSTANCE = new LongConverter();

	/**
	 * This constructor constructs a new {@link IConverter} used to convert
	 * {@link Long} objects.
	 */
	private LongConverter() {
	}

	public static LongConverter getInstance() {
		return LongConverter.INSTANCE;
	}

	@Override
	public byte[] toBytes(Long input) throws ConversionException {
		long primitive = input.longValue();
		return new byte[] { (byte) (primitive >> 56), (byte) (primitive >> 48), (byte) (primitive >> 40), (byte) (primitive >> 32), (byte) (primitive >> 24), (byte) (primitive >> 16), (byte) (primitive >> 8), (byte) primitive };
	}

	@Override
	public Long toObject(byte[] input) throws ConversionException {
		byte[] bytes;
		try {
			bytes = ByteUtils.resize(input, 8);
		} catch (ByteException exception) {
			throw new ConversionException("An error occured while resizing the given byte array!", exception);
		}
		return ((long) (bytes[0] & 0xFF) << 56) | ((long) (bytes[1] & 0xFF) << 48) | ((long) (bytes[2] & 0xFF) << 40) | ((long) (bytes[3] & 0xFF) << 32) | ((long) (bytes[4] & 0xFF) << 24) | ((long) (bytes[5] & 0xFF) << 16) | ((long) (bytes[6] & 0xFF) << 8) | ((long) bytes[7] & 0xFF);
	}

}
