package net.minebit.networking.responses.message.synchronous;

import java.util.Optional;

import net.minebit.networking.responses.IResponseBuilder;
import net.minebit.networking.responses.IResponseGuide;

/**
 * This class represents a guide used to build
 * {@link MessageSynchronousResponse}s by taking the provided information and
 * parsing it into a newly constructed instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class MessageSynchronousResponseGuide implements IResponseGuide {

	/**
	 * The only instance of the {@link MessageSynchronousResponseGuide} object.
	 */
	public static final MessageSynchronousResponseGuide INSTANCE = new MessageSynchronousResponseGuide();
	
	/**
	 * This method constructs a new {@link MessageSynchronousResponseGuide} used to build
	 * new {@link MessageSynchronousResponseBuilder} objects and return them.
	 */
	private MessageSynchronousResponseGuide() {
	}
	
	@Override
	public Optional<IResponseBuilder> build() {
		return Optional.of(MessageSynchronousResponseBuilder.empty());
	}

}
