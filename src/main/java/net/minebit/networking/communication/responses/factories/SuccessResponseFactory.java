package net.minebit.networking.communication.responses.factories;

import java.util.Map;

import net.minebit.networking.communication.ISendableFactory;
import net.minebit.networking.communication.responses.SuccessResponse;
import net.minebit.networking.exceptions.communication.SendableException;

/**
 * This class represents a factory used to construct instances of
 * {@link SuccessResponse} objects.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class SuccessResponseFactory implements ISendableFactory<SuccessResponse> {

	private static final SuccessResponseFactory INSTANCE = new SuccessResponseFactory();

	/**
	 * This constructor constructs a new {@link SuccessResponseFactory}
	 */
	private SuccessResponseFactory() {

	}

	/**
	 * This method constructs a {@link SuccessResponse} without any data.
	 * 
	 * @return The constructed {@link SuccessResponse}
	 * @throws SendableException If an error occurs while constructing the success
	 *                           response
	 */
	@Override
	public SuccessResponse construct() throws SendableException {
		return new SuccessResponse();
	}

	/**
	 * This method constructs a {@link SuccessResponse} and loads it's data from the
	 * given map.
	 * 
	 * @return The constructed {@link SuccessResponse}
	 * @throws SendableException If an error occurs while constructing the success
	 *                           response
	 */
	@Override
	public SuccessResponse construct(Map<String, Object> input) throws SendableException {
		SuccessResponse result = new SuccessResponse();
		result.load(input);
		return result;
	}

	/**
	 * This method constructs a {@link SuccessResponse} and loads it's data from the
	 * given bytes.
	 * 
	 * @return The constructed {@link SuccessResponse}
	 * @throws SendableException If an error occurs while constructing the success
	 *                           response
	 */
	@Override
	public SuccessResponse construct(byte[] input) throws SendableException {
		SuccessResponse result = new SuccessResponse();
		result.load(input);
		return result;
	}

	/**
	 * This method returns the only instance of {@link SuccessResponseFactory}.
	 * 
	 * @return The {@link SuccessResponseFactory} instance
	 */
	public static SuccessResponseFactory getInstance() {
		return SuccessResponseFactory.INSTANCE;
	}

}
