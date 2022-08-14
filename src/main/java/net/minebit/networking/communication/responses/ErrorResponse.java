package net.minebit.networking.communication.responses;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import net.minebit.networking.converting.ConversionHandler;
import net.minebit.networking.exceptions.communication.SendableException;
import net.minebit.networking.exceptions.communication.responses.ResponseException;
import net.minebit.networking.exceptions.conversions.ConversionException;

/**
 * This class represents an error response that can be sent over the network
 * from a server to a client.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class ErrorResponse extends AbstractResponse {

	private short code;
	private String message;

	/**
	 * This constructor constructs a new {@link ErrorResponse}
	 */
	public ErrorResponse() {
	}

	/**
	 * This method loads the data of the error response from the given map.
	 * 
	 * @param input The map to load the data from
	 * @throws SendableException If an error occurs while the data are being loaded
	 */
	@Override
	public void load(Map<String, Object> input) throws ResponseException {
		if (input == null) {
			throw new ResponseException("The given map cannot be NULL!");
		}
		if (input.containsKey("code")) {
			Object code = input.get("code");
			if (code instanceof Short) {
				this.code = (short) code;
			} else {
				throw new ResponseException("The value 'code' contained in the given map is not an instance of 'Short'!");
			}
		} else {
			throw new ResponseException("The given map doesn't contain the 'code' key!");
		}
		if (input.containsKey("message")) {
			Object message = input.get("message");
			if (message instanceof String) {
				this.message = (String) message;
			} else {
				throw new ResponseException("The value 'message' contained in the given map is not an instance of 'Short'!");
			}
		} else {
			throw new ResponseException("The given map doesn't contain the 'message' key!");
		}
	}

	/**
	 * This method loads the data of the error response from the given bytes.
	 * 
	 * @param input The map to load the data from
	 * @throws SendableException If an error occurs while the data are being loaded
	 */
	@Override
	public void load(byte[] input) throws ResponseException {
		if (input == null) {
			throw new ResponseException("The given byte array cannot be NULL!");
		}
		if (input.length < 2) {
			throw new ResponseException("The length of the given byte array cannot be smaller than 2!");
		}
		ByteBuffer buffer = ByteBuffer.wrap(input);
		byte[] codeBytes = new byte[2];
		byte[] messageBytes = new byte[input.length - 2];
		buffer.get(codeBytes);
		buffer.get(messageBytes);
		short code = 0;
		String message = null;
		try {
			code = (short) ConversionHandler.toObject(codeBytes, Short.class);
			message = (String) ConversionHandler.toObject(messageBytes, String.class);
		} catch (ConversionException exception) {
			throw new ResponseException("An error occured while converting the response data!", exception);
		}
		this.code = code;
		this.message = message;
	}

	/**
	 * This method returns this error response's data as a map.
	 * 
	 * @return The responses data
	 * @throws SendableException If an error occurs while getting the data
	 */
	@Override
	public Map<String, Object> asMap() throws ResponseException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", this.code);
		result.put("message", this.message);
		return result;
	}

	/**
	 * This method returns this error response's data as a byte array.
	 * 
	 * @return The responses data
	 * @throws SendableException If an error occurs while getting the data
	 */
	@Override
	public byte[] asBytes() throws ResponseException {
		byte[] codeBytes = null;
		byte[] messageBytes = null;
		try {
			codeBytes = ConversionHandler.toBytes(this.code);
			messageBytes = ConversionHandler.toBytes(this.message);
		} catch (ConversionException exception) {
			throw new ResponseException("An error occured while converting the response data!", exception);
		}
		ByteBuffer buffer = ByteBuffer.allocate(2 + messageBytes.length);
		buffer.put(codeBytes);
		buffer.put(messageBytes);
		return buffer.array();
	}

	/**
	 * Returns the code of the error to send
	 * 
	 * @return The error's code
	 */
	public short getCode() {
		return code;
	}

	/**
	 * Returns the message of the error to send
	 * 
	 * @return The error's message
	 */
	public String getMessage() {
		return message;
	}
	
}
