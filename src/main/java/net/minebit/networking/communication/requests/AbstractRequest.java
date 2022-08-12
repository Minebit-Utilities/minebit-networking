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
	
	/**
	 * This constructor constructs a new {@link AbstractRequest}
	 */
	public AbstractRequest() {
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

}
