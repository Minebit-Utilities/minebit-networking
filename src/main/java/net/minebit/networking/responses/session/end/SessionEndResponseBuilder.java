package net.minebit.networking.responses.session.end;

import java.util.Optional;

import net.minebit.networking.responses.IResponse;
import net.minebit.networking.responses.IResponseBuilder;

/**
 * This class represents a builder used to build {@link SessionEndResponse}s by
 * taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class SessionEndResponseBuilder implements IResponseBuilder {

	/**
	 * The unique id of the builder's container
	 */
	public static final byte ID = 0x02;

	/**
	 * This method returns a newly constructed empty
	 * {@link SessionEndResponseBuilder} builder.
	 * 
	 * @return The new {@link SessionEndResponseBuilder}
	 * @see #SessionEndResponseBuilder()
	 */
	public static SessionEndResponseBuilder empty() {
		return new SessionEndResponseBuilder();
	}

	/**
	 * This constructor constructs a new {@link SessionEndResponseBuilder} used to
	 * build new {@link SessionEndResponse} objects with the data provided and
	 * return them.
	 */
	private SessionEndResponseBuilder() {
	}

	@Override
	public boolean load(byte[] data) {
		return true;
	}

	@Override
	public Optional<IResponse> build() {
		return Optional.of(new SessionEndResponse());
	}

}
