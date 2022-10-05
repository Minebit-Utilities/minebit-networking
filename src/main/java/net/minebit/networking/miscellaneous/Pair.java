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
		String firstString = firstObject != null ? firstObject.toString() : "null";
		String secondString = secondObject != null ? secondObject.toString() : "null";
		return "[" + firstString + "] + [" + secondString + "]";
	}
	
	/**
	 * This method returns whether the object given is a {@link Pair} with the same contained objects as this one.
	 * 
	 * @param obj The object to check whether is the same
	 * @return Whether the two objects are the same
	 * @see Object#equals(Object)
	 */
	@Override
	public boolean equals(Object input) {
		if (this == input) {
			return true;
		}
		if (input instanceof Pair<?, ?>) {
			Pair<?, ?> inputPair = (Pair<?, ?>) input;
			Object inputFirstObject = inputPair.getFirstObject();
			Object inputSecondObject = inputPair.getSecondObject();
			return (firstObject != null ? inputFirstObject == null : firstObject.equals(inputFirstObject)) && (secondObject != null ? inputSecondObject == null : secondObject.equals(inputSecondObject));
		}
		return false;
	}
	
}
