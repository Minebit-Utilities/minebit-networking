package net.minebit.networking.converting;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.minebit.networking.exceptions.conversions.ConversionException;

/**
 * This class handles the conversion of objects to byte arrays and vice versa
 * using the java built in serializer.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class Serializer implements IConverter<Object> {

	private static final Serializer INSTANCE = new Serializer();

	/**
	 * This constructor constructs a new {@link IConverter} that utilizes java's
	 * built in serialization.
	 */
	private Serializer() {
	}

	@Override
	public byte[] toBytes(Object input) throws ConversionException {
		if (input == null) {
			throw new ConversionException("The given object cannot be NULL!");
		}
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream;
		try {
			objectOutputStream = new ObjectOutputStream(byteOutputStream);
			objectOutputStream.writeObject(input);
		} catch (IOException exception) {
			throw new ConversionException("An error occured while serialising the given object.", exception);
		}
		return byteOutputStream.toByteArray();
	}

	@Override
	public Object toObject(byte[] input) throws ConversionException {
		if (input == null) {
			throw new ConversionException("The given bytes cannot be NULL!");
		}
		ByteArrayInputStream byteInputStream = new ByteArrayInputStream(input);
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);
			return objectInputStream.readObject();
		} catch (ClassNotFoundException | IOException exception) {
			throw new ConversionException("An error occured while deserialising the given bytes.", exception);
		}
	}

	/**
	 * This method returns the only instance of the {@link Serializer} class.
	 * 
	 * @return The instance
	 */
	public static Serializer getInstance() {
		return Serializer.INSTANCE;
	}

}
