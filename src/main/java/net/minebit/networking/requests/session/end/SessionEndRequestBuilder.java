package net.minebit.networking.requests.session.end;

import java.util.Optional;

import net.minebit.networking.requests.IRequest;
import net.minebit.networking.requests.IRequestBuilder;

/**
 * This class represents a builder used to build {@link SessionEndRequest}s
 * by taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class SessionEndRequestBuilder implements IRequestBuilder {

	/**
	 * The unique id of the builder's container
	 */
	public static final byte ID = 0x01;
	
	/**
	 * This method returns a newly constructed empty
	 * {@link SessionEndRequestBuilder} builder.
	 * 
	 * @return The new {@link SessionEndRequestBuilder}
	 * @see #SessionEndRequestBuilder()
	 */
	public static SessionEndRequestBuilder empty() {
		return new SessionEndRequestBuilder();
	}
	
	/**
	 * This constructor constructs a new {@link SessionEndRequestBuilder} used to
	 * build new {@link SessionEndRequest} objects with the data provided and
	 * return them.
	 */
	private SessionEndRequestBuilder() {
	}
	
	@Override
	public boolean load(byte[] data) {
		return true;
	}

	@Override
	public Optional<IRequest> build() {
		return Optional.of(new SessionEndRequest());
	}

}
