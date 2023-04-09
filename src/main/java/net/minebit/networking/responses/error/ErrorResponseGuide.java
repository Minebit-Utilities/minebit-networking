package net.minebit.networking.responses.error;

import java.util.Optional;

import net.minebit.networking.responses.IResponseBuilder;
import net.minebit.networking.responses.IResponseGuide;

/**
 * This class represents a guide used to build {@link ErrorResponse}s by
 * taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class ErrorResponseGuide implements IResponseGuide {

	/**
	 * The only instance of the {@link ErrorResponseGuide} object.
	 */
	public static final ErrorResponseGuide INSTANCE = new ErrorResponseGuide();

	/**
	 * This method constructs a new {@link ErrorResponseGuide} used to build
	 * new {@link ErrorResponseBuilder} objects and return them.
	 */
	private ErrorResponseGuide() {
	}

	@Override
	public Optional<IResponseBuilder> build() {
		return Optional.of(ErrorResponseBuilder.empty());
	}

}
