package net.minebit.networking.responses.message.synchronous;

import java.util.Optional;

import net.minebit.networking.messages.Message;
import net.minebit.networking.responses.IResponse;

/**
 * This class represents an {@link IResponse} sent to a client by a server,
 * containing the completed synchronous response to the synchronous request made
 * previously by the client and also contains the means to convert the response
 * into an array of bytes.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 */
public class MessageSynchronousResponse implements IResponse {

	private final Message message;

	/**
	 * This constructor constructs a new {@link MessageSynchronousResponse} that
	 * represents a response to a previously sent request, containing the message
	 * response to the message contained in the client's request.
	 * 
	 * @param message The completed synchronous request
	 */
	MessageSynchronousResponse(Message message) {
		this.message = message;
	}

	/**
	 * This method returns the message response that the server responded with.
	 * 
	 * @return The responses's message
	 */
	public Message getMessage() {
		return message;
	}

	@Override
	public byte getBuilderId() {
		return MessageSynchronousResponseBuilder.ID;
	}

	@Override
	public Optional<byte[]> bytes() {
		return message.bytes();
	}

}
