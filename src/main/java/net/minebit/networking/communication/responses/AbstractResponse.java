package net.minebit.networking.communication.responses;

import java.util.Map;

import net.minebit.networking.communication.AbstractSendable;
import net.minebit.networking.communication.SendableRegistry;
import net.minebit.networking.communication.responses.factories.ErrorResponseFactory;
import net.minebit.networking.exceptions.communication.responses.ResponseException;

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
	}

	@Override
	public abstract void load(Map<String, Object> input) throws ResponseException;

	@Override
	public abstract void load(byte[] input) throws ResponseException;

	@Override
	public abstract Map<String, Object> asMap() throws ResponseException;

	@Override
	public abstract byte[] asBytes() throws ResponseException;

}
