package net.minebit.networking.conversations.requests;

import java.util.Map;

import net.minebit.networking.conversations.AbstractSendable;
import net.minebit.networking.exceptions.conversations.RequestException;

/**
 * This class represents a request that can be sent over the network from a
 * client to a server.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public abstract class AbstractRequest extends AbstractSendable {

	/**
	 * This constructor constructs a new {@link AbstractRequest}
	 * 
	 * @param conversationId The conversation id of the request
	 */
	public AbstractRequest() {

	}

	/**
	 * This method loads the data of the request from the given map.
	 * 
	 * @param input The map to load the data from
	 * @throws RequestException If an error occurs while the bytes are being loaded
	 */
	@Override
	public abstract void load(Map<String, Object> input) throws RequestException;

	/**
	 * This method loads the data of the request from the given bytes.
	 * 
	 * @param input The bytes to load the data from
	 * @throws RequestException If an error occurs while the bytes are being loaded
	 */
	@Override
	public abstract void load(byte[] input) throws RequestException;

	/**
	 * This method returns this request's data as a map.
	 * 
	 * @return The request's data
	 * @throws RequestException If an error occurs while getting the data.
	 */
	@Override
	public abstract Map<String, Object> asMap() throws RequestException;

	/**
	 * This method returns this request's data as a byte array.
	 * 
	 * @return The request's data
	 * @throws RequestException If an error occurs while getting the data.
	 */
	@Override
	public abstract byte[] asBytes() throws RequestException;

}
