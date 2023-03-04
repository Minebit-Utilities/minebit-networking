package net.minebit.networking.requests.message.update;

import java.util.Optional;

import net.minebit.networking.requests.IRequestBuilder;
import net.minebit.networking.requests.IRequestGuide;

/**
 * This class represents a guide used to build {@link MessageUpdateRequest}s
 * by taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class MessageUpdateRequestGuide implements IRequestGuide {

	/**
	 * The only instance of the {@link MessageUpdateRequestGuide} object.
	 */
	public static final MessageUpdateRequestGuide INSTANCE = new MessageUpdateRequestGuide();

	/**
	 * This method constructs a new {@link MessageUpdateRequestGuide} used to build
	 * new {@link MessageUpdateRequestBuilder} objects and return them.
	 */
	private MessageUpdateRequestGuide() {
	}

	@Override
	public Optional<IRequestBuilder> build() {
		return Optional.of(MessageUpdateRequestBuilder.empty());
	}

}
