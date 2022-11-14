package net.minebit.networking.conversions.primitives;

import net.minebit.networking.conversions.IConverter;
import net.minebit.networking.exceptions.ByteException;
import net.minebit.networking.exceptions.ConversionException;
import net.minebit.networking.miscellaneous.ByteUtils;

/**
 * This class handles the conversion of bytes to byte arrays and vice versa.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class ByteConverter implements IConverter<Byte> {

	private static final ByteConverter INSTANCE = new ByteConverter();
	
	/**
	 * This constructor constructs a new {@link IConverter} used to convert
	 * {@link Byte} objects.
	 */
	private ByteConverter() {
	}

	public static ByteConverter getInstance() {
		return ByteConverter.INSTANCE;
	}
	
	@Override
	public byte[] toBytes(Byte input) throws ConversionException {
		return new byte[] { input };
	}

	@Override
	public Byte toObject(byte[] input) throws ConversionException {
		byte[] bytes;
		try {
			bytes = ByteUtils.resize(input, 1);
		} catch (ByteException exception) {
			throw new ConversionException("An error occured while resizing the given byte array!", exception);
		}
		return (byte) (bytes[0] & 0xFF);
	}

}
