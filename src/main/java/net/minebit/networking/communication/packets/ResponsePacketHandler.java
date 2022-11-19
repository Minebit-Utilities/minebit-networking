package net.minebit.networking.communication.packets;

import net.minebit.networking.conversations.SendableTypeRegistry;
import net.minebit.networking.conversations.responses.AbstractResponse;
import net.minebit.networking.conversations.responses.ResponseUtils;
import net.minebit.networking.wrappers.IWrapper;

/**
 * This class represents a handler that handles the translation of a response to
 * bytes along it's conversation id and vice versa. Also supports compression.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class ResponsePacketHandler extends AbstractPacketHandler<AbstractResponse> {

	/**
	 * This method constructs a new packet handler that handles the translation of a
	 * response.
	 * 
	 * @param wrappers The wrappers to wrap the byte arrays with.
	 */
	public ResponsePacketHandler(IWrapper... wrappers) {
		super(wrappers);
	}

	@Override
	protected SendableTypeRegistry<AbstractResponse> registry() {
		return ResponseUtils.getResponseTypeRegistry();
	}

	@Override
	protected Class<? extends AbstractResponse> typeClass(AbstractResponse sendable) {
		return sendable.getClass();
	}

}