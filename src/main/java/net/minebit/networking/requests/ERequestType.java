package net.minebit.networking.requests;

/**
 * This enum contains all the request types of the library.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public enum ERequestType {

	;

	private final byte index;
	private final Class<? extends IRequestData> requestClass;
	private final IRequestCoder<? extends IRequestData> requestCoder;

	/**
	 * This constructor constructs a new request type that represents a request send
	 * by the client to the server.
	 * 
	 * @param <RequestDataType> The type of {@link IRequestData} of the request type
	 * @param index             The index of the request type
	 * @param requestClass      The class of the request data container
	 * @param requestCoder      The {@link IRequestCoder} used to encode and decode
	 *                          the request data
	 */
	private <RequestDataType extends IRequestData> ERequestType(byte index, Class<RequestDataType> requestClass, IRequestCoder<RequestDataType> requestCoder) {
		this.index = index;
		this.requestClass = requestClass;
		this.requestCoder = requestCoder;
	}

	/**
	 * This method returns the index that represents the request type.
	 * 
	 * @return The index of the request type.
	 */
	public byte getIndex() {
		return index;
	}

	/**
	 * This method returns the class of the request data container which is an
	 * instance of {@link IRequestData}.
	 * 
	 * @return The class of the request data
	 */
	public Class<? extends IRequestData> getRequestClass() {
		return requestClass;
	}

	/**
	 * This method returns the {@link IRequestCoder} used to encode and decode the
	 * {@link IRequestData}.
	 * 
	 * @return The request type's {@link IRequestCoder}
	 */
	public IRequestCoder<? extends IRequestData> getRequestCoder() {
		return requestCoder;
	}

	/**
	 * This method returns the {@link ERequestType} that has the same index as the
	 * index given.
	 * 
	 * @param index The index to match the {@link ERequestType} with.
	 * @return The matched {@link ERequestType}
	 */
	public static ERequestType valueOf(byte index) {
		for (ERequestType requestType : ERequestType.values()) {
			if (requestType.index == index) {
				return requestType;
			}
		}
		return null;
	}


}
