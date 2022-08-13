package net.minebit.networking.communication.responses;

import net.minebit.networking.communication.AbstractSendable;
import net.minebit.networking.communication.SendableRegistry;

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

	/**
	 * This method returns the registry that contains all the response types.
	 * 
	 * @return The response registry
	 */
	public static SendableRegistry<AbstractResponse> getResponseRegistry() {
		return AbstractResponse.RESPONSE_REGISTRY;
	}

}
