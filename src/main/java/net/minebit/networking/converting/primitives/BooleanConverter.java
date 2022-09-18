package net.minebit.networking.converting.primitives;

import net.minebit.networking.converting.IConverter;
import net.minebit.networking.exceptions.conversions.ConversionException;
import net.minebit.networking.exceptions.general.ByteException;
import net.minebit.networking.miscellaneous.ByteUtils;

/**
 * This class handles the conversion of booleans to byte arrays and vice versa.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class BooleanConverter implements IConverter<Boolean> {

	private static final BooleanConverter INSTANCE = new BooleanConverter();

	/**
	 * This constructor constructs a new {@link IConverter} used to convert
	 * {@link Boolean} objects.
	 */
	private BooleanConverter() {
	}

	public static BooleanConverter getInstance() {
		return BooleanConverter.INSTANCE;
	}

	@Override
	public byte[] toBytes(Boolean input) throws ConversionException {
		return new byte[] { (byte) (input ? 1 : 0) };
	}

	@Override
	public Boolean toObject(byte[] input) throws ConversionException {
		byte[] bytes;
		try {
			bytes = ByteUtils.resize(input, 1);
		} catch (ByteException exception) {
			throw new ConversionException("An error occured while resizing the given byte array!", exception);
		}
		byte data = bytes[0];
		switch (data & 0x1) {
			case 1:
				return true;
			default:
				return false;
		}
	}

}
