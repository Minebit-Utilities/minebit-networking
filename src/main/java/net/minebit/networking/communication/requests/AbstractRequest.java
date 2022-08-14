package net.minebit.networking.communication.requests;

import java.util.Map;

import net.minebit.networking.communication.AbstractSendable;
import net.minebit.networking.communication.SendableRegistry;
import net.minebit.networking.communication.requests.factories.GetRequestFactory;
import net.minebit.networking.communication.requests.factories.SetRequestFactory;
import net.minebit.networking.exceptions.communication.requests.RequestException;

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

	private static final SendableRegistry<AbstractRequest> REQUEST_REGISTRY = new SendableRegistry<>();

	static {
		AbstractRequest.registerBuildIn();
	}

	/**
	 * This method returns the registry that contains all the request types.
	 * 
	 * @return The request registry
	 */
	public static SendableRegistry<AbstractRequest> getRequestRegistry() {
		return AbstractRequest.REQUEST_REGISTRY;
	}
	
	/**
	 * This method registers the built in requests to the request registry.
	 */
	@SuppressWarnings("deprecation")
	private static void registerBuildIn() {
		AbstractRequest.REQUEST_REGISTRY.registerUnchecked((short) 0, GetRequest.class, GetRequestFactory.getInstance());
		AbstractRequest.REQUEST_REGISTRY.registerUnchecked((short) 1, SetRequest.class, SetRequestFactory.getInstance());
	}
	
	@Override
	public abstract void load(Map<String, Object> input) throws RequestException;
	
	@Override
	public abstract void load(byte[] input) throws RequestException;
	
	@Override
	public abstract Map<String, Object> asMap() throws RequestException;
	
	@Override
	public abstract byte[] asBytes() throws RequestException;

}
