package net.minebit.networking.communication;

import java.util.Map;

import net.minebit.networking.exceptions.communication.SendableException;

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

	/**
	 * This method constructs an {@link AbstractSendable} and loads it's data from
	 * the given map.
	 * 
	 * @return The constructed {@link AbstractSendable}
	 * @throws SendableException If an error occurs while constructing the sendable
	 */
	public SendableType construct(Map<String, Object> input) throws SendableException;

	/**
	 * This method constructs an {@link AbstractSendable} and loads it's data from
	 * the given bytes.
	 * 
	 * @return The constructed {@link AbstractSendable}
	 * @throws SendableException If an error occurs while constructing the sendable
	 */
	public SendableType construct(byte[] input) throws SendableException;

}
