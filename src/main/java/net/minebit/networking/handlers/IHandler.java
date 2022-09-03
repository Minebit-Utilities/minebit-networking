package net.minebit.networking.handlers;

/**
 * This interface represents how several events are going to get handled.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 * @param <InputType> The type of the input given to the {@link #handle(Object)}
 *                    method
 */
public interface IHandler<InputType> {

	/**
	 * This method should handle the event that occurred.
	 * 
	 * @param input The data of the event that occurred.
	 */
	public void handle(InputType input);

}
