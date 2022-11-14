package net.minebit.networking.communication;

import java.nio.ByteBuffer;

import net.minebit.networking.conversations.AbstractSendable;
import net.minebit.networking.conversations.ISendableFactory;
import net.minebit.networking.conversations.SendableTypeRegistry;
import net.minebit.networking.conversions.primitives.IntegerConverter;
import net.minebit.networking.conversions.primitives.LongConverter;
import net.minebit.networking.exceptions.ConversionException;
import net.minebit.networking.exceptions.WrapperException;
import net.minebit.networking.exceptions.communication.PacketException;
import net.minebit.networking.exceptions.conversations.SendableException;
import net.minebit.networking.exceptions.conversations.SendableRegistryException;
import net.minebit.networking.miscellaneous.Pair;
import net.minebit.networking.wrappers.IWrapper;

/**
 * This class represents a handler that handles the translation of a sendable to
 * bytes along it's conversation id and vice versa. Also supports compression.
 * 
 * @author Aggelowe
 * @since 0.1
 * 
 * @param <SendableType> The type of sendables the handler handles
 *
 */
public abstract class AbstractPacketHandler<SendableType extends AbstractSendable> {

	private final IWrapper[] wrappers;

	/**
	 * This method constructs a new packet handler that handles the translation of a
	 * sendable.
	 * 
	 * @param wrappers The wrappers to wrap the byte arrays with.
	 */
	public AbstractPacketHandler(IWrapper... wrappers) {
		this.wrappers = wrappers;
	}

	/**
	 * This method returns the registry that contains the type classes, indexes and
	 * factories of the sendables.
	 * 
	 * @return The registry
	 */
	protected abstract SendableTypeRegistry<SendableType> registry();

	/**
	 * This method returns the type class of the given sendable.
	 * 
	 * @return The class of the sendable
	 */
	protected abstract Class<? extends SendableType> typeClass(SendableType sendable);

	/**
	 * This method returns the packet as a byte array and compresses it if necessary
	 * and allowed.
	 * 
	 * @param pair The pair representing the packet
	 * @return The packet as bytes
	 * @throws PacketException If an error occurs while getting the packet as bytes
	 */
	public byte[] asBytes(Pair<SendableType, Long> pair) throws PacketException {
		if (pair == null) {
			throw new PacketException("The given pair cannot be NULL!");
		}
		long conversationId = pair.getSecondObject();
		SendableType sendable = pair.getFirstObject();
		if (sendable == null) {
			throw new PacketException("The sendable contained in the pair cannot be NULL!");
		}
		Class<? extends SendableType> sendableClass = this.typeClass(sendable);
		int sendableIndex;
		try {
			sendableIndex = this.registry().getIndex(sendableClass);
		} catch (SendableRegistryException exception) {
			throw new PacketException("An error occured while getting the sendable's index!", exception);
		}
		byte[] indexBytes;
		byte[] conversationBytes;
		byte[] sendableBytes;
		try {
			indexBytes = IntegerConverter.getInstance().toBytes(sendableIndex);
			conversationBytes = LongConverter.getInstance().toBytes(conversationId);
			sendableBytes = sendable.asBytes();
		} catch (ConversionException | SendableException exception) {
			throw new PacketException("An error occured while getting the packet data as bytes!", exception);
		}
		ByteBuffer buffer = ByteBuffer.allocate(sendableBytes.length + 12);
		buffer.put(indexBytes);
		buffer.put(conversationBytes);
		buffer.put(sendableBytes);
		byte[] result = buffer.array();
		try {
			if (wrappers != null) {
				for (IWrapper wrapper : this.wrappers) {
					if (wrapper != null) {
						result = wrapper.wrap(result);
					}
				}
			}
		} catch (WrapperException exception) {
			throw new PacketException("An error occured while trying to wrap the packet!", exception);
		}
		return result;
	}

	/**
	 * This method returns the packet generated from the given byte array.
	 * 
	 * @param bytes The byte array to load the packet from
	 * @return The packet as a pair
	 * @throws PacketException If an error occurs while getting the packet from the
	 *                         given bytes
	 */
	public Pair<SendableType, Long> asPair(byte[] bytes) throws PacketException {
		if (bytes == null) {
			throw new PacketException("The given byte array cannot be NULL!");
		}
		byte[] unwrapped = bytes;
		try {
			if (wrappers != null) {
				int index = this.wrappers.length;
				while (index > 0) {
					index--;
					unwrapped = this.wrappers[index].unwrap(unwrapped);
				}
			}
		} catch (WrapperException exception) {
			throw new PacketException("An error occured while trying to unwrap the packet!", exception);
		}
		if (unwrapped.length < 12) {
			throw new PacketException("The unwrapped byte array cannot have a length smaller than 12!");
		}
		byte[] indexBytes = new byte[4];
		byte[] conversationBytes = new byte[8];
		byte[] sendableBytes = new byte[unwrapped.length - 12];
		ByteBuffer buffer = ByteBuffer.wrap(unwrapped);
		buffer.get(indexBytes);
		buffer.get(conversationBytes);
		buffer.get(sendableBytes);
		long conversationId;
		SendableType sendable;
		try {
			int index = IntegerConverter.getInstance().toObject(indexBytes);
			conversationId = (long) LongConverter.getInstance().toObject(conversationBytes);
			ISendableFactory<? extends SendableType> factory = this.registry().getFactory(index);
			sendable = factory.construct();
			sendable.load(sendableBytes);
		} catch (ConversionException | SendableException exception) {
			throw new PacketException("An error occured while obtaining the packet!", exception);
		}
		return new Pair<SendableType, Long>(sendable, conversationId);
	}

}
