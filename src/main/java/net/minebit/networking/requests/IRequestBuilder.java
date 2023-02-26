package net.minebit.networking.requests;

import net.minebit.networking.util.communicables.ICommunicableBuilder;

/**
 * This class represents a builder used to build {@link IRequest}s by taking the
 * provided information and parsing it into a newly constructed instance.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 *
 */
public interface IRequestBuilder extends ICommunicableBuilder<IRequest> {
	
}
