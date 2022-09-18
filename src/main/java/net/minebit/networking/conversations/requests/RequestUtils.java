package net.minebit.networking.conversations.requests;

import net.minebit.networking.conversations.SendableTypeRegistry;

/**
 * This class contains several utilities necessary for the request system to
 * function.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public final class RequestUtils {

	private RequestUtils() {
	}

	private static final SendableTypeRegistry<AbstractRequest> REQUEST_TYPE_REGISTRY = new SendableTypeRegistry<>();

	/**
	 * This method returns the registry that contains all the request types.
	 * 
	 * @return The request registry
	 */
	public static SendableTypeRegistry<AbstractRequest> getRequestTypeRegistry() {
		return RequestUtils.REQUEST_TYPE_REGISTRY;
	}
	
}
