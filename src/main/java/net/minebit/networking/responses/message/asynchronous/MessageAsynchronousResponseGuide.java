package net.minebit.networking.responses.message.asynchronous;

import java.util.Optional;

import net.minebit.networking.responses.IResponseBuilder;
import net.minebit.networking.responses.IResponseGuide;

/**
 * This class represents a guide used to build
 * {@link MessageAsynchronousResponse}s by taking the provided information and
 * parsing it into a newly constructed instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class MessageAsynchronousResponseGuide implements IResponseGuide {

	/**
	 * The only instance of the {@link MessageAsynchronousResponseGuide} object.
	 */
	public static final MessageAsynchronousResponseGuide INSTANCE = new MessageAsynchronousResponseGuide();

	/**
	 * This method constructs a new {@link MessageAsynchronousResponseGuide} used to
	 * build new {@link MessageAsynchronousResponseBuilder} objects and return them.
	 */
	private MessageAsynchronousResponseGuide() {
	}

	@Override
	public Optional<IResponseBuilder> build() {
		return Optional.of(MessageAsynchronousResponseBuilder.empty());
	}

}
