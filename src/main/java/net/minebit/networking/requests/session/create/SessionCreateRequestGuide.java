package net.minebit.networking.requests.session.create;

import java.util.Optional;

import net.minebit.networking.requests.IRequestBuilder;
import net.minebit.networking.requests.IRequestGuide;

/**
 * This class represents a guide used to build {@link SessionCreateRequest}s by
 * taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class SessionCreateRequestGuide implements IRequestGuide {

	/**
	 * The only instance of the {@link SessionCreateRequestGuide} object.
	 */
	public static final SessionCreateRequestGuide INSTANCE = new SessionCreateRequestGuide();

	/**
	 * This method constructs a new {@link SessionCreateRequestGuide} used to build
	 * new {@link SessionCreateRequestBuilder} objects and return them.
	 */
	private SessionCreateRequestGuide() {
	}

	@Override
	public Optional<IRequestBuilder> build() {
		return Optional.of(SessionCreateRequestBuilder.empty());
	}

}
