package net.minebit.networking.requests.message.asynchronous;

import java.util.Optional;

import net.minebit.networking.messages.Message;
import net.minebit.networking.messages.MessageBuilder;
import net.minebit.networking.requests.IRequest;
import net.minebit.networking.requests.IRequestBuilder;

/**
 * This class represents a builder used to build
 * {@link MessageAsynchronousRequest}s by taking the provided information and
 * parsing it into a newly constructed instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class MessageAsynchronousRequestBuilder implements IRequestBuilder {

	/**
	 * The unique id of the builder's container
	 */
	public static final byte ID = 0x05;

	private final Object mutex = new Object();

	private Message message;

	/**
	 * This method returns a newly constructed empty
	 * {@link MessageAsynchronousRequestBuilder} builder.
	 * 
	 * @return The new {@link MessageAsynchronousRequestBuilder}
	 * @see #MessageAsynchronousRequestBuilder()
	 */
	public static MessageAsynchronousRequestBuilder empty() {
		return new MessageAsynchronousRequestBuilder();
	}

	/**
	 * This constructor constructs a new {@link MessageAsynchronousRequestBuilder}
	 * used to build new {@link MessageAsynchronousRequest} objects with the
	 * data provided and return them.
	 */
	private MessageAsynchronousRequestBuilder() {
	}

	/**
	 * This method sets the request's message to transmit to the server
	 * 
	 * @param message The request's message
	 */
	public void setMessage(Message message) {
		synchronized (this.mutex) {
			this.message = message;
		}
	}

	@Override
	public boolean load(byte[] data) {
		MessageBuilder builder = MessageBuilder.empty();
		boolean messageSuccess = builder.load(data);
		if (!messageSuccess) {
			return false;
		}
		Optional<Message> messageOptional = builder.build();
		synchronized (this.mutex) {
			this.message = messageOptional.get();
		}
		return true;
	}

	@Override
	public Optional<IRequest> build() {
		if (message == null) {
			return Optional.empty();
		}
		synchronized (this.mutex) {
			return Optional.of(new MessageAsynchronousRequest(this.message));
		}
	}

}
