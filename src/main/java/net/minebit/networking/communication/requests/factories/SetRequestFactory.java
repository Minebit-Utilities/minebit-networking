package net.minebit.networking.communication.requests.factories;

import java.util.Map;

import net.minebit.networking.communication.ISendableFactory;
import net.minebit.networking.communication.requests.SetRequest;
import net.minebit.networking.exceptions.communication.SendableException;

/**
 * This class represents a factory used to construct instances of
 * {@link SetRequest} objects.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class SetRequestFactory implements ISendableFactory<SetRequest> {

	private static final SetRequestFactory INSTANCE = new SetRequestFactory();

	/**
	 * This constructor constructs a new {@link SetRequestFactory}
	 */
	private SetRequestFactory() {

	}

	/**
	 * This method constructs a {@link SetRequest} without any data.
	 * 
	 * @return The constructed {@link SetRequest}
	 * @throws SendableException If an error occurs while constructing the get
	 *                           request
	 */
	@Override
	public SetRequest construct() throws SendableException {
		return new SetRequest();
	}

	/**
	 * This method constructs a {@link SetRequest} and loads it's data from the
	 * given map.
	 * 
	 * @return The constructed {@link SetRequest}
	 * @throws SendableException If an error occurs while constructing the get
	 *                           request
	 */
	@Override
	public SetRequest construct(Map<String, Object> input) throws SendableException {
		SetRequest result = new SetRequest();
		result.load(input);
		return result;
	}

	/**
	 * This method constructs a {@link SetRequest} and loads it's data from the
	 * given bytes.
	 * 
	 * @return The constructed {@link SetRequest}
	 * @throws SendableException If an error occurs while constructing the get
	 *                           request
	 */
	@Override
	public SetRequest construct(byte[] input) throws SendableException {
		SetRequest result = new SetRequest();
		result.load(input);
		return result;
	}

	/**
	 * This method returns the only instance of {@link SetRequestFactory}.
	 * 
	 * @return The {@link SetRequestFactory} instance
	 */
	public static SetRequestFactory getInstance() {
		return SetRequestFactory.INSTANCE;
	}

}
