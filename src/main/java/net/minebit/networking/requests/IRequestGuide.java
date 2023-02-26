package net.minebit.networking.requests;

import net.minebit.networking.util.communicables.ICommunicableGuide;

/**
 * Classes implementing this interface define how a specific
 * {@link IRequestBuilder} may be processed and returned using various already
 * existing data and data provided to the guide.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 */
public interface IRequestGuide extends ICommunicableGuide<IRequestBuilder> {

}
