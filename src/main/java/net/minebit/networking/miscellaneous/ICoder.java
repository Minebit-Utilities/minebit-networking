package net.minebit.networking.miscellaneous;

import net.minebit.networking.exceptions.coders.DecodingCoderException;
import net.minebit.networking.exceptions.coders.EncodingCoderException;

/**
 * This interface defines how an object of the given type will be converted to
 * bytes and vice versa.
 * 
 * @author Aggelowe
 * @since 0.1
 * @param <ObjectType> The type of object to and from bytes
 */
public interface ICoder<ObjectType> {
	
	/**
	 * This method encodes the given object into bytes.
	 * 
	 * @param input The object to encode
	 * @return The result of the encoding
	 * @throws EncodingCoderException If an error occurs during the encoding process.
	 */
	public byte[] encode(ObjectType input) throws EncodingCoderException;

	/**
	 * This method decodes the given bytes into an object.
	 * 
	 * @param input The byte array to decode
	 * @return The decoded object
	 * @throws DecodingCoderException If an error occurs during the decoding process.
	 */
	public ObjectType decode(byte[] input) throws DecodingCoderException;

}
