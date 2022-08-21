package net.minebit.networking.communication;

import net.minebit.networking.conversations.ISendableFactory;
import net.minebit.networking.conversations.requests.AbstractRequest;
import net.minebit.networking.exceptions.communication.CommunicationException;
import net.minebit.networking.exceptions.conversations.SendableException;
import net.minebit.networking.exceptions.conversations.SendableRegistryException;

/**
 * This class represents a request container that can be sent over the network
 * from a client to a server. Also supports several features such as request
 * compression.
 * 
 * @author Aggelowe
 * @since 0.1
 */
public class RequestPacket extends Packet<AbstractRequest> {

	/**
	 * This constructor constructs a new {@link RequestPacket}.
	 * 
	 * @param request  The request contained in the packet
	 * @param compress Whether the request should be compressed
	 * @throws CommunicationException If an error occurs while constructing the
	 *                                packet
	 */
	public RequestPacket(AbstractRequest request, boolean compress) throws CommunicationException {
		super(request, compress);
	}

	/**
	 * This constructor constructs a new {@link RequestPacket}.
	 * 
	 * @param data     The dataF contained in the packet
	 * @param compress Whether the request should be compressed
	 * @throws CommunicationException If an error occurs while constructing theF
	 *                                packet
	 */
	public RequestPacket(byte[] data, boolean compress) throws CommunicationException {
		super(data, compress);
	}

	@Override
	protected short getSendableIndex(AbstractRequest sendable) throws CommunicationException {
		if (sendable == null) {
			throw new CommunicationException("The given request cannot be NULL!");
		}
		try {
			return AbstractRequest.getRequestRegistry().getIndex(sendable.getClass());
		} catch (SendableRegistryException exception) {
			throw new CommunicationException("An error occured while obtaining the request's id", exception);
		}
	}

	@Override
	protected AbstractRequest getSendableFromIndex(short index, byte[] data) throws CommunicationException {
		if (data == null) {
			throw new CommunicationException("The given data cannot be NULL!");
		}
		ISendableFactory<? extends AbstractRequest> factory;
		try {
			factory = AbstractRequest.getRequestRegistry().getFactory(index);
		} catch (SendableRegistryException exception) {
			throw new CommunicationException("An error occured while getting the request's factory!", exception);
		}
		try {
			return factory.construct(data);
		} catch (SendableException exception) {
			throw new CommunicationException("An error occured while constructing the request");
		}
	}

}
