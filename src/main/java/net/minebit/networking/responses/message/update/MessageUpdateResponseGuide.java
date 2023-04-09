package net.minebit.networking.responses.message.update;

import java.util.Optional;

import net.minebit.networking.responses.IResponseBuilder;
import net.minebit.networking.responses.IResponseGuide;

/**
 * This class represents a guide used to build {@link MessageUpdateResponse}s by
 * taking the provided information and parsing it into a newly constructed
 * instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public class MessageUpdateResponseGuide implements IResponseGuide {

	/**
	 * The only instance of the {@link MessageUpdateResponseGuide} object.
	 */
	public static final MessageUpdateResponseGuide INSTANCE = new MessageUpdateResponseGuide();

	/**
	 * This method constructs a new {@link MessageUpdateResponseGuide} used to build
	 * new {@link MessageUpdateResponseBuilder} objects and return them.
	 */
	private MessageUpdateResponseGuide() {
	}

	@Override
	public Optional<IResponseBuilder> build() {
		return Optional.of(MessageUpdateResponseBuilder.empty());
	}

}
