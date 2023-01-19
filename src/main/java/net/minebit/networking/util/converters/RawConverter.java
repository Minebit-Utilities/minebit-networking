package net.minebit.networking.util.converters;

import java.util.Optional;

/**
 * This class converts the given byte arrays into byte arrays and vice versa by
 * returning the given byte array as a result. <b>Note:</b> This converter
 * doesn't do anything; it exists only to be ably to identify raw data remotely.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public final class RawConverter implements IConverter<byte[]> {

	/**
	 * The only instance of the {@link RawConverter} object.
	 */
	public static final RawConverter INSTANCE = new RawConverter();

	/**
	 * This constructor constructs a new {@link RawConverter} that acts as a
	 * converter that converts of byte arrays to byte arrays and vice versa.
	 */
	private RawConverter() {
	}

	/**
	 * This method returns the given byte array inside an {@link Optional}. If the
	 * given byte array is NULL then an empty {@link Optional} will be returned.
	 */
	@Override
	public Optional<byte[]> sourceToBytes(byte[] input) {
		return input == null ? Optional.empty() : Optional.of(input);
	}

	/**
	 * This method returns the given byte array inside an {@link Optional}. If the
	 * given byte array is NULL then an empty {@link Optional} will be returned.
	 */
	@Override
	public Optional<byte[]> bytesToSource(byte[] input) {
		return input == null ? Optional.empty() : Optional.of(input);
	}

}
