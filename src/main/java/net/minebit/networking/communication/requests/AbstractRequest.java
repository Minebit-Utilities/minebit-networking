package net.minebit.networking.communication.requests;

import net.minebit.networking.communication.AbstractSendable;
import net.minebit.networking.communication.SendableRegistry;
import net.minebit.networking.communication.requests.factories.GetRequestFactory;
import net.minebit.networking.communication.requests.factories.SetRequestFactory;

/**
 * This class represents a request that can be sent over the network from a
 * client to a server.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public abstract class AbstractRequest extends AbstractSendable {

	private long conversationId;

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

	/**
	 * This method returns the request's conversation id.
	 * 
	 * @return The requests conversation id
	 */
	public long getConversationId() {
		return conversationId;
	}

	/**
	 * This method sets the request's conversation id.
	 * 
	 * @param conversationId The requests conversation id
	 */
	public void setConversationId(long conversationId) {
		this.conversationId = conversationId;
	}

}
