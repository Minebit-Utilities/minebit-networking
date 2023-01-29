package net.minebit.networking.util.converters.primitives;

import java.util.Optional;

import net.minebit.networking.util.converters.IConverter;

/**
 * This class contains the means to convert single byte values into byte arrays
 * by getting the byte's primitive data and vice versa to convert byte arrays
 * into single byte values.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class ByteConverter implements IConverter<Byte> {

	/**
	 * The unique id of the converter's container
	 */
	public static final byte ID = 0x03;
	
	/**
	 * The only instance of the {@link ByteConverter} object.
	 */
	public static final ByteConverter INSTANCE = new ByteConverter();

	/**
	 * This constructor constructs a new {@link ByteConverter} that to converts
	 * single byte values into byte arrays and vice versa.
	 */
	private ByteConverter() {
	}

	/**
	 * This method converts the given byte value and returns the resultant byte
	 * array in an {@link Optional}. If the given object is NULL then an empty
	 * optional will be returned.
	 */
	@Override
	public Optional<byte[]> sourceToBytes(Byte input) {
		if (input == null) {
			return Optional.empty();
		}
		return Optional.of(new byte[] { input });
	}

	/**
	 * This method converts the given byte array and returns the resultant byte
	 * value in an {@link Optional}. If the given byte array is NULL then an empty
	 * optional will be returned. If the given byte array is empty then an optional
	 * containing a zero will be returned.
	 */
	@Override
	public Optional<Byte> bytesToSource(byte[] input) {
		if (input == null) {
			return Optional.empty();
		}
		if (input.length == 0) {
			return Optional.of((byte) 0);
		}
		return Optional.of(input[input.length - 1]);
	}

}
