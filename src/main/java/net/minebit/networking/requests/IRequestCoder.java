package net.minebit.networking.requests;

import net.minebit.networking.exceptions.requests.RequestCoderException;

/**
 * This interface defines how an to encode and decode the data of a request.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public interface IRequestCoder<RequestDataType extends IRequestData> {

	/**
	 * This method defines how the bytes given will be decoded into request data.
	 * 
	 * @param input The bytes to decode into data.
	 * @return The result of the decoding.
	 */
	public RequestDataType decode(byte[] input) throws RequestCoderException;

	/**
	 * This method defines how the request data given will be encoded into bytes.
	 * 
	 * @param input The data to encode into bytes.
	 * @return The result of the encoding.
	 */
	public byte[] encode(RequestDataType input) throws RequestCoderException;

}
