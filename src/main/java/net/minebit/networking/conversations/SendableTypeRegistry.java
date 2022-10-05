package net.minebit.networking.conversations;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.minebit.networking.exceptions.conversations.SendableRegistryException;
import net.minebit.networking.miscellaneous.Pair;

/**
 * This class represents a container that contains the classes of sendables
 * along with their factories to construct them.
 * 
 * @author Aggelowe
 * @since 0.1
 *
 */
public class SendableTypeRegistry<SendableType extends AbstractSendable> {

	private final Object mutex;

	private final Map<Class<? extends SendableType>, Pair<ISendableFactory<? extends SendableType>, Integer>> registryMap;

	/**
	 * This constructor constructs a new container for classes of sendables to be
	 * stored along with their factories.
	 */
	public SendableTypeRegistry() {
		this.mutex = new Object();
		this.registryMap = new HashMap<>();
	}

	/**
	 * This method returns whether the registry contains a type class with the given
	 * index.
	 * 
	 * @param index The index to check if it is contained
	 * @return Whether the registry contains the index
	 */
	public boolean containsIndex(int index) {
		synchronized (this.mutex) {
			for (Pair<ISendableFactory<? extends SendableType>, Integer> pair : this.registryMap.values()) {
				int pairIndex = pair.getSecondObject();
				if (pairIndex == index) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * This method returns whether the registry contains the given sendable factory.
	 * 
	 * @param factory The {@link ISendableFactory} to check if it is contained
	 * @return Whether the registry contains the factory
	 * @throws SendableRegistryException If an error occurs while checking whether
	 *                                   the factory is contained
	 */
	public boolean containsFactory(ISendableFactory<? extends SendableType> factory) throws SendableRegistryException {
		synchronized (this.mutex) {
			if (factory == null) {
				throw new SendableRegistryException("The given factory cannot be NULL!");
			}
			for (Pair<ISendableFactory<? extends SendableType>, Integer> pair : this.registryMap.values()) {
				ISendableFactory<? extends SendableType> pairFactory = pair.getFirstObject();
				if (pairFactory == factory) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * This method returns whether the registry contains the given type class.
	 * 
	 * @param typeClass The type class to check if it is contained
	 * @return Whether the registry contains the type class
	 * @throws SendableRegistryException If an error occurs while checking whether
	 *                                   the type class is contained
	 */
	public boolean containsTypeClass(Class<? extends SendableType> typeClass) throws SendableRegistryException {
		synchronized (this.mutex) {
			if (typeClass == null) {
				throw new SendableRegistryException("The given type class cannot be NULL!");
			}
			return this.registryMap.containsKey(typeClass);
		}
	}

	/**
	 * This method registers the given class with the given index at the registry
	 * along the construction factory.
	 * 
	 * @param index           The index representing the sendable class
	 * @param sendableClass   The sendable class to register
	 * @param sendableFactory The construction factory used to construct the
	 *                        sendable
	 * @param <InputType>     The class type of the type class and the factory
	 * @throws SendableRegistryException If an error occurs while registering the
	 *                                   given objects.
	 */
	public <InputType extends SendableType> void register(int index, Class<InputType> sendableClass, ISendableFactory<InputType> sendableFactory) throws SendableRegistryException {
		synchronized (this.mutex) {
			if (sendableClass == null) {
				throw new SendableRegistryException("The given sendable class cannot be NULL!");
			}
			if (sendableFactory == null) {
				throw new SendableRegistryException("The given factory cannot be NULL!");
			}
			if (this.registryMap.containsKey(sendableClass)) {
				throw new SendableRegistryException("An entry with the given sendable class already exists!");
			}
			if (this.containsIndex(index)) {
				throw new SendableRegistryException("An entry with the given index already exists!");

			}
			Pair<ISendableFactory<? extends SendableType>, Integer> registryPair = new Pair<ISendableFactory<? extends SendableType>, Integer>(sendableFactory, index);
			this.registryMap.put(sendableClass, registryPair);
		}
	}

	/**
	 * This method returns the type class associated with the given index.
	 * 
	 * @param index The index of the type class
	 * @return The associated type class
	 * @throws SendableRegistryException If an error occurs while getting the type
	 *                                   class
	 */
	public Class<? extends SendableType> getTypeClass(int index) throws SendableRegistryException {
		synchronized (this.mutex) {
			for (Class<? extends SendableType> typeClass : this.registryMap.keySet()) {
				Pair<ISendableFactory<? extends SendableType>, Integer> pair = this.registryMap.get(typeClass);
				int pairIndex = pair.getSecondObject();
				if (pairIndex == index) {
					return typeClass;
				}
			}
			throw new SendableRegistryException("No type class with that index is contained in the registry!");
		}
	}

	/**
	 * This method returns the index associated with the given type class.
	 * 
	 * @param typeClass The type class associated with the index
	 * @return The associated index
	 * @throws SendableRegistryException If an error occurs while getting the index
	 */
	public int getIndex(Class<? extends SendableType> typeClass) throws SendableRegistryException {
		synchronized (this.mutex) {
			if (!this.containsTypeClass(typeClass)) {
				throw new SendableRegistryException("The given type class is not contained in the registry!");
			}
			Pair<ISendableFactory<? extends SendableType>, Integer> pair = this.registryMap.get(typeClass);
			return pair.getSecondObject();
		}
	}

	/**
	 * This method returns the construction factory associated with the given type
	 * class.
	 * 
	 * @param typeClass The type class associated with the factory
	 * @return The associated factory
	 * @throws SendableRegistryException If an error occurs while getting the
	 *                                   factory
	 */
	public ISendableFactory<? extends SendableType> getFactory(Class<? extends SendableType> typeClass) throws SendableRegistryException {
		synchronized (this.mutex) {
			if (!this.containsTypeClass(typeClass)) {
				throw new SendableRegistryException("The given type class is not contained in the registry!");
			}
			Pair<ISendableFactory<? extends SendableType>, Integer> pair = this.registryMap.get(typeClass);
			return pair.getFirstObject();
		}
	}

	/**
	 * This method returns the construction factory associated with the given index.
	 * 
	 * @param typeClass The index associated with the factory
	 * @return The associated factory
	 * @throws SendableRegistryException If an error occurs while getting the
	 *                                   factory
	 */
	public ISendableFactory<? extends SendableType> getFactory(int index) throws SendableRegistryException {
		synchronized (this.mutex) {
			for (Pair<ISendableFactory<? extends SendableType>, Integer> pair : this.registryMap.values()) {
				int pairIndex = pair.getSecondObject();
				if (pairIndex == index) {
					return pair.getFirstObject();
				}
			}
			throw new SendableRegistryException("The given index is not contained in the registry!");
		}
	}

	/**
	 * This method returns the registry as a <i>read-only</i> map.
	 * 
	 * @return The registry
	 */
	public Map<Class<? extends SendableType>, Pair<ISendableFactory<? extends SendableType>, Integer>> getAsMap() {
		return Collections.unmodifiableMap(this.registryMap);
	}
}
