package net.minebit.networking.communication;

import net.minebit.networking.conversations.SendableRegistry;
import net.minebit.networking.conversations.responses.AbstractResponse;

/**
 * This class represents a handler that handles the translation of a response to
 * bytes along it's conversation id and vice versa. Also supports compression.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class ResponsePacketHandler extends AbstractPacketHandler<AbstractResponse> {

	private static final ResponsePacketHandler INSTANCE = new ResponsePacketHandler();

	private ResponsePacketHandler() {
	}

	@Override
	protected SendableRegistry<AbstractResponse> registry() {
		return AbstractResponse.getResponseRegistry();
	}

	@Override
	protected Class<? extends AbstractResponse> typeClass(AbstractResponse sendable) {
		return sendable.getClass();
	}

	/**
	 * This method returns the only instance of {@link ResponsePacketHandler}
	 * 
	 * @return The handler's instance
	 */
	public static ResponsePacketHandler getInstance() {
		return ResponsePacketHandler.INSTANCE;
	}

}
