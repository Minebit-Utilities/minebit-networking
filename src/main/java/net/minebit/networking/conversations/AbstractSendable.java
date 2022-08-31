package net.minebit.networking.conversations;

import java.util.Map;

import net.minebit.networking.exceptions.conversations.SendableException;

/**
 * This class represents an object that can be sent over the network from a
 * client to a server and vice versa.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public abstract class AbstractSendable {
	
	/**
	 * This constructor constructs a new {@link AbstractSendable}.
	 */
	public AbstractSendable() {
		
	}

	/**
	 * This method loads the data of the sendable from the given map.
	 * 
	 * @param input The map to load the data from
	 * @throws SendableException If an error occurs while the data are being loaded
	 */
	public abstract void load(Map<String, Object> input) throws SendableException;

	/**
	 * This method loads the data of the sendable from the given bytes.
	 * 
	 * @param input The bytes to load the data from
	 * @throws SendableException If an error occurs while the bytes are being loaded
	 */
	public abstract void load(byte[] input) throws SendableException;

	/**
	 * This method returns this sendable's data as a map.
	 * 
	 * @return The sendable's data
	 * @throws SendableException If an error occurs while getting the data.
	 */
	public abstract Map<String, Object> asMap() throws SendableException;

	/**
	 * This method returns this sendable's data as a byte array.
	 * 
	 * @return The sendable's data
	 * @throws SendableException If an error occurs while getting the data.
	 */
	public abstract byte[] asBytes() throws SendableException;
	
}
