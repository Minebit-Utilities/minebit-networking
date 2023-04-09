package net.minebit.networking.responses.message.update;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import net.minebit.networking.messages.Message;
import net.minebit.networking.messages.MessageBuilder;
import net.minebit.networking.responses.IResponse;
import net.minebit.networking.responses.IResponseBuilder;
import net.minebit.networking.util.ByteUtils;
import net.minebit.networking.util.converters.primitives.IntegerConverter;

/**
 * This class represents a builder used to build {@link MessageUpdateResponse}s
 * by taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class MessageUpdateResponseBuilder implements IResponseBuilder {

	/**
	 * The unique id of the builder's container
	 */
	public static final byte ID = 0x04;

	private final Object mutex = new Object();
	private final Map<Integer, Message> messages = new HashMap<>();

	/**
	 * This method returns a newly constructed empty
	 * {@link MessageUpdateResponseBuilder} builder.
	 * 
	 * @return The new {@link MessageUpdateResponseBuilder}
	 * @see #MessageUpdateResponseBuilder()
	 */
	public static MessageUpdateResponseBuilder empty() {
		return new MessageUpdateResponseBuilder();
	}

	/**
	 * This constructor constructs a new {@link MessageUpdateResponseBuilder} used
	 * to build new {@link MessageUpdateResponse} objects with the data provided and
	 * return them.
	 */
	private MessageUpdateResponseBuilder() {
	}

	/**
	 * This method adds the given message id to the {@link Map} of {@link Message}s
	 * to be sent to the client along with the response id provided.
	 * 
	 * @param id      The id of the original asynchronous request
	 * @param message The message to send
	 * @return Whether adding the given id and message to the map was successful.
	 */
	public boolean putMessage(int id, Message message) {
		if (message == null) {
			return false;
		}
		synchronized (this.mutex) {
			this.messages.put(id, message);
		}
		return true;
	}

	@Override
	public boolean load(byte[] data) {
		Optional<byte[]> sizeBytesOptional = ByteUtils.rip(data, 0, 3);
		if (!sizeBytesOptional.isPresent()) {
			return false;
		}
		byte[] sizeBytes = sizeBytesOptional.get();
		int size = IntegerConverter.INSTANCE.bytesToSource(sizeBytes).get(), position = 4;
		int[] ids = new int[size];
		Message[] messages = new Message[size];
		for (int counter = 0; counter < size; counter++) {
			Optional<byte[]> idBytesOptional = ByteUtils.rip(data, position, position + 3);
			position += 4;
			Optional<byte[]> lengthBytesOptional = ByteUtils.rip(data, position, position + 3);
			position += 4;
			if (!idBytesOptional.isPresent() || !lengthBytesOptional.isPresent()) {
				return false;
			}
			byte[] idBytes = idBytesOptional.get(), lengthBytes = lengthBytesOptional.get();
			int id = IntegerConverter.INSTANCE.bytesToSource(idBytes).get();
			int length = IntegerConverter.INSTANCE.bytesToSource(lengthBytes).get();
			Optional<byte[]> messageBytesOptional = ByteUtils.rip(data, position, position + length - 1);
			position += length;
			if (!messageBytesOptional.isPresent()) {
				return false;
			}
			byte[] messageBytes = messageBytesOptional.get();
			MessageBuilder messageBuilder = MessageBuilder.empty();
			if (!messageBuilder.load(messageBytes)) {
				return false;
			}
			Optional<Message> messageOptional = messageBuilder.build();
			if (!messageOptional.isPresent()) {
				return false;
			}
			Message message = messageOptional.get();
			ids[counter] = id;
			messages[counter] = message;
		}
		synchronized (this.mutex) {
			for (int counter = 0; counter < size; counter++) {
				this.messages.put(ids[counter], messages[counter]);
			}
		}
		return true;
	}

	@Override
	public Optional<IResponse> build() {
		synchronized (this.mutex) {
			return Optional.of(new MessageUpdateResponse(this.messages));
		}
	}

}
