package net.minebit.networking.requests.session.end;

import java.util.Optional;

import net.minebit.networking.requests.IRequest;

/**
 * This class represents an {@link IRequest} sent to a server by a client,
 * requesting the deletion of the current session and also the means to convert
 * the request into an array of bytes.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 */
public class SessionEndRequest implements IRequest {

	/**
	 * This constructor constructs a new {@link SessionEndRequest} that represents a
	 * request to end the current session sent from a client to the sever
	 */
	SessionEndRequest() {
	}

	@Override
	public byte getBuilderId() {
		return SessionEndRequestBuilder.ID;
	}

	@Override
	public Optional<byte[]> bytes() {
		return Optional.of(new byte[0]);
	}

}
