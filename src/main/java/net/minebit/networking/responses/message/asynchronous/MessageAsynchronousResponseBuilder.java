package net.minebit.networking.responses.message.asynchronous;

import java.util.Optional;

import net.minebit.networking.responses.IResponse;
import net.minebit.networking.responses.IResponseBuilder;
import net.minebit.networking.util.converters.primitives.IntegerConverter;

/**
 * This class represents a builder used to build
 * {@link MessageAsynchronousResponse}s by taking the provided information and
 * parsing it into a newly constructed instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class MessageAsynchronousResponseBuilder implements IResponseBuilder {

	/**
	 * The unique id of the builder's container
	 */
	public static final byte ID = 0x06;

	private final Object mutex = new Object();

	private int id;

	/**
	 * This method returns a newly constructed empty
	 * {@link MessageAsynchronousResponseBuilder} builder.
	 * 
	 * @return The new {@link MessageAsynchronousResponseBuilder}
	 * @see #MessageAsynchronousResponseBuilder()
	 */
	public static MessageAsynchronousResponseBuilder empty() {
		return new MessageAsynchronousResponseBuilder();
	}

	/**
	 * This constructor constructs a new {@link MessageAsynchronousResponseBuilder}
	 * used to build new {@link MessageAsynchronousResponse} objects with the data
	 * provided and return them.
	 */
	private MessageAsynchronousResponseBuilder() {
	}

	/**
	 * This method sets the id of the conversation to transmit to the client
	 * 
	 * @param description The error's description
	 */
	public void setId(int id) {
		synchronized (this.mutex) {
			this.id = id;
		}
	}

	@Override
	public boolean load(byte[] data) {
		Optional<Integer> idOptional = IntegerConverter.INSTANCE.bytesToSource(data);
		if (idOptional.isPresent()) {
			synchronized (this.mutex) {
				this.id = idOptional.get();
			}
			return true;
		}
		return false;
	}

	@Override
	public Optional<IResponse> build() {
		synchronized (this.mutex) {
			return Optional.of(new MessageAsynchronousResponse(id));
		}
	}

}
