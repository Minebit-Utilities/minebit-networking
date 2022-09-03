package net.minebit.networking.handlers;

import net.minebit.networking.conversations.requests.AbstractRequest;
import net.minebit.networking.conversations.responses.AbstractResponse;
import net.minebit.networking.miscellaneous.Triple;

/**
 * This interface represents a handler used to handle received responses.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public interface IResponseHandler extends IHandler<Triple<AbstractRequest, AbstractResponse, Long>> {

}
