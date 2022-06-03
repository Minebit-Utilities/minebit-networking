package net.minebit.networking.common;

/**
 * This class is used as a container for two different objects of different
 * types.
 * 
 * @author Aggelowe
 *
 * @param <FirstType>  The type of the first object.
 * @param <SecondType> The type of the second object.
 * 
 * @since 0.1
 */
public final class Pair<FirstType, SecondType> {

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
	 * This method indicates whether the given object is the same as this pair or,
	 * if it is a pair, it contains the same objects as this pair.
	 * 
	 * @return Whether the given object is 'the same'.
	 * @see Object#equals(Object)
	 */
	@Override
	public boolean equals(Object input) {
		if (this == input) {
			return true;
		}
		if (input instanceof Pair) {
			Pair<?, ?> pair = (Pair<?, ?>) input;
			if (this.firstObject == pair.getFirstObject() && this.secondObject == pair.getSecondObject()) {
				return true;
			}
		}
		return false;
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
