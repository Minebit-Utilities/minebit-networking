package net.minebit.networking.communication;

import java.util.List;

import net.minebit.networking.exceptions.communication.SendableException;

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
	 * This constructor constructs a new {@link AbstractSendable} and loads the
	 * sendable's data from the given object list.
	 * 
	 * @param data The object list to load the data from
	 * @throws SendableException If an error occurs while loading the sendable's
	 *                           data.
	 */
	public AbstractSendable(List<Object> data) throws SendableException {
		this.load(data);
	}

	/**
	 * This constructor constructs a new {@link AbstractSendable} and loads the
	 * sendable's data from the byte array
	 * 
	 * @param data The bytes to load the data from
	 * @throws SendableException If an error occurs while loading the sendable's
	 *                           data.
	 */
	public AbstractSendable(byte[] data) throws SendableException {
		this.load(data);
	}

	/**
	 * This method loads the data of the sendable from the given list of objects.
	 * 
	 * @param input The objects to load the data from
	 * @throws SendableException If an error occurs while the data are being loaded
	 */
	public abstract void load(List<Object> input) throws SendableException;

	/**
	 * This method loads the data of the sendable from the given bytes.
	 * 
	 * @param input The bytes to load the data from
	 * @throws SendableException If an error occurs while the bytes are being loaded
	 */
	public abstract void load(byte[] input) throws SendableException;

	/**
	 * This method returns this sendable's data as a list of objects.
	 * 
	 * @return The sendable's data
	 * @throws SendableException If an error occurs while getting the data.
	 */
	public abstract List<Object> asObjectList() throws SendableException;

	/**
	 * This method returns this sendable's data as a byte array.
	 * 
	 * @return The sendable's data
	 * @throws SendableException If an error occurs while getting the data.
	 */
	public abstract byte[] asBytes() throws SendableException;

}
