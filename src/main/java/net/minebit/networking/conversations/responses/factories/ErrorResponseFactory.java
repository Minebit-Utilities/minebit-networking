package net.minebit.networking.conversations.responses.factories;

import java.util.Map;

import net.minebit.networking.conversations.ISendableFactory;
import net.minebit.networking.conversations.responses.ErrorResponse;
import net.minebit.networking.exceptions.conversations.SendableException;

/**
 * This class represents a factory used to construct instances of
 * {@link ErrorResponse} objects.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class ErrorResponseFactory implements ISendableFactory<ErrorResponse> {

	private static final ErrorResponseFactory INSTANCE = new ErrorResponseFactory();

	/**
	 * This constructor constructs a new {@link ErrorResponseFactory}
	 */
	private ErrorResponseFactory() {

	}

	/**
	 * This method constructs a {@link ErrorResponse} without any data.
	 * 
	 * @return The constructed {@link ErrorResponse}
	 * @throws SendableException If an error occurs while constructing the error
	 *                           response
	 */
	@Override
	public ErrorResponse construct() throws SendableException {
		return new ErrorResponse();
	}

	/**
	 * This method constructs a {@link ErrorResponse} and loads it's data from the
	 * given map.
	 * 
	 * @return The constructed {@link ErrorResponse}
	 * @throws SendableException If an error occurs while constructing the error
	 *                           response
	 */
	@Override
	public ErrorResponse construct(Map<String, Object> input) throws SendableException {
		ErrorResponse result = new ErrorResponse();
		result.load(input);
		return result;
	}

	/**
	 * This method constructs a {@link ErrorResponse} and loads it's data from the
	 * given bytes.
	 * 
	 * @return The constructed {@link ErrorResponse}
	 * @throws SendableException If an error occurs while constructing the error
	 *                           response
	 */
	@Override
	public ErrorResponse construct(byte[] input) throws SendableException {
		ErrorResponse result = new ErrorResponse();
		result.load(input);
		return result;
	}

	/**
	 * This method returns the only instance of {@link ErrorResponseFactory}.
	 * 
	 * @return The {@link ErrorResponseFactory} instance
	 */
	public static ErrorResponseFactory getInstance() {
		return ErrorResponseFactory.INSTANCE;
	}

}
