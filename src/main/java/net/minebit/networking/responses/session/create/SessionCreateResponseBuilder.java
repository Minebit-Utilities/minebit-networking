package net.minebit.networking.responses.session.create;

import java.util.Optional;

import net.minebit.networking.responses.IResponse;
import net.minebit.networking.responses.IResponseBuilder;
import net.minebit.networking.util.converters.primitives.IntegerConverter;

/**
 * This class represents a builder used to build {@link SessionCreateResponse}s
 * by taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class SessionCreateResponseBuilder implements IResponseBuilder {

	/**
	 * The unique id of the builder's container
	 */
	public static final byte ID = 0x01;

	private final Object mutex = new Object();

	private int sessionId;

	/**
	 * This method returns a newly constructed empty
	 * {@link SessionCreateResponseBuilder} builder.
	 * 
	 * @return The new {@link SessionCreateResponseBuilder}
	 * @see #SessionCreateResponseBuilder()
	 */
	public static SessionCreateResponseBuilder empty() {
		return new SessionCreateResponseBuilder();
	}

	/**
	 * This constructor constructs a new {@link SessionCreateResponseBuilder} used
	 * to build new {@link SessionCreateResponse} objects with the data provided and
	 * return them.
	 */
	private SessionCreateResponseBuilder() {
	}

	/**
	 * This method sets the new session id to transmit to the client
	 * 
	 * @param sessionId The session id
	 */
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public boolean load(byte[] data) {
		Optional<Integer> sessionIdOptional = IntegerConverter.INSTANCE.bytesToSource(data);
		if (sessionIdOptional.isPresent()) {
			synchronized (this.mutex) {
				this.sessionId = sessionIdOptional.get();
			}
			return true;
		}
		return false;
	}

	@Override
	public Optional<IResponse> build() {
		synchronized (this.mutex) {
			return Optional.of(new SessionCreateResponse(this.sessionId));
		}
	}
}
