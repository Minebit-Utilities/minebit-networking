package net.minebit.networking.communication.requests;

import net.minebit.networking.communication.AbstractSendable;
import net.minebit.networking.communication.SendableRegistry;

/**
 * This class represents a request that can be sent over the network from a
 * client to a server.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public abstract class AbstractRequest extends AbstractSendable {

	private final long conversationId;

	/**
	 * This constructor constructs a new {@link AbstractRequest}
	 * 
	 * @param conversationId The conversation id of the request
	 */
	public AbstractRequest(long conversationId) {
		this.conversationId = conversationId;
	}

	private static final SendableRegistry<AbstractRequest> REQUEST_REGISTRY = new SendableRegistry<>();

	/**
	 * This method returns the registry that contains all the request types.
	 * 
	 * @return The request registry
	 */
	public static SendableRegistry<AbstractRequest> getRequestRegistry() {
		return AbstractRequest.REQUEST_REGISTRY;
	}

	/**
	 * This method returns the requests conversation id.
	 * 
	 * @return
	 */
	public long getConversationId() {
		return conversationId;
	}

}
