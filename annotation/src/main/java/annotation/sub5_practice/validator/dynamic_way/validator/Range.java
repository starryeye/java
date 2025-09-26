package annotation.sub5_practice.validator.dynamic_way.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {

    int min() default 0;
    int max() default Integer.MAX_VALUE;

    String message() default "value is out of range..";
}
