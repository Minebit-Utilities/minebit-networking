package net.minebit.networking.responses.session.end;

import java.util.Optional;

import net.minebit.networking.responses.IResponse;

/**
 * This class represents an {@link IResponse} sent to a client by a server,
 * marking the deletion of the client's session successful and also contains the
 * means to convert the response into an array of bytes.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 */
public class SessionEndResponse implements IResponse {

	/**
	 * This constructor constructs a new {@link SessionEndResponse} that represents
	 * a response to a previously sent request marking the successful deletion of
	 * the client's current session.
	 */
	SessionEndResponse() {
	}

	@Override
	public byte getBuilderId() {
		return SessionEndResponseBuilder.ID;
	}

	@Override
	public Optional<byte[]> bytes() {
		return Optional.of(new byte[0]);
	}

}
