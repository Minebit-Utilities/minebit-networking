package net.minebit.networking.communication;

import net.minebit.networking.conversations.SendableRegistry;
import net.minebit.networking.conversations.requests.AbstractRequest;

/**
 * This class represents a handler that handles the translation of a request to
 * bytes along it's conversation id and vice versa. Also supports compression.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class RequestPacketHandler extends AbstractPacketHandler<AbstractRequest> {

	private static final RequestPacketHandler INSTANCE = new RequestPacketHandler();

	private RequestPacketHandler() {
	}

	@Override
	protected SendableRegistry<AbstractRequest> registry() {
		return AbstractRequest.getRequestRegistry();
	}

	@Override
	protected Class<? extends AbstractRequest> typeClass(AbstractRequest sendable) {
		return sendable.getClass();
	}

	/**
	 * This method returns the only instance of {@link RequestPacketHandler}
	 * 
	 * @return The handler's instance
	 */
	public static RequestPacketHandler getInstance() {
		return RequestPacketHandler.INSTANCE;
	}

}
