package net.minebit.networking.conversations.responses;

import net.minebit.networking.conversations.SendableTypeRegistry;

/**
 * This class contains several utilities necessary for the response system to
 * function.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public final class ResponseUtils {

	private ResponseUtils() {
	}

	private static final SendableTypeRegistry<AbstractResponse> RESPONSE_TYPE_REGISTRY = new SendableTypeRegistry<>();

	/**
	 * This method returns the registry that contains all the response types.
	 * 
	 * @return The response registry
	 */
	public static SendableTypeRegistry<AbstractResponse> getResponseTypeRegistry() {
		return ResponseUtils.RESPONSE_TYPE_REGISTRY;
	}

}
