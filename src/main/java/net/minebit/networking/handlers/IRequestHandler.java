package net.minebit.networking.handlers;

import net.minebit.networking.conversations.requests.AbstractRequest;
import net.minebit.networking.miscellaneous.Pair;

/**
 * This interface represents a handler used to handle received requests.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public interface IRequestHandler extends IHandler<Pair<AbstractRequest, Long>> {

}
