package net.minebit.networking.communication;

import net.minebit.networking.conversations.SendableRegistry;
import net.minebit.networking.conversations.requests.AbstractRequest;
import net.minebit.networking.wrappers.IWrapper;

/**
 * This class represents a handler that handles the translation of a request to
 * bytes along it's conversation id and vice versa. Also supports compression.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class RequestPacketHandler extends AbstractPacketHandler<AbstractRequest> {

	/**
	 * This method constructs a new packet handler that handles the translation of a
	 * request.
	 * 
	 * @param wrappers The wrappers to wrap the byte arrays with.
	 */
	public RequestPacketHandler(IWrapper... wrappers) {
		super(wrappers);
	}

	@Override
	protected SendableRegistry<AbstractRequest> registry() {
		return AbstractRequest.getRequestRegistry();
	}

	@Override
	protected Class<? extends AbstractRequest> typeClass(AbstractRequest sendable) {
		return sendable.getClass();
	}

}
