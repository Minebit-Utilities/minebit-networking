package net.minebit.networking.util.converters;

import java.io.Serializable;
import java.util.Optional;

import net.minebit.networking.util.converters.primitives.ByteConverter;
import net.minebit.networking.util.converters.primitives.CharacterConverter;
import net.minebit.networking.util.converters.primitives.DoubleConverter;
import net.minebit.networking.util.converters.primitives.FloatConverter;
import net.minebit.networking.util.converters.primitives.IntegerConverter;
import net.minebit.networking.util.converters.primitives.LongConverter;
import net.minebit.networking.util.converters.primitives.ShortConverter;

/**
 * This enum acts as a container for all {@link IConverter}s supported by the
 * library along with their unique id numbers to make them identifiable remotely
 * when used. <b>Note: </b> The objects in the enum are sorted by their id.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public enum EConverterContainer {

	RAW((byte) 0x00, RawConverter.INSTANCE), SERIALIZER((byte) 0x01, Serializer.INSTANCE), STRING((byte) 0x02, StringConverter.INSTANCE), BYTE((byte) 0x03, ByteConverter.INSTANCE), SHORT((byte) 0x04, ShortConverter.INSTANCE), INTEGER((byte) 0x05, IntegerConverter.INSTANCE), LONG((byte) 0x06, LongConverter.INSTANCE), FLOAT((byte) 0x07, FloatConverter.INSTANCE), DOUBLE((byte) 0x08, DoubleConverter.INSTANCE), CHARACTER((byte) 0x09, CharacterConverter.INSTANCE);

	private final byte id;
	private final IConverter<?> converter;

	/**
	 * This constructor constructs a new {@link EConverterContainer} that contains
	 * the supported raw converter along with its unique id.
	 * 
	 * @param id        The unique identifier of the converter
	 * @param converter The {@link IConverter} that converts the objects.
	 */
	private EConverterContainer(byte id, IConverter<?> converter) {
		this.id = id;
		this.converter = converter;
	}

	/**
	 * This method returns the {@link IConverter} contained that is used to convert
	 * the object given to it to byte arrays and vice versa.
	 * 
	 * @return The converter's {@link IConverter}
	 */
	public IConverter<?> getConverter() {
		return this.converter;
	}

	/**
	 * This method returns the unique id of this {@link EConverterContainer}
	 * instance.
	 * 
	 * @return The container's id
	 */
	public byte getId() {
		return this.id;
	}

	/**
	 * This method searches with binary search the enum for the
	 * {@link EConverterContainer} that has the given id. If it could not be found,
	 * an empty {@link Optional} shall be returned.
	 * 
	 * @param id The id number of the wanted converter.
	 * @return The converter with the exact given id.
	 */
	public static Optional<EConverterContainer> getById(byte id) {
		int min = 0, max = values().length - 1, mid;
		while (max >= min) {
			mid = (max - min) / 2 + min;
			EConverterContainer value = values()[mid];
			if (value.id == id) {
				return Optional.of(value);
			}
			if (value.id > id) {
				max = mid - 1;
				continue;
			}
			min = mid + 1;
		}
		return Optional.empty();
	}

	/**
	 * This method checks the class type of the given object and returns the best
	 * suited {@link EConverterContainer} for it to be converted by. If the given
	 * object is NULL or no suitable {@link EConverterContainer} could be found an
	 * empty {@link Optional} will be returned.
	 * 
	 * @param object The object to find the best {@link EConverterContainer} for
	 * @return The best suited converter
	 */
	public static Optional<EConverterContainer> getOptimal(Object object) {
		if (object instanceof byte[]) {
			return Optional.of(RAW);
		}
		if (object instanceof String) {
			return Optional.of(STRING);
		}
		if (object instanceof Byte) {
			return Optional.of(BYTE);
		}
		if (object instanceof Short) {
			return Optional.of(SHORT);
		}
		if (object instanceof Integer) {
			return Optional.of(INTEGER);
		}
		if (object instanceof Long) {
			return Optional.of(LONG);
		}
		if (object instanceof Float) {
			return Optional.of(FLOAT);
		}
		if (object instanceof Double) {
			return Optional.of(DOUBLE);
		}
		if (object instanceof Character) {
			return Optional.of(CHARACTER);
		}
		if (object instanceof Serializable) {
			return Optional.of(SERIALIZER);
		}
		return Optional.empty();
	}

}
