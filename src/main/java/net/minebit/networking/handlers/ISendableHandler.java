package net.minebit.networking.handlers;

import net.minebit.networking.conversations.AbstractSendable;
import net.minebit.networking.miscellaneous.Pair;

/**
 * This interface represents a handler used to handle received sendables.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public interface ISendableHandler<InputType extends AbstractSendable> extends IHandler<Pair<Long, InputType>> {

}