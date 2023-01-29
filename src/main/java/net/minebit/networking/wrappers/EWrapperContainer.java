package net.minebit.networking.wrappers;

import java.util.Optional;

/**
 * This enum acts as a container for all {@link IWrapper}s supported by the
 * library along with their unique id numbers to make them identifiable remotely
 * when used. <b>Note: </b> The objects in the enum are sorted by their id.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public enum EWrapperContainer {

	COMPRESSION(CompressionWrapper.ID, CompressionWrapper.INSTANCE);

	private final byte id;
	private final IWrapper wrapper;

	/**
	 * This constructor constructs a new {@link EWrapperContainer} that contains the
	 * supported raw wrapper along with its unique id.
	 * 
	 * @param id        The unique identifier of the wrapper
	 * @param converter The {@link IWrapper} that converts the objects.
	 */
	private EWrapperContainer(byte id, IWrapper wrapper) {
		this.id = id;
		this.wrapper = wrapper;
	}

	/**
	 * This method returns the {@link IWrapper} contained that is used to wrap and
	 * unwrap the byte arrays given to it.
	 * 
	 * @return The container's {@link IWrapper}
	 */
	public IWrapper getWrapper() {
		return this.wrapper;
	}

	/**
	 * This method returns the unique id of this {@link EWrapperContainer} instance.
	 * 
	 * @return The container's id
	 */
	public byte getId() {
		return this.id;
	}

	/**
	 * This method searches with binary search the enum for the
	 * {@link EWrapperContainer} that has the given id. If it could not be found, an
	 * empty {@link Optional} shall be returned.
	 * 
	 * @param id The id number of the wanted wrapper.
	 * @return The wrapper with the exact given id.
	 */
	public static Optional<EWrapperContainer> getById(byte id) {
		int min = 0, max = values().length - 1, mid;
		while (max >= min) {
			mid = (max - min) / 2 + min;
			EWrapperContainer value = values()[mid];
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
