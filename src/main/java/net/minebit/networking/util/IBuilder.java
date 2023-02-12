package net.minebit.networking.util;

import java.util.Optional;

/**
 * Classes implementing this interface define how an {@link Object} of the given
 * type may be built using various already existing data and data provided to
 * the builder.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 * @param <OutputType> The type of object that may be built using the builder
 */
public interface IBuilder<OutputType> {

	/**
	 * This method finalizes the building process and returns the final object of
	 * the given type contained in an {@link Optional}.
	 * 
	 * @return The newly built object of the given type
	 * @see IBuilder
	 */
	public Optional<OutputType> build();

}
