package net.minebit.networking.converting.primitives;

import net.minebit.networking.converting.IConverter;
import net.minebit.networking.exceptions.conversions.ConversionException;
import net.minebit.networking.exceptions.general.ByteException;
import net.minebit.networking.miscellaneous.ByteUtils;

/**
 * This class handles the conversion of shorts to byte arrays and vice versa.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class ShortConverter implements IConverter<Short> {

	private static final ShortConverter INSTANCE = new ShortConverter();

	/**
	 * This constructor constructs a new {@link IConverter} used to convert
	 * {@link Short} objects.
	 */
	private ShortConverter() {
	}

	public static ShortConverter getInstance() {
		return ShortConverter.INSTANCE;
	}

	@Override
	public byte[] toBytes(Short input) throws ConversionException {
		short primitive = input.shortValue();
		return new byte[] { (byte) (primitive >> 8), (byte) primitive };
	}

	@Override
	public Short toObject(byte[] input) throws ConversionException {
		byte[] bytes;
		try {
			bytes = ByteUtils.resize(input, 2);
		} catch (ByteException exception) {
			throw new ConversionException("An error occured while resizing the given byte array!", exception);
		}
		return (short) ((bytes[0] & 0xFF) << 8 | (bytes[1] & 0xFF));
	}

}
