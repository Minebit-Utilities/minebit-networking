package net.minebit.networking.converting;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minebit.networking.exceptions.conversions.ObjectConversionException;
import net.minebit.networking.exceptions.general.InputException;
import net.minebit.networking.miscellaneous.Pair;

/**
 * {@link PrimitiveConverter} handles the conversion of primitives to byte
 * arrays and vice versa.
 * 
 * @author Aggelowe
 * @since 0.1
 */
public final class ObjectConverter {

	private ObjectConverter() {
	}

	/**
	 * This method returns the given primitive as a byte array.
	 * 
	 * @param input The primitive to convert to bytes
	 * @return The result of the conversion
	 * @throws ObjectConversionException If an error occurs while converting the
	 *                                   object.
	 */
	public static byte[] convertToBytes(Object input) throws ObjectConversionException {
		if (input == null) {
			throw new ObjectConversionException("The given object cannot be NULL!");
		}
		Class<?> inputClass = input.getClass();
		Pair<Method, Method> methods = null;
		try {
			methods = getConversionMethods(inputClass);
		} catch (InputException exception) {
			throw new ObjectConversionException("The conversion methods could not be obtained from the class of the given object!", exception);
		}
		Method byteMethod = methods.getFirstObject();
		byte[] result = null;
		try {
			result = (byte[]) byteMethod.invoke(null, input);
		} catch (IllegalAccessException | InvocationTargetException exception) {
			throw new ObjectConversionException("An exception was thrown while converting the given object!", exception);
		}
		if (result == null) {
			throw new ObjectConversionException("The result of the convertion cannot be NULL!");
		}
		return result;
	}

	/**
	 * This method returns the given byte array as an object.
	 * 
	 * @param input       The bytes to convert to an object
	 * @param objectClass The class of the output object
	 * @return The result of the conversion
	 * @throws ObjectConversionExceptionv If an error occurs while converting the
	 *                                    bytes
	 */
	public static Object convertToObject(byte[] input, Class<?> objectClass) throws ObjectConversionException {
		if (input == null) {
			throw new ObjectConversionException("The given bytes cannot be NULL!");
		}
		if (objectClass == null) {
			throw new ObjectConversionException("The given class cannot be NULL!");
		}
		Pair<Method, Method> methods = null;
		try {
			methods = getConversionMethods(objectClass);
		} catch (InputException exception) {
			throw new ObjectConversionException("The conversion methods could not be obtained from the given class!", exception);
		}
		Method objectMethod = methods.getSecondObject();
		Object result = null;
		try {
			result = objectMethod.invoke(null, input);
		} catch (IllegalAccessException | InvocationTargetException exception) {
			throw new ObjectConversionException("An exception was thrown while converting the given bytes!", exception);
		}
		if (result == null) {
			throw new ObjectConversionException("The result of the convertion cannot be NULL!");
		}
		return result;
	}

	/**
	 * This method returns the methods used to convert an object of the given class
	 * to bytes and vice versa.
	 * 
	 * @param inputClass The class that contains the conversion methods
	 * @return The conversion methods of the given class
	 * @throws InputException If the given class doesn't contain the methods or the
	 *                        methods are invalid.
	 */
	public static Pair<Method, Method> getConversionMethods(Class<?> inputClass) throws InputException {
		if (inputClass == null) {
			throw new InputException("The given class cannot be NULL!");
		}
		Method byteMethod;
		try {
			byteMethod = inputClass.getDeclaredMethod("toBytes", inputClass);
		} catch (NoSuchMethodException exception) {
			throw new InputException("The object conversion method is missing from the given class!", exception);
		}
		if ((byteMethod.getModifiers() & 0x9) != 0x9 || byteMethod.getReturnType() != byte[].class) {
			throw new InputException("The object conversion method of the given class doesn't have the correct modifiers or return type!");
		}
		Method objectMethod;
		try {
			objectMethod = inputClass.getDeclaredMethod("toObject", byte[].class);
		} catch (NoSuchMethodException exception) {
			throw new InputException("The byte conversion method is missing from the given class!", exception);
		}
		if ((objectMethod.getModifiers() & 0x9) != 0x9 || objectMethod.getReturnType() != inputClass) {
			throw new InputException("The byte conversion method of the given class doesn't have the correct modifiers or return type!");
		}
		Pair<Method, Method> result = new Pair<Method, Method>(byteMethod, objectMethod);
		return result;
	}

}
