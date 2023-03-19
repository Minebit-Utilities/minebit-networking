package net.minebit.networking.responses.session.end;

import java.util.Optional;

import net.minebit.networking.responses.IResponseBuilder;
import net.minebit.networking.responses.IResponseGuide;

/**
 * This class represents a guide used to build {@link SessionEndResponse}s by
 * taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class SessionEndResponseGuide implements IResponseGuide {

	/*
	 * The only instance of the {@link SessionEndResponseGuide} object.
	 */
	public static final SessionEndResponseGuide INSTANCE = new SessionEndResponseGuide();

	/**
	 * This method constructs a new {@link SessionEndResponseGuide} used to build
	 * new {@link SessionEndResponseBuilder} objects and return them.
	 */
	private SessionEndResponseGuide() {
	}

	@Override
	public Optional<IResponseBuilder> build() {
		return Optional.of(SessionEndResponseBuilder.empty());
	}

}
