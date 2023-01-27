package net.minebit.networking.util;

/**
 * Classes implementing can do everything {@link IBuilder} offers and
 * additionally are able to load the data from an array of bytes.
 * 
 * @author Aggelowe
 * @since v0.2.0-beta
 * @param <OutputType> The type of object that may be build using the builder
 */

public interface ILoadableBuilder<OutputType> extends IBuilder<OutputType> {

	/**
	 * This method decodes the given byte array and replaces the current data with
	 * the ones obtained from the given byte array.
	 * 
	 * @param data The byte array to get the data from
	 * @return Whether the operation was successful
	 */
	public boolean load(byte[] data);

}
