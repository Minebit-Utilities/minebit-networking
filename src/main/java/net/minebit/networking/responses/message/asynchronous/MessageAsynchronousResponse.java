package net.minebit.networking.responses.message.asynchronous;

import java.util.Optional;

import net.minebit.networking.responses.IResponse;
import net.minebit.networking.util.converters.primitives.IntegerConverter;

/**
 * This class represents an {@link IResponse} sent to a client by a server,
 * containing the conversation id of the asynchronous conversation between the
 * server and the client and also contains the means to convert the response
 * into an array of bytes.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 */
public class MessageAsynchronousResponse implements IResponse {

	private final int id;

	/**
	 * This constructor constructs a new {@link MessageAsynchronousResponse} that
	 * represents a response to a previously sent request, containing the
	 * conversation id of the asynchronous conversation between the server and the
	 * client.
	 * 
	 * @param id The id of the conversation
	 */
	MessageAsynchronousResponse(int id) {
		this.id = id;
	}

	/**
	 * This method returns the id of the asynchronous conversation between the
	 * client and the server.
	 * 
	 * @return The responses's message
	 */
	public int getId() {
		return id;
	}

	@Override
	public byte getBuilderId() {
		return MessageAsynchronousResponseBuilder.ID;
	}

	@Override
	public Optional<byte[]> bytes() {
		return IntegerConverter.INSTANCE.sourceToBytes(id);
	}

}
