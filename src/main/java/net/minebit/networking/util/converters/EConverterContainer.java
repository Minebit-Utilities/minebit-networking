package net.minebit.networking.util.converters;

import java.util.Optional;

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

	RAW((byte) 0x00, RawConverter.INSTANCE), SERIALIZER((byte) 0x01, Serializer.INSTANCE), STRING((byte) 0x02, Serializer.INSTANCE);

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

}
