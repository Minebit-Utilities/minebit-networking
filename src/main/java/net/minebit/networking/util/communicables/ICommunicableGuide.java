package net.minebit.networking.util.communicables;

import net.minebit.networking.util.IGuide;

/**
 * Classes implementing this interface define how a specific
 * {@link ICommunicableBuilder} may be processed and returned using various
 * already existing data and data provided to the guide.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 * @param <OutputType> The type of {@link ICommunicableBuilder} that may be built using the guide
 */
public interface ICommunicableGuide<OutputType extends ICommunicableBuilder<?>> extends IGuide<OutputType> {

}
