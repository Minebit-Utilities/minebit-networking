package net.minebit.networking.requests;

import java.util.Optional;

import net.minebit.networking.requests.session.create.SessionCreateRequestBuilder;
import net.minebit.networking.requests.session.create.SessionCreateRequestGuide;
import net.minebit.networking.requests.session.end.SessionEndRequestBuilder;
import net.minebit.networking.requests.session.end.SessionEndRequestGuide;
import net.minebit.networking.requests.session.resume.SessionResumeRequestBuilder;
import net.minebit.networking.requests.session.resume.SessionResumeRequestGuide;
import net.minebit.networking.util.IGuide;

/**
 * This enum acts as a container for {@link IRequestGuide} objects that contain
 * the {@link IRequestBuilder}s that build the requests supported by the library
 * along with their unique id numbers to make them identifiable remotely when
 * used. <b>Note: </b> The objects in the enum are sorted by their id.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public enum ERequestBuilderContainer {

	// @formatter:off
	SESSION_CREATE(SessionCreateRequestBuilder.ID, SessionCreateRequestGuide.INSTANCE),
	SESSION_END(SessionEndRequestBuilder.ID, SessionEndRequestGuide.INSTANCE),
	SESSION_RESUME(SessionResumeRequestBuilder.ID, SessionResumeRequestGuide.INSTANCE);
	// @formatter:on

	private final byte id;
	private final IRequestGuide guide;

	/**
	 * This constructor constructs a new {@link IRequestGuide} that contains the
	 * {@link IGuide}s used to construct the builders of the supported requests
	 * along with its unique id.
	 * 
	 * @param id    The unique identifier of the guide
	 * @param guide The {@link IRequestGuide} that constructs the
	 *              {@link IRequestBuilder}s.
	 */
	private ERequestBuilderContainer(byte id, IRequestGuide guide) {
		this.id = id;
		this.guide = guide;
	}

	/**
	 * This method returns a new {@link IRequestBuilder} instance obtained from
	 * invoking the guide contained in the container. If the object provided by the
	 * guide is NULL an empty {@link Optional} will be returned.
	 * 
	 * @return The new {@link IRequestBuilder} instance.
	 */
	public Optional<IRequestBuilder> getBuilder() {
		return this.guide.build();
	}

	/**
	 * This method returns the unique id of this {@link ERequestBuilderContainer}
	 * instance.
	 * 
	 * @return The container's id
	 */
	public byte getId() {
		return this.id;
	}

	/**
	 * This method searches with binary search the enum for the
	 * {@link ERequestBuilderContainer} that has the given id. If it could not be
	 * found, an empty {@link Optional} shall be returned.
	 * 
	 * @param id The id number of the wanted request.
	 * @return The request with the exact given id.
	 */
	public static Optional<ERequestBuilderContainer> getById(byte id) {
		int min = 0, max = values().length - 1, mid;
		while (max >= min) {
			mid = (max - min) / 2 + min;
			ERequestBuilderContainer value = values()[mid];
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
