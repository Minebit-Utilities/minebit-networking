package net.minebit.networking.wrappers;

import java.nio.ByteBuffer;

import net.minebit.networking.converting.primitives.BooleanConverter;
import net.minebit.networking.exceptions.conversions.ConversionException;
import net.minebit.networking.exceptions.general.ByteException;
import net.minebit.networking.exceptions.wrappers.WrapperException;
import net.minebit.networking.miscellaneous.ByteUtils;

/**
 * This class represents a wrapper used to compress and uncompress byte arrays
 * using {@link ByteUtils}
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class CompressionWrapper implements IWrapper {

	private static final CompressionWrapper INSTANCE = new CompressionWrapper();

	/**
	 * This constructor constructs a new {@link CompressionWrapper}
	 */
	private CompressionWrapper() {
	}

	/**
	 * This method compresses the given byte array using GZip.
	 */
	@Override
	public byte[] wrap(byte[] bytes) throws WrapperException {
		if (bytes == null) {
			throw new WrapperException("The given byte array cannot be null!");
		}
		boolean isCompressed = false;
		byte[] output = bytes;
		byte[] compressed;
		try {
			compressed = ByteUtils.compress(bytes, 1024);
		} catch (ByteException exception) {
			throw new WrapperException("An error occured while compressing the given byte array!", exception);
		}
		if (compressed.length < bytes.length) {
			output = compressed;
			isCompressed = true;
		}
		byte[] compressedBytes;
		try {
			compressedBytes = BooleanConverter.getInstance().toBytes(isCompressed);
		} catch (ConversionException exception) {
			throw new WrapperException("An error occured while converting the compression byte!", exception);
		}
		ByteBuffer buffer = ByteBuffer.allocate(output.length + 1);
		buffer.put(compressedBytes);
		buffer.put(output);
		return buffer.array();
	}

	/**
	 * This method decompressed the given byte array using GZip
	 */
	@Override
	public byte[] unwrap(byte[] bytes) throws WrapperException {
		if (bytes == null) {
			throw new WrapperException("The given byte array cannot be null!");
		}
		if (bytes.length < 1) {
			throw new WrapperException("The given byte array's length cannot be smaller than 1!");
		}
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		byte[] compressionBytes = new byte[1];
		byte[] inputBytes = new byte[bytes.length - 1];
		buffer.get(compressionBytes);
		buffer.get(inputBytes);
		boolean isCompressed;
		try {
			isCompressed = BooleanConverter.getInstance().toObject(compressionBytes);
		} catch (ConversionException exception) {
			throw new WrapperException("An error occured while converting the compression byte!", exception);
		}
		byte[] result = inputBytes;
		if (isCompressed) {
			try {
				result = ByteUtils.decompress(inputBytes, 1024);
			} catch (ByteException exception) {
				throw new WrapperException("An error occured while compressing the given byte array!", exception);
			}
		}
		return result;
	}

	/**
	 * This method returns the only instance of {@link CompressionWrapper}
	 * 
	 * @return The class's instance
	 */
	public static CompressionWrapper getInstance() {
		return CompressionWrapper.INSTANCE;
	}

}
