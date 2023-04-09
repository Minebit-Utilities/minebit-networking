package net.minebit.networking.responses.message.synchronous;

import java.util.Optional;

import net.minebit.networking.messages.Message;
import net.minebit.networking.messages.MessageBuilder;
import net.minebit.networking.responses.IResponse;
import net.minebit.networking.responses.IResponseBuilder;

/**
 * This class represents a builder used to build
 * {@link MessageSynchronousResponse}s by taking the provided information and
 * parsing it into a newly constructed instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class MessageSynchronousResponseBuilder implements IResponseBuilder {

	/**
	 * The unique id of the builder's container
	 */
	public static final byte ID = 0x05;

	private final Object mutex = new Object();

	private Message message;

	/**
	 * This method returns a newly constructed empty
	 * {@link MessageSynchronousResponseBuilder} builder.
	 * 
	 * @return The new {@link MessageSynchronousResponseBuilder}
	 * @see #MessageSynchronousResponseBuilder()
	 */
	public static MessageSynchronousResponseBuilder empty() {
		return new MessageSynchronousResponseBuilder();
	}

	/**
	 * This constructor constructs a new {@link MessageSynchronousResponseBuilder}
	 * used to build new {@link MessageSynchronousResponse} objects with the data
	 * provided and return them.
	 */
	private MessageSynchronousResponseBuilder() {
	}

	/**
	 * This method sets the response's message to transmit to the client
	 * 
	 * @param message The response's message
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
	public Optional<IResponse> build() {
		if (message == null) {
			return Optional.empty();
		}
		synchronized (this.mutex) {
			return Optional.of(new MessageSynchronousResponse(this.message));
		}
	}

}
