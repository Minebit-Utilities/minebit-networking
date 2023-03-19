package net.minebit.networking.responses.session.resume;

import java.util.Optional;

import net.minebit.networking.responses.IResponseBuilder;
import net.minebit.networking.responses.IResponseGuide;

/**
 * This class represents a guide used to build {@link SessionResumeResponse}s by
 * taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class SessionResumeResponseGuide implements IResponseGuide {

	/*
	 * The only instance of the {@link SessionResumeResponseGuide} object.
	 */
	public static final SessionResumeResponseGuide INSTANCE = new SessionResumeResponseGuide();

	/**
	 * This method constructs a new {@link SessionResumeResponseGuide} used to build
	 * new {@link SessionResumeResponseBuilder} objects and return them.
	 */
	private SessionResumeResponseGuide() {
	}

	@Override
	public Optional<IResponseBuilder> build() {
		return Optional.of(SessionResumeResponseBuilder.empty());
	}

}
