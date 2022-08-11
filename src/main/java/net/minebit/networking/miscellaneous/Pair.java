package net.minebit.networking.miscellaneous;

/**
 * This class is used as a container for two different objects of different
 * types.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 * @param <FirstType>  The type of the first object
 * @param <SecondType> The type of the second object
 * 
 */
public class Pair<FirstType, SecondType> {

	private final FirstType firstObject;
	private final SecondType secondObject;

	/**
	 * This constructor constructs a new object that contains two different objects
	 * of different types.
	 * 
	 * @param objectA The first object.
	 * @param objectB The second object.
	 */
	public Pair(FirstType objectA, SecondType objectB) {
		this.firstObject = objectA;
		this.secondObject = objectB;
	}

	/**
	 * This method returns the first object.
	 * 
	 * @return The first object.
	 */
	public FirstType getFirstObject() {
		return firstObject;
	}

	/**
	 * This method returns the second object.
	 * 
	 * @return The second object.
	 */
	public SecondType getSecondObject() {
		return secondObject;
	}

	/**
	 * This method returns a composed string from both objects in the format
	 * <code>[<i>firstObject</i>]+[<i>secondObject</i>]</code> using the
	 * {@link Object#toString()} method.
	 * 
	 * @return A composed string from both objects.
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		String firstString = firstObject != null ? firstObject.toString() : "NULL";
		String secondString = secondObject != null ? secondObject.toString() : "NULL";
		return "[" + firstString + "] + [" + secondString + "]";
	}
}
