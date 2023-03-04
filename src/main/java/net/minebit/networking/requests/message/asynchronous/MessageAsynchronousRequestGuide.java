package net.minebit.networking.requests.message.asynchronous;

import java.util.Optional;

import net.minebit.networking.requests.IRequestBuilder;
import net.minebit.networking.requests.IRequestGuide;

/**
 * This class represents a guide used to build
 * {@link MessageAsynchronousRequest}s by taking the provided information and
 * parsing it into a newly constructed instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class MessageAsynchronousRequestGuide implements IRequestGuide {

	/**
	 * The only instance of the {@link MessageAsynchronousRequestGuide} object.
	 */
	public static final MessageAsynchronousRequestGuide INSTANCE = new MessageAsynchronousRequestGuide();

	/**
	 * This method constructs a new {@link MessageAsynchronousRequestGuide} used to
	 * build new {@link MessageAsynchronousRequestBuilder} objects and return them.
	 */
	private MessageAsynchronousRequestGuide() {
	}

	@Override
	public Optional<IRequestBuilder> build() {
		return Optional.of(MessageAsynchronousRequestBuilder.empty());
	}

}
