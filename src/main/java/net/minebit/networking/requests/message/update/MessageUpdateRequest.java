package net.minebit.networking.requests.message.update;

import java.util.Optional;

import net.minebit.networking.requests.IRequest;

/**
 * This class represents an {@link IRequest} sent to a server by a client,
 * requesting the transmission of all completed asynchronous message requests
 * and also contains the means to convert the request into an array of bytes.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 */
public class MessageUpdateRequest implements IRequest {

	/**
	 * This constructor constructs a new {@link MessageUpdateRequest} that
	 * represents a request for the transmission of completed asynchronous message
	 * requests sent from a client to the sever
	 */
	MessageUpdateRequest() {
	}

	@Override
	public byte getBuilderId() {
		return MessageUpdateRequestBuilder.ID;
	}

	@Override
	public Optional<byte[]> bytes() {
		return Optional.of(new byte[0]);
	}

}
