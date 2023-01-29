package net.minebit.networking.wrappers;

import java.io.ByteArrayOutputStream;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * This class contains the means to compress the given byte arrays and vice
 * versa to decompress the given byte arrays.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class CompressionWrapper implements IWrapper {

	/**
	 * The unique id of the wrapper's container
	 */
	public static final byte ID = 0x00;
	
	/**
	 * The only instance of the {@link CompressionWrapper} object.
	 */
	public static final CompressionWrapper INSTANCE = new CompressionWrapper();

	/**
	 * The size of the buffer used in compression and decompression of the byte
	 * arrays.
	 */
	private int bufferSize = 1024;

	/**
	 * This constructor constructs a new {@link CompressionWrapper} that to
	 * compresses and decompresses byte arrays.
	 */
	private CompressionWrapper() {
	}

	/**
	 * This method compresses the given byte array using the ZLib algorithm and
	 * returns the result in an {@link Optional}. If the given array is NULL or an
	 * error occurs while compressing it then an empty {@link Optional} will be
	 * returned.
	 */
	@Override
	public Optional<byte[]> wrap(byte[] bytes) {
		if (bytes == null) {
			return Optional.empty();
		}
		Deflater deflater = new Deflater(Deflater.BEST_SPEED);
		deflater.setInput(bytes);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] compressionBuffer = new byte[this.bufferSize];
		while (!deflater.finished()) {
			int size = deflater.deflate(compressionBuffer);
			if (size > 0) {
				outputStream.write(compressionBuffer, 0, size);
			}
		}
		deflater.end();
		byte[] result = outputStream.toByteArray();
		return Optional.of(result);
	}

	/**
	 * This method decompresses the given byte array using the ZLib algorithm and
	 * returns the result in an {@link Optional}. If the given array is NULL or an
	 * error occurs while decompressing it then an empty {@link Optional} will be
	 * returned.
	 */
	@Override
	public Optional<byte[]> unwrap(byte[] bytes) {
		if (bytes == null) {
			return Optional.empty();
		}
		Inflater inflater = new Inflater();
		inflater.setInput(bytes);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] decompressionBuffer = new byte[this.bufferSize];
		while (!inflater.finished()) {
			int remaining;
			try {
				remaining = inflater.inflate(decompressionBuffer);
			} catch (DataFormatException exception) {
				return Optional.empty();
			}
			if (remaining > 0) {
				outputStream.write(decompressionBuffer, 0, remaining);
			}
		}
		inflater.end();
		byte[] result = outputStream.toByteArray();
		return Optional.of(result);
	}

	/**
	 * This method sets the size of the buffer used for compressing and
	 * decompressing the given byte arrays.
	 * 
	 * @param bufferSize The new buffer size
	 * @return Whether the operation was successful.
	 */
	public boolean setBufferSize(int bufferSize) {
		if (bufferSize <= 0) {
			return false;
		}
		this.bufferSize = bufferSize;
		return true;
	}

}
