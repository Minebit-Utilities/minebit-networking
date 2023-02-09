package net.minebit.networking.util;

import java.util.Optional;

/**
 * Classes implementing this interface define how instances of that class may be
 * converted into byte arrays and returned.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 */
public interface IBytable {

	/**
	 * This method converts the instance along with it's data into a new byte array
	 * and returns it contained in an {@link Optional}.
	 * 
	 * @return The byte array containing the instance's data
	 */
	public Optional<byte[]> bytes();

}
