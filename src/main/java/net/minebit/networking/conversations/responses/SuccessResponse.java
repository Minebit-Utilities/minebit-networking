package net.minebit.networking.conversations.responses;

import java.util.HashMap;
import java.util.Map;

import net.minebit.networking.exceptions.conversations.ResponseException;
import net.minebit.networking.exceptions.conversations.SendableException;

/**
 * This class represents a success response that can be sent over the network
 * from a server to a client.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class SuccessResponse extends AbstractResponse {

	/**
	 * This constructor constructs a new {@link SuccessResponse}
	 */
	public SuccessResponse() {
	}

	/**
	 * This method loads the data of the success response from the given map.
	 * 
	 * @param input The map to load the data from
	 * @throws SendableException If an error occurs while the data are being loaded
	 */
	@Override
	public void load(Map<String, Object> input) throws ResponseException {
		if (input == null) {
			throw new ResponseException("The given map cannot be NULL!");
		}
	}

	/**
	 * This method loads the data of the success response from the given bytes.
	 * 
	 * @param input The map to load the data from
	 * @throws SendableException If an error occurs while the data are being loaded
	 */
	@Override
	public void load(byte[] input) throws ResponseException {
		if (input == null) {
			throw new ResponseException("The given byte array cannot be NULL!");
		}
	}

	/**
	 * This method returns this success response's data as a map.
	 * 
	 * @return The responses data
	 * @throws SendableException If an error occurs while getting the data
	 */
	@Override
	public Map<String, Object> asMap() throws ResponseException {
		return new HashMap<String, Object>();
	}

	/**
	 * This method returns this success response's data as a byte array.
	 * 
	 * @return The responses data
	 * @throws SendableException If an error occurs while getting the data
	 */
	@Override
	public byte[] asBytes() throws ResponseException {
		return new byte[0];
	}
	
}
