package net.minebit.networking.miscellaneous;

/**
 * This class is used as a container for three different objects of different
 * types.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 * @param <FirstType>  The type of the first object
 * @param <SecondType> The type of the second object
 * @param <ThirdType>  The type of the third object
 * 
 */
public class Triple<FirstType, SecondType, ThirdType> extends Pair<FirstType, SecondType> {

	private final ThirdType thirdObject;

	/**
	 * This constructor constructs a new object that contains three different
	 * objects of different types.
	 * 
	 * @param objectA The first object.
	 * @param objectB The second object.
	 * @param objectC The third object.
	 */
	public Triple(FirstType objectA, SecondType objectB, ThirdType objectC) {
		super(objectA, objectB);
		this.thirdObject = objectC;
	}

	/**
	 * This method returns the third object.
	 * 
	 * @return The third object.
	 */
	public ThirdType getThirdObject() {
		return thirdObject;
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
		String firstString = getFirstObject() != null ? getFirstObject().toString() : "NULL";
		String secondString = getSecondObject() != null ? getSecondObject().toString() : "NULL";
		String thirdString = thirdObject != null ? thirdObject.toString() : "NULL";
		return "[" + firstString + "] + [" + secondString + "] + [" + thirdString + "]";
	}

}
