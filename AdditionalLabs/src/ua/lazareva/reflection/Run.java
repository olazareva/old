package ua.lazareva.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface Run {

}

@Target(value= ElementType.FIELD)
@Retention(value= RetentionPolicy.RUNTIME)
@interface Inject {
Class className() default Void.class;
}
