package net.minebit.networking.requests.session.end;

import java.util.Optional;

import net.minebit.networking.requests.IRequestBuilder;
import net.minebit.networking.requests.IRequestGuide;

/**
 * This class represents a guide used to build {@link SessionEndRequest}s by
 * taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class SessionEndRequestGuide implements IRequestGuide {

	/**
	 * The only instance of the {@link SessionEndRequestGuide} object.
	 */
	public static final SessionEndRequestGuide INSTANCE = new SessionEndRequestGuide();

	/**
	 * This method constructs a new {@link SessionEndRequestGuide} used to build new
	 * {@link SessionEndRequestBuilder} objects and return them.
	 */
	private SessionEndRequestGuide() {
	}

	@Override
	public Optional<IRequestBuilder> build() {
		return Optional.of(SessionEndRequestBuilder.empty());
	}

}
