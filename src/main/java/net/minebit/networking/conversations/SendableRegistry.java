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
public class SendableRegistry<SendableType extends AbstractSendable> {

	private final Object mutex;

	private final Map<Class<? extends SendableType>, Pair<ISendableFactory<? extends SendableType>, Short>> registryMap;

	/**
	 * This constructor constructs a new container for classes of sendables to be
	 * stored along with their factories.
	 */
	public SendableRegistry() {
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
	public boolean containsIndex(short index) {
		synchronized (this.mutex) {
			for (Pair<ISendableFactory<? extends SendableType>, Short> pair : this.registryMap.values()) {
				short pairIndex = pair.getSecondObject();
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
	 * @param index The {@link ISendableFactory} to check if it is contained
	 * @return Whether the registry contains the factory
	 * @throws SendableRegistryException If an error occurs while checking whether
	 *                                   the factory is contained
	 */
	public boolean containsFactory(ISendableFactory<? extends SendableType> factory) throws SendableRegistryException {
		synchronized (this.mutex) {
			if (factory == null) {
				throw new SendableRegistryException("The given factory cannot be NULL!");
			}
			return this.containsFactoryUnchecked(factory);
		}
	}

	/**
	 * This method returns whether the registry contains the given sendable factory
	 * without checking if the given factory is valid.
	 * 
	 * @param index The {@link ISendableFactory} to check if it is contained
	 * @return Whether the registry contains the factory
	 */
	@Deprecated
	public boolean containsFactoryUnchecked(ISendableFactory<? extends SendableType> factory) {
		for (Pair<ISendableFactory<? extends SendableType>, Short> pair : this.registryMap.values()) {
			ISendableFactory<? extends SendableType> pairFactory = pair.getFirstObject();
			if (pairFactory == factory) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method returns whether the registry contains the given type class.
	 * 
	 * @param index The type class to check if it is contained
	 * @return Whether the registry contains the type class
	 * @throws SendableRegistryException If an error occurs while checking whether
	 *                                   the type class is contained
	 */
	public boolean containsTypeClass(Class<? extends SendableType> typeClass) throws SendableRegistryException {
		synchronized (this.mutex) {
			if (typeClass == null) {
				throw new SendableRegistryException("The given type class cannot be NULL!");
			}
			return this.containsTypeClassUnchecked(typeClass);
		}
	}

	/**
	 * This method returns whether the registry contains the given type class
	 * without checking if the given class is valid.
	 * 
	 * @param index The type class to check if it is contained
	 * @return Whether the registry contains the type class
	 */
	@Deprecated
	public boolean containsTypeClassUnchecked(Class<? extends SendableType> typeClass) {
		return this.registryMap.containsKey(typeClass);
	}

	/**
	 * This method registers the given class with the given index at the registry
	 * along the construction factory.
	 * 
	 * @param index           The index representing the sendable class
	 * @param sendableClass   The sendable class to register
	 * @param sendableFactory The construction factory used to construct the
	 *                        sendable
	 * @throws SendableRegistryException If an error occurs while registering the
	 *                                   given objects.
	 */
	public void register(short index, Class<? extends SendableType> sendableClass, ISendableFactory<? extends SendableType> sendableFactory) throws SendableRegistryException {
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
			this.registerUnchecked(index, sendableClass, sendableFactory);
		}
	}

	/**
	 * This method registers the given class with the given index at the registry
	 * along the construction factory without checking if the given values are
	 * valid.
	 * 
	 * @param index           The index representing the sendable class
	 * @param sendableClass   The sendable class to register
	 * @param sendableFactory The construction factory used to construct the
	 *                        sendable
	 */
	@Deprecated
	public void registerUnchecked(short index, Class<? extends SendableType> sendableClass, ISendableFactory<? extends SendableType> sendableFactory) {
		Pair<ISendableFactory<? extends SendableType>, Short> registryPair = new Pair<ISendableFactory<? extends SendableType>, Short>(sendableFactory, index);
		this.registryMap.put(sendableClass, registryPair);
	}

	/**
	 * This method returns the type class associated with the given index.
	 * 
	 * @param index The index of the type class
	 * @return The associated type class
	 * @throws SendableRegistryException If an error occurs while getting the type
	 *                                   class
	 */
	public Class<? extends SendableType> getTypeClass(short index) throws SendableRegistryException {
		synchronized (this.mutex) {
			Class<? extends SendableType> result = this.getTypeClassUnchecked(index);
			if (result == null) {
				throw new SendableRegistryException("No type class with that index is contained in the registry!");
			}
			return result;
		}
	}

	/**
	 * This method returns the type class associated with the given index without
	 * checking if the given index is valid.
	 * 
	 * @param index The index of the type class
	 * @return The associated type class
	 */
	@Deprecated
	public Class<? extends SendableType> getTypeClassUnchecked(short index) {
		for (Class<? extends SendableType> typeClass : this.registryMap.keySet()) {
			Pair<ISendableFactory<? extends SendableType>, Short> pair = this.registryMap.get(typeClass);
			short pairIndex = pair.getSecondObject();
			if (pairIndex == index) {
				return typeClass;
			}
		}
		return null;
	}

	/**
	 * This method returns the index associated with the given type class.
	 * 
	 * @param index The type class associated with the index
	 * @return The associated index
	 * @throws SendableRegistryException If an error occurs while getting the index
	 */
	public short getIndex(Class<? extends SendableType> typeClass) throws SendableRegistryException {
		synchronized (this.mutex) {
			if (!this.containsTypeClass(typeClass)) {
				throw new SendableRegistryException("The given type class is not contained in the registry!");
			}
			return this.getIndexUnchecked(typeClass);
		}
	}

	/**
	 * This method returns the index associated with the given type class without
	 * checking if the given type class is valid.
	 * 
	 * @param index The type class associated with the index
	 * @return The associated index
	 */
	@Deprecated
	public short getIndexUnchecked(Class<? extends SendableType> typeClass) {
		Pair<ISendableFactory<? extends SendableType>, Short> pair = this.registryMap.get(typeClass);
		return pair.getSecondObject();
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
			return this.getFactoryUnchecked(typeClass);
		}
	}

	/**
	 * This method returns the construction factory associated with the given type
	 * class without checking if the given class is valid.
	 * 
	 * @param typeClass The type class associated with the factory
	 * @return The associated factory
	 */
	@Deprecated
	public ISendableFactory<? extends SendableType> getFactoryUnchecked(Class<? extends SendableType> typeClass) {
		Pair<ISendableFactory<? extends SendableType>, Short> pair = this.registryMap.get(typeClass);
		return pair.getFirstObject();
	}

	/**
	 * This method returns the construction factory associated with the given index.
	 * 
	 * @param typeClass The index associated with the factory
	 * @return The associated factory
	 * @throws SendableRegistryException If an error occurs while getting the
	 *                                   factory
	 */
	public ISendableFactory<? extends SendableType> getFactory(short index) throws SendableRegistryException {
		synchronized (this.mutex) {
			ISendableFactory<? extends SendableType> result = this.getFactoryUnchecked(index);
			if (result == null) {
				throw new SendableRegistryException("The given index is not contained in the registry!");
			}
			return result;
		}
	}

	/**
	 * This method returns the construction factory associated with the given index
	 * without checking if the given index is valid.
	 * 
	 * @param typeClass The index associated with the factory
	 * @return The associated factory
	 */
	@Deprecated
	public ISendableFactory<? extends SendableType> getFactoryUnchecked(short index) {
		for (Pair<ISendableFactory<? extends SendableType>, Short> pair : this.registryMap.values()) {
			short pairIndex = pair.getSecondObject();
			if (pairIndex == index) {
				return pair.getFirstObject();
			}
		}
		return null;
	}

	/**
	 * This method returns the registry as a <i>read-only</i> map.
	 * 
	 * @return The registry
	 */
	public Map<Class<? extends SendableType>, Pair<ISendableFactory<? extends SendableType>, Short>> getAsMap() {
		return Collections.unmodifiableMap(this.registryMap);
	}
}
