package net.minebit.networking.common.keys;

/**
 * An interface used to define how a specific object can be translated from and
 * to a byte array. The key has an index number to be able to translate the
 * object from data of other programs.
 * 
 * @author Aggelowe
 *
 * @param <InputType> The type of object the key translates.
 */
public interface ITranslationKey<ObjectType> {

	/**
	 * Translates the given object to a byte array.
	 * 
	 * @param input The object to be translated.
	 * @return The result of the translation.
	 */
	public byte[] translateToBytes(ObjectType input);

	/**
	 * Translates a byte array to an object of the given type.
	 * 
	 * @param input The byte array to be translated.
	 * @return The result of the translation.
	 */
	public ObjectType translateFromBytes(byte... input);
}
