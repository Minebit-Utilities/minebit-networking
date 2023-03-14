package net.minebit.networking.responses.error;

import java.util.Optional;

import net.minebit.networking.responses.IResponse;
import net.minebit.networking.util.converters.StringConverter;

/**
 * This class represents an {@link IResponse} sent to a client by a server,
 * responding with an error to a previously sent request and also contains
 * the means to convert the response into an array of bytes.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 */
public class ErrorResponse implements IResponse {

	private final String description;

	/**
	 * This constructor constructs a new {@link ErrorResponse} that represents
	 * a response to a previously sent request describing an error that occurred
	 * while processing or interpreting the request's data.
	 * 
	 * @param description The error's description
	 */
	ErrorResponse(String description) {
		this.description = description;
	}

	/**
	 * This method returns the description of the input error that occurred.
	 * 
	 * @return The description of the error
	 */
	public String getDescription() {
		return this.description;
	}

	@Override
	public byte getBuilderId() {
		return ErrorResponseBuilder.ID;
	}

	@Override
	public Optional<byte[]> bytes() {
		return StringConverter.INSTANCE.sourceToBytes(description);
	}

}
