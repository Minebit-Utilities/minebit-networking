package net.minebit.networking.responses.session.resume;

import java.util.Optional;

import net.minebit.networking.responses.IResponse;

/**
 * This class represents an {@link IResponse} sent to a client by a server,
 * marking the success of the continuation of the client's old session and also
 * contains the means to convert the response into an array of bytes.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 */
public class SessionResumeResponse implements IResponse {

	/**
	 * This constructor constructs a new {@link SessionResumeResponse} that
	 * represents a response to a previously sent request marking the successful
	 * continuation of the client's old session.
	 */
	SessionResumeResponse() {
	}

	@Override
	public byte getBuilderId() {
		return SessionResumeResponseBuilder.ID;
	}

	@Override
	public Optional<byte[]> bytes() {
		return Optional.of(new byte[0]);
	}

}
