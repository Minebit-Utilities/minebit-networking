package net.minebit.networking.responses.session.create;

import java.util.Optional;

import net.minebit.networking.responses.IResponse;
import net.minebit.networking.util.converters.primitives.IntegerConverter;

/**
 * This class represents an {@link IResponse} sent to a client by a server,
 * responding with the data of the newly created session and marking the
 * creation successful and also contains the means to convert the response into
 * an array of bytes.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 */
public class SessionCreateResponse implements IResponse {

	private final int sessionId;

	/**
	 * This constructor constructs a new {@link SessionCreateResponse} that
	 * represents a response to a previously sent request containing the information
	 * of the newly created session.
	 * 
	 * @param sessionId The requested session's id
	 */
	SessionCreateResponse(int sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * This method returns the session id of the newly created session.
	 * 
	 * @return The new session id
	 */
	public int getSessionId() {
		return this.sessionId;
	}

	@Override
	public byte getBuilderId() {
		return SessionCreateResponseBuilder.ID;
	}

	@Override
	public Optional<byte[]> bytes() {
		return IntegerConverter.INSTANCE.sourceToBytes(sessionId);
	}

}
