package net.minebit.networking.conversations.responses;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import net.minebit.networking.converting.ConversionHandler;
import net.minebit.networking.exceptions.conversations.ResponseException;
import net.minebit.networking.exceptions.conversations.SendableException;
import net.minebit.networking.exceptions.conversions.ConversionException;

/**
 * This class represents a data response that can be sent over the network from
 * a server to a client.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class DataResponse extends AbstractResponse {

	private Object data;

	/**
	 * This constructor constructs a new {@link DataResponse}
	 */
	public DataResponse() {
	}

	/**
	 * This method loads the data of the data response from the given map.
	 * 
	 * @param input The map to load the data from
	 * @throws SendableException If an error occurs while the data are being loaded
	 */
	@Override
	public void load(Map<String, Object> input) throws ResponseException {
		if (input == null) {
			throw new ResponseException("The given map cannot be NULL!");
		}
		if (input.containsKey("data")) {
			Object data = input.get("data");
			if (data == null) {
				throw new ResponseException("The value 'data' contained in the given map cannot be NULL!");
			}
			this.data = data;

		} else {
			throw new ResponseException("The given map doesn't contain the 'data' key!");
		}
	}

	/**
	 * This method loads the data of the data response from the given bytes.
	 * 
	 * @param input The map to load the data from
	 * @throws SendableException If an error occurs while the data are being loaded
	 */
	@Override
	public void load(byte[] input) throws ResponseException {
		if (input == null) {
			throw new ResponseException("The given byte array cannot be NULL!");
		}
		ByteBuffer buffer = ByteBuffer.wrap(input);
		byte[] dataClassLengthBytes = new byte[4];
		byte[] dataLengthBytes = new byte[4];
		buffer.get(dataClassLengthBytes);
		buffer.get(dataLengthBytes);
		int dataClassLenth;
		int dataLength;
		try {
			dataClassLenth = (int) ConversionHandler.toObject(dataClassLengthBytes, Integer.class);
			dataLength = (int) ConversionHandler.toObject(dataLengthBytes, Integer.class);
		} catch (ConversionException exception) {
			throw new ResponseException("An error occured while converting the response headers!", exception);
		}
		byte[] dataClassBytes = new byte[dataClassLenth];
		byte[] dataBytes = new byte[dataLength];
		buffer.get(dataClassBytes);
		buffer.get(dataBytes);
		Object data;
		try {
			String dataClassName = (String) ConversionHandler.toObject(dataClassBytes, String.class);
			Class<?> dataClass = null;
			try {
				dataClass = Class.forName(dataClassName);
			} catch (ClassNotFoundException exception) {
				throw new ResponseException("An error occured while searching for the given class!", exception);
			}
			data = ConversionHandler.toObject(dataBytes, dataClass);
		} catch (ConversionException exception) {
			throw new ResponseException("An error occured while converting the response's data!", exception);
		}
		this.data = data;
	}

	/**
	 * This method returns this data response's data as a map.
	 * 
	 * @return The responses data
	 * @throws SendableException If an error occurs while getting the data
	 */
	@Override
	public Map<String, Object> asMap() throws ResponseException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", this.data);
		return result;
	}

	/**
	 * This method returns this data response's data as a byte array.
	 * 
	 * @return The responses data
	 * @throws SendableException If an error occurs while getting the data
	 */
	@Override
	public byte[] asBytes() throws ResponseException {
		String dataClassName = this.data.getClass().getName();
		byte[] dataClassBytes = null;
		byte[] dataClassLengthBytes = null;
		byte[] dataBytes = null;
		byte[] dataLengthBytes = null;
		try {
			dataClassBytes = ConversionHandler.toBytes(dataClassName);
			dataClassLengthBytes = ConversionHandler.toBytes(dataClassBytes.length);
			dataBytes = ConversionHandler.toBytes(this.data);
			dataLengthBytes = ConversionHandler.toBytes(dataBytes.length);
		} catch (ConversionException exception) {
			throw new ResponseException("An error occured while converting the response data!", exception);
		}
		int totalLength = 8 + dataClassBytes.length + dataBytes.length;
		ByteBuffer buffer = ByteBuffer.allocate(totalLength);
		buffer.put(dataClassLengthBytes);
		buffer.put(dataLengthBytes);
		buffer.put(dataClassBytes);
		buffer.put(dataBytes);
		return buffer.array();
	}

	/**
	 * Returns the requested value
	 * 
	 * @return The requested value
	 */
	public Object getData() {
		return this.data;
	}

}
