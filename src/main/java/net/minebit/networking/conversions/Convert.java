package net.minebit.networking.conversions;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation is used to define a method that can be used to either convert
 * an object to bytes or vice versa.
 * 
 * @author Aggelowe
 *
 * @since 0.1
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Convert {

}
