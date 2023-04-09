package net.minebit.networking.responses.session.create;

import java.util.Optional;

import net.minebit.networking.responses.IResponseBuilder;
import net.minebit.networking.responses.IResponseGuide;

/**
 * This class represents a guide used to build {@link SessionCreateResponse}s by
 * taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class SessionCreateResponseGuide implements IResponseGuide {

	/**
	 * The only instance of the {@link SessionCreateResponseGuide} object.
	 */
	public static final SessionCreateResponseGuide INSTANCE = new SessionCreateResponseGuide();

	/**
	 * This method constructs a new {@link SessionCreateResponseGuide} used to build
	 * new {@link SessionCreateResponseBuilder} objects and return them.
	 */
	private SessionCreateResponseGuide() {
	}

	@Override
	public Optional<IResponseBuilder> build() {
		return Optional.of(SessionCreateResponseBuilder.empty());
	}
}
