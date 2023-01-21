package net.minebit.networking.util.converters;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * This class contains the means to convert {@link String} objects into byte
 * arrays by utilizing java's build in {@link String} methods and vice versa to
 * convert byte arrays into {@link String}s.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class StringConverter implements IConverter<String> {

	/**
	 * The only instance of the {@link StringConverter} object.
	 */
	public static final StringConverter INSTANCE = new StringConverter();

	/**
	 * This constructor constructs a new {@link StringConverter} that utilizes
	 * java's built in {@link String} methods to convert {@link String} objects into
	 * byte arrays and vice versa.
	 */
	private StringConverter() {
	}

	/**
	 * This method converts the given {@link String} object and returns the
	 * resultant byte array in an {@link Optional}. If the given object is NULL then
	 * an empty optional will be returned.
	 */
	@Override
	public Optional<byte[]> sourceToBytes(String input) {
		return input == null ? Optional.empty() : Optional.of(input.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * This method converts the given byte array and returns the resultant
	 * {@link String} in an {@link Optional}. If the given byte array is NULL then
	 * an empty optional will be returned.
	 */
	@Override
	public Optional<String> bytesToSource(byte[] input) {
		return input == null ? Optional.empty() : Optional.of(new String(input, StandardCharsets.UTF_8));
	}

}
