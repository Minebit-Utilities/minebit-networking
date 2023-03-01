package net.minebit.networking.requests.session.resume;

import java.util.Optional;

import net.minebit.networking.requests.IRequestBuilder;
import net.minebit.networking.requests.IRequestGuide;

/**
 * This class represents a builder used to build {@link SessionResumeRequest}s
 * by taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class SessionResumeRequestGuide implements IRequestGuide {

	/**
	 * The only instance of the {@link SessionResumeRequestGuide} object.
	 */
	public static final SessionResumeRequestGuide INSTANCE = new SessionResumeRequestGuide();
	
	/**
	 * This method constructs a new {@link SessionResumeRequestGuide} used to build
	 * new {@link SessionResumeRequestBuilder} objects and return them.
	 */
	private SessionResumeRequestGuide() {
	}
	
	@Override
	public Optional<IRequestBuilder> build() {
		return Optional.of(SessionResumeRequestBuilder.empty());
	}

}
