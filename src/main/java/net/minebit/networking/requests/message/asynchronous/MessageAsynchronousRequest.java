package net.minebit.networking.requests.message.asynchronous;

import java.util.Optional;

import net.minebit.networking.messages.Message;
import net.minebit.networking.requests.IRequest;

/**
 * This class represents an {@link IRequest} sent to a server by a client,
 * transmitting a message and requesting it's response to be processed and
 * stored and also contains the means to convert the request into an array of
 * bytes.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 */
public class MessageAsynchronousRequest implements IRequest {

	private final Message message;

	/**
	 * This constructor constructs a new {@link MessageAsynchronousRequest} that
	 * represents a request to process the transmitted message and store it until
	 * requested.
	 * 
	 * @param message The message the server will respond to
	 */
	MessageAsynchronousRequest(Message message) {
		this.message = message;
	}

	/**
	 * This method returns the message that the server will respond to.
	 * 
	 * @return The request's message
	 */
	public Message getMessage() {
		return this.message;
	}

	@Override
	public byte getBuilderId() {
		return MessageAsynchronousRequestBuilder.ID;
	}

	@Override
	public Optional<byte[]> bytes() {
		return message.bytes();
	}

}
