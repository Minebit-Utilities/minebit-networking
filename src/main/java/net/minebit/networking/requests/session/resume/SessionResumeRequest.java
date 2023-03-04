package net.minebit.networking.requests.session.resume;

import java.util.Optional;

import net.minebit.networking.requests.IRequest;
import net.minebit.networking.util.converters.primitives.IntegerConverter;

/**
 * This class represents an {@link IRequest} sent to a server by a client,
 * requesting the continuation of a previously created session and also contains the
 * means to convert the request into an array of bytes.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 */
public class SessionResumeRequest implements IRequest {

	private final int sessionId;

	/**
	 * This constructor constructs a new {@link SessionResumeRequest} that
	 * represents a request to continue an old session sent from a client to the
	 * server.
	 * 
	 * @param sessionId The id of the session to continue
	 */
	SessionResumeRequest(int sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * This method returns the session id of the session to continue from.
	 * 
	 * @return The session's id
	 */
	public int getSessionId() {
		return this.sessionId;
	}

	@Override
	public byte getBuilderId() {
		return SessionResumeRequestBuilder.ID;
	}

	@Override
	public Optional<byte[]> bytes() {
		return IntegerConverter.INSTANCE.sourceToBytes(sessionId);
	}

}
