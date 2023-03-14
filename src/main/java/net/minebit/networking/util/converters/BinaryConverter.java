package net.minebit.networking.util.converters;

import java.util.Optional;

import net.minebit.networking.util.converters.primitives.ShortConverter;

/**
 * This class contains the means to convert arrays of boolean values into byte
 * arrays by compiling the output's binary data from the booleans and vice versa
 * to convert byte arrays into boolean arrays.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class BinaryConverter implements IConverter<boolean[]> {

	/**
	 * The unique id of the converter's container
	 */
	public static final byte ID = 0x0A;

	/**
	 * The only instance of the {@link BinaryConverter} object.
	 */
	public static final BinaryConverter INSTANCE = new BinaryConverter();

	/**
	 * This constructor constructs a new {@link ShortConverter} that to converts
	 * boolean arrays into byte arrays and vice versa.
	 */
	private BinaryConverter() {
	}

	@Override
	public Optional<byte[]> sourceToBytes(boolean[] input) {
		if (input == null) {
			return Optional.empty();
		}
		int length = input.length / 8 + ((input.length % 8 == 0) ? 0 : 1);
		byte[] bytes = new byte[length];
		for (int index = 0; index < input.length; index++) {
			byte value = (byte) ((input[index] ? 1 : 0) << 7 - index % 8);
			bytes[index / 8] += value;
		}
		return Optional.of(bytes);
	}

	@Override
	public Optional<boolean[]> bytesToSource(byte[] input) {
		if (input == null) {
			return Optional.empty();
		}
		boolean[] booleans = new boolean[input.length * 8];
		for (int index = 0; index < input.length; index++) {
			for (int position = 0; position < 8; position++) {
				boolean value = (input[index] & (1 << 7 - position)) == 0 ? false : true;
				booleans[index * 8 + position] = value;
			}
		}
		return Optional.of(booleans);
	}

}
