package net.minebit.networking.responses.session.resume;

import java.util.Optional;

import net.minebit.networking.responses.IResponse;
import net.minebit.networking.responses.IResponseBuilder;

/**
 * This class represents a builder used to build {@link SessionResumeResponse}s
 * by taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class SessionResumeResponseBuilder implements IResponseBuilder {

	/**
	 * The unique id of the builder's container
	 */
	public static final byte ID = 0x03;

	/**
	 * This method returns a newly constructed empty
	 * {@link SessionResumeResponseBuilder} builder.
	 * 
	 * @return The new {@link SessionResumeResponseBuilder}
	 * @see #SessionResumeResponseBuilder()
	 */
	public static SessionResumeResponseBuilder empty() {
		return new SessionResumeResponseBuilder();
	}

	/**
	 * This constructor constructs a new {@link SessionResumeResponseBuilder} used
	 * to build new {@link SessionResumeResponse} objects with the data provided and
	 * return them.
	 */
	private SessionResumeResponseBuilder() {
	}

	@Override
	public boolean load(byte[] data) {
		return true;
	}

	@Override
	public Optional<IResponse> build() {
		return Optional.of(new SessionResumeResponse());
	}

}
