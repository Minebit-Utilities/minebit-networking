package net.minebit.networking.communication;

import net.minebit.networking.wrappers.IWrapper;

/**
 * This class represents a dyad of a {@link RequestPacketHandler} and a
 * {@link ResponsePacketHandler} that have the same wrappers.
 * 
 * @author Aggelowe
 * @since 0.1
 */
public class PacketHandlerDyad {

	private final RequestPacketHandler requestPacketHandler;
	private final ResponsePacketHandler responsePacketHandler;

	/**
	 * This constructor constructs a new dyad of a {@link RequestPacketHandler} and
	 * a {@link ResponsePacketHandler} with the same wrappers.
	 * 
	 * @param wrappers The wrappers of the handlers
	 */
	public PacketHandlerDyad(IWrapper... wrappers) {
		this.requestPacketHandler = new RequestPacketHandler(wrappers);
		this.responsePacketHandler = new ResponsePacketHandler(wrappers);
	}

	/**
	 * This method returns the {@link RequestPacketHandler} of the dyad.
	 * 
	 * @return The {@link RequestPacketHandler}
	 */
	public final RequestPacketHandler getRequestPacketHandler() {
		return this.requestPacketHandler;
	}

	/**
	 * This method returns the {@link ResponsePacketHandler} of the dyad.
	 * 
	 * @return The {@link ResponsePacketHandler}
	 */
	public final ResponsePacketHandler getResponsePacketHandler() {
		return this.responsePacketHandler;
	}

}
