package net.minebit.networking.communication;

import java.util.Map;

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
	 */
	public SendableType construct();

	/**
	 * This method constructs an {@link AbstractSendable} and loads it's data from
	 * the given map.
	 * 
	 * @return The constructed {@link AbstractSendable}
	 */
	public SendableType construct(Map<String, Object> input);

	/**
	 * This method constructs an {@link AbstractSendable} and loads it's data from
	 * the given bytes.
	 * 
	 * @return The constructed {@link AbstractSendable}
	 */
	public SendableType construct(byte[] input);

}
