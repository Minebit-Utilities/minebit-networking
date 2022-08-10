package net.minebit.networking.communication;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.minebit.networking.exceptions.communication.SendableException;

/**
 * This class is used as a registry for types of sendables.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class SendableRegistry<SendableType extends AbstractSendable> {

	/**
	 * This list that contains all the sendable types along with their factories.
	 */
	private final Map<Class<? extends SendableType>, ISendableFactory<? extends SendableType>> sendablesMap = Collections.synchronizedMap(new HashMap<>());

	/**
	 * This constructor constructs a new registry for sendable types to be stored
	 * along with their factories.
	 */
	public SendableRegistry() {
	}

	/**
	 * 
	 * This method registers the given type class and factory to the registry.
	 * 
	 * @param <InputType> The type of the input objects' generic inputs.
	 * @param typeClass   The class representing the sendable type.
	 * @param typeFactory The factory used to construct a sendable of the given
	 *                    type.
	 * @throws SendableException If an error occurs while registering the class and
	 *                           factory.
	 */
	public <InputType extends SendableType> void register(Class<InputType> typeClass, ISendableFactory<InputType> typeFactory) throws SendableException {
		if (typeClass == null) {
			throw new SendableException("The given type class cannot be NULL!");
		}
		if (sendablesMap.containsKey(typeClass)) {
			throw new SendableException("The given type class is already registered!");
		}
		if (typeFactory == null) {
			throw new SendableException("The given sendable factory cannot be NULL!");
		}
		this.sendablesMap.put(typeClass, typeFactory);
	}

	/**
	 * This method returns whether the registry contains the given type class.
	 * 
	 * @param typeClass The class to check whether it is contained
	 * @return If the given class is contained in the registry
	 * @throws SendableException If an error occurs while checking
	 */
	public boolean contains(Class<? extends SendableType> typeClass) throws SendableException {
		if (typeClass == null) {
			throw new SendableException("The given type class cannot be NULL!");
		}
		return sendablesMap.containsKey(typeClass);
	}

	/**
	 * This method returns the factory associated with the given type class.
	 * 
	 * @param typeClass The class the factory is associated with
	 * @return The associated factory
	 * @throws SendableException If an error occurs while getting the factory
	 */
	public ISendableFactory<? extends SendableType> getFactory(Class<? extends SendableType> typeClass) throws SendableException {
		if (typeClass == null) {
			throw new SendableException("The given type class cannot be NULL!");
		}
		if (!sendablesMap.containsKey(typeClass)) {
			throw new SendableException("The given type class is not contained in the registry!");
		}
		return sendablesMap.get(typeClass);
	}

	/**
	 * This method returns the registry as an <i>unmodifiable</i> list.
	 * 
	 * @return The registry
	 */
	public Map<Class<? extends SendableType>, ISendableFactory<? extends SendableType>> getAsMap() {
		return Collections.unmodifiableMap(sendablesMap);
	}

}
