package net.minebit.networking.communication.requests;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import net.minebit.networking.converting.ConversionHandler;
import net.minebit.networking.exceptions.communication.requests.RequestException;
import net.minebit.networking.exceptions.conversions.ConversionException;

/**
 * This class represents a set request that can be sent over the network from a
 * client to a server
 * 
 * @author Aggelowe
 * @since 0.1
 */
public class SetRequest extends AbstractRequest {

	private String value;
	private Object data;

	/**
	 * This constructor constructs a new {@link SetRequest}
	 */
	public SetRequest() {
	}

	/**
	 * This method loads the data of the set request from the given map.
	 * 
	 * @param input The map to load the data from
	 * @throws RequestException If an error occurs while the data are being loaded
	 */
	@Override
	public void load(Map<String, Object> input) throws RequestException {
		if (input == null) {
			throw new RequestException("The given map cannot be NULL!");
		}
		if (input.containsKey("value")) {
			Object value = input.get("value");
			if (value instanceof String) {
				this.value = (String) value;
			} else {
				throw new RequestException("The value 'value' contained in the given map is not an instance of 'String'!");
			}
		} else {
			throw new RequestException("The given map doesn't contain the 'value' key!");
		}
		if (input.containsKey("data")) {
			Object data = input.get("data");
			if (data == null) {
				throw new RequestException("The value 'data' contained in the given map cannot be NULL!");
			}
			this.data = data;

		} else {
			throw new RequestException("The given map doesn't contain the 'data' key!");
		}
	}

	/**
	 * This method loads the data of the set request from the given bytes.
	 * 
	 * @param input The map to load the data from
	 * @throws RequestException If an error occurs while the data are being loaded
	 */
	@Override
	public void load(byte[] input) throws RequestException {
		if (input == null) {
			throw new RequestException("The given byte array cannot be NULL!");
		}
		ByteBuffer buffer = ByteBuffer.wrap(input);
		byte[] valueLengthBytes = new byte[4];
		byte[] dataClassLengthBytes = new byte[4];
		byte[] dataLengthBytes = new byte[4];
		buffer.get(valueLengthBytes);
		buffer.get(dataClassLengthBytes);
		buffer.get(dataLengthBytes);
		int valueLenth;
		int dataClassLenth;
		int dataLength;
		try {
			valueLenth = (int) ConversionHandler.toObject(valueLengthBytes, Integer.class);
			dataClassLenth = (int) ConversionHandler.toObject(dataClassLengthBytes, Integer.class);
			dataLength = (int) ConversionHandler.toObject(dataLengthBytes, Integer.class);
		} catch (ConversionException exception) {
			throw new RequestException("An error occured while converting the request headers!", exception);
		}
		byte[] valueBytes = new byte[valueLenth];
		byte[] dataClassBytes = new byte[dataClassLenth];
		byte[] dataBytes = new byte[dataLength];
		buffer.get(valueBytes);
		buffer.get(dataClassBytes);
		buffer.get(dataBytes);
		String value;
		Object data;
		try {
			value = (String) ConversionHandler.toObject(valueBytes, String.class);
			String dataClassName = (String) ConversionHandler.toObject(dataClassBytes, String.class);
			Class<?> dataClass = null;
			try {
				dataClass = Class.forName(dataClassName);
			} catch (ClassNotFoundException exception) {
				throw new RequestException("An error occured while searching for the given class!", exception);
			}
			data = ConversionHandler.toObject(dataBytes, dataClass);
		} catch (ConversionException exception) {
			throw new RequestException("An error occured while converting the request's data!", exception);
		}
		this.value = value;
		this.data = data;
	}

	/**
	 * This method loads the data of the set request from the bytes.
	 * 
	 * @param input The map to load the data from
	 * @throws RequestException If an error occurs while the data are being loaded
	 */
	@Override
	public Map<String, Object> asMap() throws RequestException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("value", this.value);
		result.put("data", this.data);
		return result;
	}

	/**
	 * This method returns this set requests data as a map.
	 * 
	 * @return The requests data
	 * @throws RequestException If an error occurs while getting the data.
	 */
	@Override
	public byte[] asBytes() throws RequestException {
		String dataClassName = this.data.getClass().getName();
		byte[] valueBytes = null;
		byte[] valueLengthBytes = null;
		byte[] dataClassBytes = null;
		byte[] dataClassLengthBytes = null;
		byte[] dataBytes = null;
		byte[] dataLengthBytes = null;
		try {
			valueBytes = ConversionHandler.toBytes(this.value);
			valueLengthBytes = ConversionHandler.toBytes(valueBytes.length);
			dataClassBytes = ConversionHandler.toBytes(dataClassName);
			dataClassLengthBytes = ConversionHandler.toBytes(dataClassBytes.length);
			dataBytes = ConversionHandler.toBytes(this.data);
			dataLengthBytes = ConversionHandler.toBytes(dataBytes.length);
		} catch (ConversionException exception) {
			throw new RequestException("An error occured while converting the request data!", exception);
		}
		int totalLength = 12 + valueBytes.length + dataClassBytes.length + dataBytes.length;
		ByteBuffer buffer = ByteBuffer.allocate(totalLength);
		buffer.put(valueLengthBytes);
		buffer.put(dataClassLengthBytes);
		buffer.put(dataLengthBytes);
		buffer.put(valueBytes);
		buffer.put(dataClassBytes);
		buffer.put(dataBytes);
		return buffer.array();
	}

	/**
	 * Returns the name of the value to set
	 * 
	 * @return The name of the wanted value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Returns the value to set
	 * 
	 * @return The wanted value
	 */
	public Object getData() {
		return this.data;
	}

}
