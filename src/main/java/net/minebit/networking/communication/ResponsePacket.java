package net.minebit.networking.communication;

import net.minebit.networking.conversations.ISendableFactory;
import net.minebit.networking.conversations.responses.AbstractResponse;
import net.minebit.networking.exceptions.communication.CommunicationException;
import net.minebit.networking.exceptions.conversations.SendableException;
import net.minebit.networking.exceptions.conversations.SendableRegistryException;

/**
 * This class represents a response container that can be sent over the network
 * from a client to a server. Also supports several features such as response
 * compression.
 * 
 * @author Aggelowe
 * @since 0.1
 */
public class ResponsePacket extends Packet<AbstractResponse> {

	/**
	 * This constructor constructs a new {@link ResponsePacket}.
	 * 
	 * @param response The response contained in the packet
	 * @param compress Whether the response should be compressed
	 * @throws CommunicationException If an error occurs while constructing the
	 *                                packet
	 */
	public ResponsePacket(AbstractResponse response, boolean compress) throws CommunicationException {
		super(response, compress);
	}

	/**
	 * This constructor constructs a new {@link ResponsePacket}.
	 * 
	 * @param data     The dataF contained in the packet
	 * @param compress Whether the response should be compressed
	 * @throws CommunicationException If an error occurs while constructing theF
	 *                                packet
	 */
	public ResponsePacket(byte[] data, boolean compress) throws CommunicationException {
		super(data, compress);
	}

	@Override
	protected short getSendableIndex(AbstractResponse sendable) throws CommunicationException {
		if (sendable == null) {
			throw new CommunicationException("The given response cannot be NULL!");
		}
		try {
			return AbstractResponse.getResponseRegistry().getIndex(sendable.getClass());
		} catch (SendableRegistryException exception) {
			throw new CommunicationException("An error occured while obtaining the response's id", exception);
		}
	}

	@Override
	protected AbstractResponse getSendableFromIndex(short index, byte[] data) throws CommunicationException {
		if (data == null) {
			throw new CommunicationException("The given data cannot be NULL!");
		}
		ISendableFactory<? extends AbstractResponse> factory;
		try {
			factory = AbstractResponse.getResponseRegistry().getFactory(index);
		} catch (SendableRegistryException exception) {
			throw new CommunicationException("An error occured while getting the response's factory!", exception);
		}
		try {
			return factory.construct(data);
		} catch (SendableException exception) {
			throw new CommunicationException("An error occured while constructing the response");
		}
	}

}
