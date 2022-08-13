package net.minebit.networking.communication.requests;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import net.minebit.networking.converting.ConversionHandler;
import net.minebit.networking.exceptions.communication.SendableException;
import net.minebit.networking.exceptions.communication.requests.RequestException;
import net.minebit.networking.exceptions.conversions.ConversionException;

/**
 * This class represents a get request that can be sent over the network from a
 * client to a server.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class GetRequest extends AbstractRequest {

	private String value;

	/**
	 * This constructor constructs a new {@link GetRequest}
	 * 
	 * @param conversationId The conversation id of the request
	 */
	public GetRequest(long conversationId) {
		super(conversationId);
	}

	/**
	 * This method loads the data of the get request from the given map.
	 * 
	 * @param input The map to load the data from
	 * @throws SendableException If an error occurs while the data are being loaded
	 */
	@Override
	public void load(Map<String, Object> input) throws SendableException {
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
	}

	/**
	 * This method loads the data of the get request from the bytes.
	 * 
	 * @param input The map to load the data from
	 * @throws SendableException If an error occurs while the data are being loaded
	 */
	@Override
	public void load(byte[] input) throws SendableException {
		if (input == null) {
			throw new RequestException("The given byte array cannot be NULL!");
		}
		String value;
		try {
			value = (String) ConversionHandler.toObject(input, String.class);
		} catch (ConversionException exception) {
			throw new RequestException("An error occured while converting the 'value' into bytes!");
		}
		this.value = value;
	}

	/**
	 * This method returns this get requests data as a map.
	 * 
	 * @return The requests data
	 * @throws SendableException If an error occurs while getting the data.
	 */
	@Override
	public Map<String, Object> asMap() throws SendableException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("value", this.value);
		return result;
	}

	/**
	 * This method returns this get requests data as a byte array.
	 * 
	 * @return The request's data
	 * @throws SendableException If an error occurs while getting the data.
	 */
	@Override
	public byte[] asBytes() throws SendableException {
		byte[] valueBytes = null;
		try {
			valueBytes = ConversionHandler.toBytes(this.value);
		} catch (ConversionException exception) {
			throw new RequestException("An error occured while converting the 'value' string into bytes!", exception);
		}
		ByteBuffer buffer = ByteBuffer.allocate(valueBytes.length);
		buffer.put(valueBytes);
		return buffer.array();
	}

	/**
	 * Returns the name of the value to get
	 * 
	 * @return The name of the wanted value
	 */
	public String getValue() {
		return value;
	}

}
