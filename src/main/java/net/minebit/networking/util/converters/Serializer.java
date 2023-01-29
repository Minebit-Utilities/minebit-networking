package net.minebit.networking.util.converters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Optional;

/**
 * This class contains the means to convert {@link Serializable} objects into
 * byte arrays by utilizing java's build in serialization and vice versa to
 * convert byte arrays into {@link Object}s.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public final class Serializer implements IConverter<Object> {

	/**
	 * The unique id of the converter's container
	 */
	public static final byte ID = 0x01;
	
	/**
	 * The only instance of the {@link Serializer} object.
	 */
	public static final Serializer INSTANCE = new Serializer();

	/**
	 * This constructor constructs a new {@link Serializer} that utilizes java's
	 * built in serialization to convert {@link Serializable} objects into byte
	 * arrays and vice versa.
	 */
	private Serializer() {
	}

	/**
	 * This method serializes the given {@link Serializable} object and returns the
	 * resultant byte array in an {@link Optional}. If the given object could not be
	 * serialized then an empty optional will be returned.
	 */
	@Override
	public Optional<byte[]> sourceToBytes(Object input) {
		if (input == null || !(input instanceof Serializable)) {
			return Optional.empty();
		}
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ObjectOutputStream objectStream;
		try {
			objectStream = new ObjectOutputStream(byteStream);
			objectStream.writeObject(input);
		} catch (IOException exception) {
			return Optional.empty();
		}
		return Optional.of(byteStream.toByteArray());
	}

	/**
	 * This method deserializes the given byte array and returns the resultant
	 * {@link Object} in an {@link Optional}. If the given byte array could not be
	 * deserialized then an empty optional will be returned.
	 */
	@Override
	public Optional<Object> bytesToSource(byte[] input) {
		if (input == null) {
			return Optional.empty();
		}
		ByteArrayInputStream byteStream = new ByteArrayInputStream(input);
		try {
			ObjectInputStream objectStream = new ObjectInputStream(byteStream);
			return Optional.of(objectStream.readObject());
		} catch (IOException | ClassNotFoundException exception) {
			return Optional.empty();
		}
	}

}
