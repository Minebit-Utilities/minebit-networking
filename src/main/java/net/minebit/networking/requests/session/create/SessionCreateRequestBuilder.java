package net.minebit.networking.requests.session.create;

import java.util.Optional;

import net.minebit.networking.requests.IRequest;
import net.minebit.networking.requests.IRequestBuilder;

/**
 * This class represents a builder used to build {@link SessionCreateRequest}s
 * by taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class SessionCreateRequestBuilder implements IRequestBuilder {

	/**
	 * The unique id of the builder's container
	 */
	public static final byte ID = 0x00;

	/**
	 * This method returns a newly constructed empty
	 * {@link SessionCreateRequestBuilder} builder.
	 * 
	 * @return The new {@link SessionCreateRequestBuilder}
	 * @see #SessionCreateRequestBuilder()
	 */
	public static SessionCreateRequestBuilder empty() {
		return new SessionCreateRequestBuilder();
	}

	/**
	 * This constructor constructs a new {@link SessionCreateRequestBuilder} used to
	 * build new {@link SessionCreateRequest} objects with the data provided and
	 * return them.
	 */
	private SessionCreateRequestBuilder() {
	}

	@Override
	public boolean load(byte[] data) {
		return true;
	}

	@Override
	public Optional<IRequest> build() {
		return Optional.of(new SessionCreateRequest());
	}

}
