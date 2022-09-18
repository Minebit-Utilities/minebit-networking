package net.minebit.networking.conversations;

import net.minebit.networking.exceptions.conversations.SendableException;

/**
 * This interface defines how an {@link AbstractSendable} can be constructed.
 * 
 * @param <SendableType> The type of sendable the factory constructs
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public interface ISendableFactory<SendableType extends AbstractSendable> {

	/**
	 * This method constructs an {@link AbstractSendable} without any data.
	 * 
	 * @return The constructed {@link AbstractSendable}
	 * @throws SendableException If an error occurs while constructing the sendable
	 */
	public SendableType construct() throws SendableException;

}
