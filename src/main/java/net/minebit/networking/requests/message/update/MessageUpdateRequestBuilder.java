package net.minebit.networking.requests.message.update;

import java.util.Optional;

import net.minebit.networking.requests.IRequest;
import net.minebit.networking.requests.IRequestBuilder;

/**
 * This class represents a builder used to build {@link MessageUpdateRequest}s
 * by taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class MessageUpdateRequestBuilder implements IRequestBuilder {

	/**
	 * The unique id of the builder's container
	 */
	public static final byte ID = 0x03;

	/**
	 * This method returns a newly constructed empty
	 * {@link MessageUpdateRequestBuilder} builder.
	 * 
	 * @return The new {@link MessageUpdateRequestBuilder}
	 * @see #MessageUpdateRequestBuilder()
	 */
	public static MessageUpdateRequestBuilder empty() {
		return new MessageUpdateRequestBuilder();
	}

	/**
	 * This constructor constructs a new {@link MessageUpdateRequestBuilder} used to
	 * build new {@link MessageUpdateRequest} objects with the data provided and
	 * return them.
	 */
	private MessageUpdateRequestBuilder() {
	}

	@Override
	public boolean load(byte[] data) {
		return true;
	}

	@Override
	public Optional<IRequest> build() {
		return Optional.of(new MessageUpdateRequest());
	}

}
