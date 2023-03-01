package net.minebit.networking.requests.session.create;

import java.util.Optional;

import net.minebit.networking.requests.IRequest;

/**
 * This class represents an {@link IRequest} sent to a server by a client,
 * requesting the creation of a new session and also the means to convert the
 * request into an array of bytes.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 */
public class SessionCreateRequest implements IRequest {

	/**
	 * This constructor constructs a new {@link SessionCreateRequest} that
	 * represents a request for a new session sent from a client to the sever
	 */
	SessionCreateRequest() {
	}

	@Override
	public byte getBuilderId() {
		return SessionCreateRequestBuilder.ID;
	}	
	
	@Override
	public Optional<byte[]> bytes() {
		return Optional.of(new byte[0]);
	}

}
