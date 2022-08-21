package net.minebit.networking.communication;

import java.nio.ByteBuffer;

import net.minebit.networking.conversations.AbstractSendable;
import net.minebit.networking.converting.ConversionHandler;
import net.minebit.networking.exceptions.communication.CommunicationException;
import net.minebit.networking.exceptions.conversations.SendableException;
import net.minebit.networking.exceptions.conversions.ConversionException;
import net.minebit.networking.exceptions.general.ByteException;
import net.minebit.networking.miscellaneous.ByteUtils;

/**
 * This class represents a sendable container that can be sent over the network
 * from a client to a server and vice versa. Also supports several features such
 * as sendable compression.
 *
 * @param <SendableType> The type of sendables the packet accepts
 * 
 * @author Aggelowe
 * @since 0.1
 */
public abstract class Packet<SendableType extends AbstractSendable> {

	private SendableType sendable;
	private boolean compress;

	/**
	 * This constructor constructs a new {@link Packet}
	 * 
	 * @param sendable The sendable to send
	 * @param compress Whether to allow compression of the sendable
	 * @throws CommunicationException If the given sendable is null
	 */
	public Packet(SendableType sendable, boolean compress) throws CommunicationException {
		if (sendable == null) {
			throw new CommunicationException("The given sendable cannot be NULL!");
		}
		this.sendable = sendable;
		this.compress = compress;
	}
	/**
	 * This constructor constructs a new {@link Packet}
	 * 
	 * @param sendable The sendable to send
	 * @param compress Whether to allow compression of the sendable
	 * @throws CommunicationException If an error occurs while loading the given bytes
	 */
	public Packet(byte[] data, boolean compress) throws CommunicationException {
		if (data == null) {
			throw new CommunicationException("The given byte array cannot be NULL!");
		}
		this.load(data);
		this.compress = compress;
	}

	/**
	 * This method returns the contained sendable
	 * 
	 * @return The contained sendable
	 */
	public SendableType getSendable() {
		return this.sendable;
	}

	/**
	 * This method returns the index of the class of the sendable.
	 * 
	 * @return The sendable's class's index
	 * @throws CommunicationException If an error occurs while getting the index
	 */
	protected abstract short getSendableIndex(SendableType sendable) throws CommunicationException;

	/**
	 * This method returns a sendable associated with the given index containing the
	 * given data.
	 * 
	 * @param index The index associated with the sendable
	 * @param data  The data to load the sendable from
	 * @return The newly constructed sendable
	 * @throws CommunicationException If an error occurs while constructing the sendable
	 */
	protected abstract SendableType getSendableFromIndex(short index, byte[] data) throws CommunicationException;

	/**
	 * This method returns the packet as a byte array
	 * 
	 * @return The packet
	 * @throws CommunicationException If an error occurs while getting the packet as bytes
	 */
	public byte[] asBytes() throws CommunicationException {
		byte[] indexBytes;
		byte[] conversationBytes;
		try {
			indexBytes = ConversionHandler.toBytes(this.getSendableIndex(sendable));
			conversationBytes = ConversionHandler.toBytes(this.sendable.getConversationId());
		} catch (ConversionException exception) {
			throw new CommunicationException("An error occured while converting the sendable's data!", exception);
		}
		byte[] sendableBytes;
		try {
			sendableBytes = this.sendable.asBytes();
		} catch (SendableException exception) {
			throw new CommunicationException("An error occured while getting the sendable as a byte array!", exception);
		}
		boolean isCompressed = false;
		if (this.compress) {
			byte[] compressedSendable;
			try {
				compressedSendable = ByteUtils.compress(sendableBytes, 256);
			} catch (ByteException exception) {
				throw new CommunicationException("An error occured while compressing the sendable!", exception);
			}
			if (compressedSendable.length < sendableBytes.length) {
				sendableBytes = compressedSendable;
				isCompressed = true;
			}
		}
		byte[] compressedBytes;
		try {
			compressedBytes = ConversionHandler.toBytes(isCompressed);
		} catch (ConversionException exception) {
			throw new CommunicationException("An error occured while converting compression boolean!", exception);
		}
		ByteBuffer buffer = ByteBuffer.allocate(sendableBytes.length + 11);
		buffer.put(compressedBytes);
		buffer.put(indexBytes);
		buffer.put(conversationBytes);
		buffer.put(sendableBytes);
		return buffer.array();
	}

	/**
	 * This method loads the packet from the given byte array.
	 * 
	 * @param input The byte array to load the packet from
	 * @throws CommunicationException If an error occurs while loading the packet from the
	 *                         given bytes
	 */
	private void load(byte[] input) throws CommunicationException {
		if (input.length < 11) {
			throw new CommunicationException("The given byte array must not have a length smaller than 11!");
		}
		ByteBuffer buffer = ByteBuffer.wrap(input);
		byte[] compressedBytes = new byte[1];
		byte[] indexBytes = new byte[2];
		byte[] conversationBytes = new byte[8];
		byte[] sendableBytes = new byte[input.length - 11];
		buffer.get(compressedBytes);
		buffer.get(indexBytes);
		buffer.get(conversationBytes);
		buffer.get(sendableBytes);
		boolean isCompressed = false;
		short index = 0;
		long conversationId = 0;
		try {
			isCompressed = (boolean) ConversionHandler.toObject(compressedBytes, Boolean.class);
			index = (short) ConversionHandler.toObject(indexBytes, Short.class);
			conversationId = (long) ConversionHandler.toObject(compressedBytes, Long.class);
		} catch (ConversionException exception) {
			throw new CommunicationException("An error occured while converting the packet's data!", exception);
		}
		if (isCompressed) {
			try {
				sendableBytes = ByteUtils.decompress(sendableBytes, 256);
			} catch (ByteException exception) {
				throw new CommunicationException("An error occured while decompressing the given sendable", exception);
			}
		}
		SendableType sendable = getSendableFromIndex(index, sendableBytes);
		sendable.setConversationId(conversationId);
		this.sendable = sendable;
	}

}
