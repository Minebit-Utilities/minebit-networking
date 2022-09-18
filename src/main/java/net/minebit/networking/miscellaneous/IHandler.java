package net.minebit.networking.miscellaneous;

/**
 * This method defines what should happen when an event occurs,
 * 
 * @author Aggelowe
 * @since 0.1
 * 
 * @param <InputType> The type of data provided to the handler
 *
 */
public interface IHandler<InputType> {

	/**
	 * This method is invoked whenever the defined event occurs.
	 * 
	 * @param input The data provided to the handler
	 */
	public void handle(InputType input);

}
