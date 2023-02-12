package net.minebit.networking.util.communicables;

import net.minebit.networking.util.ILoadableBuilder;

/**
 * Classes implementing this interface define how an {@link ICommunicable} of
 * the given type that may be built using various already existing data and data
 * provided to the builder.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 * @param <OutputType> The type of {@link ICommunicable} that may be built using
 *                     the builder
 */
public interface ICommunicableBuilder<OutputType extends ICommunicable> extends ILoadableBuilder<OutputType> {

}