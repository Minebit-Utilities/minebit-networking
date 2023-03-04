package net.minebit.networking.requests.message.synchronous;

import java.util.Optional;

import net.minebit.networking.requests.IRequestBuilder;
import net.minebit.networking.requests.IRequestGuide;

/**
 * This class represents a guide used to build
 * {@link MessageSynchronousRequest}s by taking the provided information and
 * parsing it into a newly constructed instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class MessageSynchronousRequestGuide implements IRequestGuide {

	/**
	 * The only instance of the {@link MessageSynchronousRequestGuide} object.
	 */
	public static final MessageSynchronousRequestGuide INSTANCE = new MessageSynchronousRequestGuide();

	/**
	 * This method constructs a new {@link MessageSynchronousRequestGuide} used to
	 * build new {@link MessageSynchronousRequestBuilder} objects and return them.
	 */
	private MessageSynchronousRequestGuide() {
	}

	@Override
	public Optional<IRequestBuilder> build() {
		return Optional.of(MessageSynchronousRequestBuilder.empty());
	}

}
