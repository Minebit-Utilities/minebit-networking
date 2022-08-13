package net.minebit.networking.communication.requests.factories;

import java.util.Map;

import net.minebit.networking.communication.ISendableFactory;
import net.minebit.networking.communication.requests.GetRequest;
import net.minebit.networking.exceptions.communication.SendableException;

/**
 * This class represents a factory used to construct instances of
 * {@link GetRequest} objects.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class GetRequestFactory implements ISendableFactory<GetRequest> {

	private static final GetRequestFactory INSTANCE = new GetRequestFactory();

	/**
	 * This constructor constructs a new {@link GetRequestFactory}
	 */
	private GetRequestFactory() {

	}

	/**
	 * This method constructs a {@link GetRequest} without any data.
	 * 
	 * @return The constructed {@link GetRequest}
	 * @throws SendableException If an error occurs while constructing the get
	 *                           request
	 */
	@Override
	public GetRequest construct() throws SendableException {
		return new GetRequest();
	}

	/**
	 * This method constructs a {@link GetRequest} and loads it's data from the
	 * given map.
	 * 
	 * @return The constructed {@link GetRequest}
	 * @throws SendableException If an error occurs while constructing the get
	 *                           request
	 */
	@Override
	public GetRequest construct(Map<String, Object> input) throws SendableException {
		GetRequest result = new GetRequest();
		result.load(input);
		return result;
	}

	/**
	 * This method constructs a {@link GetRequest} and loads it's data from the
	 * given bytes.
	 * 
	 * @return The constructed {@link GetRequest}
	 * @throws SendableException If an error occurs while constructing the get
	 *                           request
	 */
	@Override
	public GetRequest construct(byte[] input) throws SendableException {
		GetRequest result = new GetRequest();
		result.load(input);
		return result;
	}

	/**
	 * This method returns the only instance of {@link GetRequestFactory}.
	 * 
	 * @return The {@link GetRequestFactory} instance
	 */
	public static GetRequestFactory getInstance() {
		return GetRequestFactory.INSTANCE;
	}

}
