package net.minebit.networking.conversations.responses;

import java.util.Map;

import net.minebit.networking.conversations.AbstractSendable;
import net.minebit.networking.conversations.SendableRegistry;
import net.minebit.networking.conversations.responses.factories.DataResponseFactory;
import net.minebit.networking.conversations.responses.factories.ErrorResponseFactory;
import net.minebit.networking.conversations.responses.factories.SuccessResponseFactory;
import net.minebit.networking.exceptions.conversations.ResponseException;

/**
 * This class represents a response that can be sent over the network from a
 * server to a client.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public abstract class AbstractResponse extends AbstractSendable {

	/**
	 * This constructor constructs a new {@link AbstractResponse}
	 */
	public AbstractResponse() {

	}

	private static final SendableRegistry<AbstractResponse> RESPONSE_REGISTRY = new SendableRegistry<>();

	static {
		AbstractResponse.registerBuildIn();
	}

	/**
	 * This method returns the registry that contains all the response types.
	 * 
	 * @return The response registry
	 */
	public static SendableRegistry<AbstractResponse> getResponseRegistry() {
		return AbstractResponse.RESPONSE_REGISTRY;
	}

	/**
	 * This method registers the built in responses to the response registry.
	 */
	@SuppressWarnings("deprecation")
	private static void registerBuildIn() {
		AbstractResponse.RESPONSE_REGISTRY.registerUnchecked((short) 0, ErrorResponse.class, ErrorResponseFactory.getInstance());
		AbstractResponse.RESPONSE_REGISTRY.registerUnchecked((short) 1, SuccessResponse.class, SuccessResponseFactory.getInstance());
		AbstractResponse.RESPONSE_REGISTRY.registerUnchecked((short) 2, DataResponse.class, DataResponseFactory.getInstance());
	}

	/**
	 * This method loads the data of the response from the given map.
	 * 
	 * @param input The map to load the data from
	 * @throws ResponseException If an error occurs while the bytes are being loaded
	 */
	@Override
	public abstract void load(Map<String, Object> input) throws ResponseException;

	/**
	 * This method loads the data of the response from the given bytes.
	 * 
	 * @param input The bytes to load the data from
	 * @throws ResponseException If an error occurs while the bytes are being loaded
	 */
	@Override
	public abstract void load(byte[] input) throws ResponseException;

	/**
	 * This method returns this response's data as a map.
	 * 
	 * @return The response's data
	 * @throws ResponseException If an error occurs while getting the data.
	 */
	@Override
	public abstract Map<String, Object> asMap() throws ResponseException;

	/**
	 * This method returns this response's data as a byte array.
	 * 
	 * @return The response's data
	 * @throws ResponseException If an error occurs while getting the data.
	 */
	@Override
	public abstract byte[] asBytes() throws ResponseException;

}
