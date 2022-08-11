package net.minebit.networking.communication;

import java.util.Map;

import net.minebit.networking.exceptions.communication.SendableFactoryException;

/**
 * This interface defines how an {@link AbstractSendable} can be constructed.
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
	 * @throws SendableFactoryException If an error occurs while constructing the
	 *                                  sendable
	 */
	public SendableType construct() throws SendableFactoryException;

	/**
	 * This method constructs an {@link AbstractSendable} and loads it's data from
	 * the given map.
	 * 
	 * @return The constructed {@link AbstractSendable}
	 * @throws SendableFactoryException If an error occurs while constructing the
	 *                                  sendable
	 */
	public SendableType construct(Map<String, Object> input) throws SendableFactoryException;

	/**
	 * This method constructs an {@link AbstractSendable} and loads it's data from
	 * the given bytes.
	 * 
	 * @return The constructed {@link AbstractSendable}
	 * @throws SendableFactoryException If an error occurs while constructing the
	 *                                  sendable
	 */
	public SendableType construct(byte[] input) throws SendableFactoryException;

}
