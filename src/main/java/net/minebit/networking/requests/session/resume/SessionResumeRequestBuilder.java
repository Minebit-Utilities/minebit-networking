package net.minebit.networking.requests.session.resume;

import java.util.Optional;

import net.minebit.networking.requests.IRequest;
import net.minebit.networking.requests.IRequestBuilder;
import net.minebit.networking.util.converters.primitives.IntegerConverter;

/**
 * This class represents a builder used to build {@link SessionResumeRequest}s
 * by taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class SessionResumeRequestBuilder implements IRequestBuilder {

	/**
	 * The unique id of the builder's container
	 */
	public static final byte ID = 0x02;

	private final Object mutex = new Object();
	
	private int sessionId = 0;

	/**
	 * This method returns a newly constructed empty
	 * {@link SessionResumeRequestBuilder} builder.
	 * 
	 * @return The new {@link SessionResumeRequestBuilder}
	 * @see #SessionResumeRequestBuilder()
	 */
	public static SessionResumeRequestBuilder empty() {
		return new SessionResumeRequestBuilder();
	}

	/**
	 * This constructor constructs a new {@link SessionResumeRequestBuilder} used to
	 * build new {@link SessionResumeRequest} objects with the data provided and
	 * return them.
	 */
	private SessionResumeRequestBuilder() {
	}

	/**
	 * This method sets the session id of the session to continue.
	 * 
	 * @param sessionId The session's id
	 */
	public void setSessionId(int sessionId) {
		synchronized (this.mutex) {
			this.sessionId = sessionId;
		}
	}

	@Override
	public boolean load(byte[] data) {
		synchronized (this.mutex) {
			Optional<Integer> sessionIdOptional = IntegerConverter.INSTANCE.bytesToSource(data);
			if (!sessionIdOptional.isPresent()) {
				return false;
			}
			this.sessionId = sessionIdOptional.get();
			return true;
		}
	}

	@Override
	public Optional<IRequest> build() {
		synchronized (this.mutex) {
			return Optional.of(new SessionResumeRequest(this.sessionId));
		}
	}

}
