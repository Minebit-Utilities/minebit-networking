package net.minebit.networking.util;

/**
 * Classes implementing this interface define how a specific {@link IBuilder} may be
 * built using various already existing data and data provided to the guide.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 * @param <OutputType> The type of {@link IBuilder} that may be built using the guide
 */
public interface IGuide<OutputType extends IBuilder<?>> extends IBuilder<OutputType> {

}
