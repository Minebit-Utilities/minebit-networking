package net.minebit.networking.responses;

import java.util.Optional;

import net.minebit.networking.responses.error.ErrorResponseBuilder;
import net.minebit.networking.responses.error.ErrorResponseGuide;
import net.minebit.networking.util.IGuide;

/**
 * This enum acts as a container for {@link IResponseGuide} objects that contain
 * the {@link IResponseBuilder}s that build the responses supported by the
 * library along with their unique id numbers to make them identifiable remotely
 * when used. <b>Note: </b> The objects in the enum are sorted by their id.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public enum EResponseBuilderContainer {

	// @formatter:off
	ERROR(ErrorResponseBuilder.ID, ErrorResponseGuide.INSTANCE);
	// @formatter:on

	private final byte id;
	private final IResponseGuide guide;

	/**
	 * This constructor constructs a new {@link IResponseGuide} that contains the
	 * {@link IGuide}s used to construct the builders of the supported responses
	 * along with its unique id.
	 * 
	 * @param id    The unique identifier of the guide
	 * @param guide The {@link IResponseGuide} that constructs the
	 *              {@link IResponseBuilder}s.
	 */
	private EResponseBuilderContainer(byte id, IResponseGuide guide) {
		this.id = id;
		this.guide = guide;
	}

	/**
	 * This method returns a new {@link IResponseBuilder} instance obtained from
	 * invoking the guide contained in the container. If the object provided by the
	 * guide is NULL an empty {@link Optional} will be returned.
	 * 
	 * @return The new {@link IResponseBuilder} instance.
	 */
	public Optional<IResponseBuilder> getBuilder() {
		return this.guide.build();
	}

	/**
	 * This method returns the unique id of this {@link EResponseBuilderContainer}
	 * instance.
	 * 
	 * @return The container's id
	 */
	public byte getId() {
		return this.id;
	}

	/**
	 * This method searches with binary search the enum for the
	 * {@link EResponseBuilderContainer} that has the given id. If it could not be
	 * found, an empty {@link Optional} shall be returned.
	 * 
	 * @param id The id number of the wanted response.
	 * @return The response with the exact given id.
	 */
	public static Optional<EResponseBuilderContainer> getById(byte id) {
		int min = 0, max = values().length - 1, mid;
		while (max >= min) {
			mid = (max - min) / 2 + min;
			EResponseBuilderContainer value = values()[mid];
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
