package net.minebit.networking.responses.message.update;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import net.minebit.networking.messages.Message;
import net.minebit.networking.responses.IResponse;
import net.minebit.networking.util.ByteUtils;
import net.minebit.networking.util.converters.primitives.IntegerConverter;

/**
 * This class represents an {@link IResponse} sent to a client by a server,
 * containing all the completed asynchronous responses to the asynchronous
 * requests made by the client and also contains the means to convert the
 * response into an array of bytes.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 */
public class MessageUpdateResponse implements IResponse {

	private final Map<Integer, Message> messages;

	/**
	 * This constructor constructs a new {@link MessageUpdateResponse} that
	 * represents a response to a previously sent request, that all the completed
	 * asynchronous message responses to the asynchronous message requests made by
	 * the client.
	 * 
	 * @param messages The completed asynchronous requests
	 */
	MessageUpdateResponse(Map<Integer, Message> messages) {
		this.messages = messages;
	}

	/**
	 * This method returns an <i>unmodifiable</i> {@link Map} containing all the
	 * completed responses to the client's asynchronous request.
	 * 
	 * @return The server's responses
	 */
	public Map<Integer, Message> getMessages() {
		return Collections.unmodifiableMap(this.messages);
	}

	@Override
	public byte getBuilderId() {
		return MessageUpdateResponseBuilder.ID;
	}

	@Override
	public Optional<byte[]> bytes() {
		int size = messages.size();
		byte[] result = new byte[4];
		for (Entry<Integer, Message> messageEntry : this.messages.entrySet()) {
			int id = messageEntry.getKey();
			Message message = messageEntry.getValue();
			Optional<byte[]> messageBytesOptional = message.bytes();
			if (!messageBytesOptional.isPresent()) {
				size--;
				continue;
			}
			int pos = result.length;
			byte[] messageBytes = messageBytesOptional.get();
			byte[] idBytes = IntegerConverter.INSTANCE.sourceToBytes(id).get();
			byte[] lengthBytes = IntegerConverter.INSTANCE.sourceToBytes(messageBytes.length).get();
			result = ByteUtils.enlarge(result, 0, messageBytes.length + 8).get();
			ByteUtils.overwrite(result, idBytes, pos);
			ByteUtils.overwrite(result, lengthBytes, pos + 4);
			ByteUtils.overwrite(result, messageBytes, pos + 8);
		}
		byte[] sizeBytes = IntegerConverter.INSTANCE.sourceToBytes(size).get();
		ByteUtils.overwrite(result, sizeBytes, 0);
		return Optional.of(result);
	}

}
