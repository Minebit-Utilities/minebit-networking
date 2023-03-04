package net.minebit.networking.requests.message.synchronous;

import java.util.Optional;

import net.minebit.networking.messages.Message;
import net.minebit.networking.requests.IRequest;

/**
 * This class represents an {@link IRequest} sent to a server by a client,
 * transmitting a message and requesting it's response to be processed and
 * transmitted and also contains the means to convert the request into an array
 * of bytes.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 */
public class MessageSynchronousRequest implements IRequest {

	private final Message message;

	/**
	 * This constructor constructs a new {@link MessageSynchronousRequest} that
	 * represents a request to respond immediately to the transmitted message.
	 * 
	 * @param message The message the server will respond to
	 */
	MessageSynchronousRequest(Message message) {
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
		return MessageSynchronousRequestBuilder.ID;
	}

	@Override
	public Optional<byte[]> bytes() {
		return message.bytes();
	}

}
