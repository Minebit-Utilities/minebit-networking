package net.minebit.networking.common;

import java.util.function.Function;

/**
 * {@link Link} that acts as a link for another object of the given type
 * on a different location.
 * 
 * @author Aggelowe
 *
 * @param <ObjectType> The type of the linked object.
 */
public abstract class Link<ObjectType> {

	private Function<Link<ObjectType>, Void> onModification = (object) -> null;

	/**
	 * Write the given value to the linked object.
	 * 
	 * @param value The value to change the linked object to.
	 */
	public abstract void write(ObjectType value);

	/**
	 * Read the value of the linked object.
	 * 
	 * @return The object's value.
	 */
	public abstract ObjectType read();

	/**
	 * This method defines what should happen when the object is modified.
	 * 
	 * @param function The defining {@link Function}.
	 */
	public final void onModification(Function<Link<ObjectType>, Void> function) {
		this.onModification = function;
	}

	/**
	 * This method runs the function is set by the {@link #onModification(Function)}
	 * method.
	 */
	protected final void onModification() {
		this.onModification.apply(this);
	};

}
