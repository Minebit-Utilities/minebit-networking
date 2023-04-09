package net.minebit.networking.responses.error;

import java.util.Optional;

import net.minebit.networking.responses.IResponse;
import net.minebit.networking.responses.IResponseBuilder;
import net.minebit.networking.util.converters.StringConverter;

/**
 * This class represents a builder used to build {@link ErrorResponse}s by
 * taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class ErrorResponseBuilder implements IResponseBuilder {

	/**
	 * The unique id of the builder's container
	 */
	public static final byte ID = 0x00;

	private final Object mutex = new Object();

	private String description;

	/**
	 * This method returns a newly constructed empty
	 * {@link ErrorResponseBuilder} builder.
	 * 
	 * @return The new {@link ErrorResponseBuilder}
	 * @see #ErrorResponseBuilder()
	 */
	public static ErrorResponseBuilder empty() {
		return new ErrorResponseBuilder();
	}

	/**
	 * This constructor constructs a new {@link ErrorResponseBuilder} used to
	 * build new {@link ErrorResponse} objects with the data provided and
	 * return them.
	 */
	private ErrorResponseBuilder() {
	}

	/**
	 * This method sets the request's error description to transmit to the client
	 * 
	 * @param description The error's description
	 */
	public void setDescription(String description) {
		synchronized (this.mutex) {
			this.description = description;
		}
	}

	@Override
	public boolean load(byte[] data) {
		Optional<String> descriptionOptional = StringConverter.INSTANCE.bytesToSource(data);
		if (descriptionOptional.isPresent()) {
			synchronized (this.mutex) {
				this.description = descriptionOptional.get();
			}
			return true;
		}
		return false;
	}

	@Override
	public Optional<IResponse> build() {
		if (description == null) {
			return Optional.empty();
		}
		synchronized (this.mutex) {
			return Optional.of(new ErrorResponse(this.description));
		}
	}

}
