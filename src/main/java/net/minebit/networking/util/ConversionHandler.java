package net.minebit.networking.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;

import net.minebit.networking.exceptions.ConversionException;
import net.minebit.networking.exceptions.InvalidInputException;

/**
 * {@link ConversionHandler} is a class that handles the conversion of object to
 * bytes and vice versa.
 * 
 * @author Aggelowe
 * 
 * @since 0.1
 *
 */
public final class ConversionHandler {

	/**
	 * This method returns the result of invocation of the method contained in the
	 * class of the given object that is used to convert the given object to bytes.
	 * 
	 * @param inputObject The object to convert to bytes.
	 * @return The result of the invocation of the conversion method.
	 * @throws ConversionException   If an error occurs while the method's
	 *                               invocation takes place.
	 * @throws InvalidInputException If the class of the given object is null or
	 *                               doesn't qualify for conversions or the given
	 *                               object is null.
	 * @see ConversionHandler#getBytesMethod(Class)
	 */
	public static ByteBuffer toBytes(Object inputObject) throws ConversionException, InvalidInputException {
		if (inputObject == null) {
			throw new InvalidInputException("The given object cannot be NULL!");
		}
		Class<?> objectClass = inputObject.getClass();
		Method objectMethod = getBytesMethod(objectClass);
		ByteBuffer result;
		try {
			result = (ByteBuffer) objectMethod.invoke(null, inputObject);
		} catch (Exception exception) {
			throw new ConversionException("An error occured while invoking the conversion method.", exception);
		}
		if (result == null) {
			throw new ConversionException("The output of the invoked method cannot be NULL!");
		}
		return result;
	}

	/**
	 * This method returns the result of invocation of the method contained in the
	 * given class that is used to convert bytes to an object, which has a class
	 * type of the given class.
	 * 
	 * @param inputBytes The bytes to convert to an object.
	 * @param inputClass The class from which the conversion method can be obtained.
	 * @return The result of the invocation of the conversion method.
	 * @throws ConversionException   If an error occurs while the method's
	 *                               invocation takes place.
	 * @throws InvalidInputException If the given class is null or doesn't qualify
	 *                               for conversions or the given {@link ByteBuffer}
	 *                               is null.
	 * @see ConversionHandler#getObjectMethod(Class)
	 */
	public static Object toObject(ByteBuffer inputBytes, Class<?> inputClass) throws ConversionException, InvalidInputException {
		if (inputBytes == null) {
			throw new InvalidInputException("The given java.nio.ByteBuffer cannot be NULL!");
		}
		Method objectMethod = getObjectMethod(inputClass);
		Object result;
		inputBytes.position(0);
		try {
			result = objectMethod.invoke(null, inputBytes);
		} catch (Exception exception) {
			throw new ConversionException("An error occured while invoking the conversion method.", exception);
		}
		if (result == null) {
			throw new ConversionException("The output of the invoked method cannot be NULL!");
		}
		return result;
	}

	/**
	 * This method obtains and returns the method for byte to object conversions
	 * found inside the given glass. A qualified method must:
	 * <li>Be called <code>toBytes()</code>.
	 * <li>Be static.
	 * <li>Be public.
	 * <li>Return an object with a type of that class.
	 * <li>Have a {@link ByteBuffer} as the only parameter.
	 * 
	 * @param inputClass The class from which the method can be obtained.
	 * @return The first qualified method of the given class.
	 * @throws InvalidInputException If the given class is null or isn't qualified
	 *                               for conversions.
	 */
	public static Method getBytesMethod(Class<?> inputClass) throws InvalidInputException {
		if (inputClass == null) {
			throw new InvalidInputException("The given class cannot be NULL!");
		}
		Method method;
		try {
			method = inputClass.getDeclaredMethod("toBytes", inputClass);
		} catch (Exception exception) {
			throw new InvalidInputException("The given class isn't qualified for object to byte conversions!", exception);
		}
		if (Modifier.isStatic(method.getModifiers()) && Modifier.isPublic(method.getModifiers()) && method.getReturnType() == ByteBuffer.class) {
			return method;
		}
		throw new InvalidInputException("The given class isn't qualified for object to byte conversions!");
	}

	/**
	 * This method obtains and returns the first qualified method for object to byte
	 * conversions found inside the given glass. A qualified method must:
	 * <li>Be called <code>toObject()</code>.
	 * <li>Be static.
	 * <li>Be public.
	 * <li>Return a {@link ByteBuffer}.
	 * <li>Have an object with a type of that class as the only parameter.
	 * 
	 * @param inputClass The class from which the method can be obtained.
	 * @return The first qualified method of the given class.
	 * @throws InvalidInputException If the given class is null or isn't qualified
	 *                               for conversions.
	 */
	public static Method getObjectMethod(Class<?> inputClass) throws InvalidInputException {
		if (inputClass == null) {
			throw new InvalidInputException("The given class cannot be NULL!");
		}
		Method method;
		try {
			method = inputClass.getDeclaredMethod("toObject", ByteBuffer.class);
		} catch (Exception exception) {
			throw new InvalidInputException("The given class isn't qualified for byte to object conversions!", exception);
		}
		if (Modifier.isStatic(method.getModifiers()) && Modifier.isPublic(method.getModifiers()) && method.getReturnType() == inputClass) {
			return method;
		}
		throw new InvalidInputException("The given class isn't qualified for byte to object conversions!");
	}

}
