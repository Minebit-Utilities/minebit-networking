package net.minebit.networking.conversations.responses.factories;

import java.util.Map;

import net.minebit.networking.conversations.ISendableFactory;
import net.minebit.networking.conversations.responses.DataResponse;
import net.minebit.networking.exceptions.conversations.SendableException;

/**
 * This class represents a factory used to construct instances of
 * {@link DataResponse} objects.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class DataResponseFactory implements ISendableFactory<DataResponse> {

	private static final DataResponseFactory INSTANCE = new DataResponseFactory();

	/**
	 * This constructor constructs a new {@link DataResponseFactory}
	 */
	private DataResponseFactory() {

	}

	/**
	 * This method constructs a {@link DataResponse} without any data.
	 * 
	 * @return The constructed {@link DataResponse}
	 * @throws SendableException If an error occurs while constructing the data
	 *                           response
	 */
	@Override
	public DataResponse construct() throws SendableException {
		return new DataResponse();
	}

	/**
	 * This method constructs a {@link DataResponse} and loads it's data from the
	 * given map.
	 * 
	 * @return The constructed {@link DataResponse}
	 * @throws SendableException If an error occurs while constructing the data
	 *                           response
	 */
	@Override
	public DataResponse construct(Map<String, Object> input) throws SendableException {
		DataResponse result = new DataResponse();
		result.load(input);
		return result;
	}

	/**
	 * This method constructs a {@link DataResponse} and loads it's data from the
	 * given bytes.
	 * 
	 * @return The constructed {@link DataResponse}
	 * @throws SendableException If an error occurs while constructing the data
	 *                           response
	 */
	@Override
	public DataResponse construct(byte[] input) throws SendableException {
		DataResponse result = new DataResponse();
		result.load(input);
		return result;
	}

	/**
	 * This method returns the only instance of {@link DataResponseFactory}.
	 * 
	 * @return The {@link DataResponseFactory} instance
	 */
	public static DataResponseFactory getInstance() {
		return DataResponseFactory.INSTANCE;
	}

}
