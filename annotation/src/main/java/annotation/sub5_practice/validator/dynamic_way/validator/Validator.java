package annotation.sub5_practice.validator.dynamic_way.validator;

import java.lang.reflect.Field;

public abstract class Validator {

    private Validator() {}

    public static void validate(Object obj) throws IllegalAccessException {

        // obj 의 전체 필드 순회
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {

            declaredField.setAccessible(true); // private 접근허용

            if (declaredField.isAnnotationPresent(NotEmpty.class)) { // @NotEmpty 적용된 필드
                NotEmpty annotation = declaredField.getAnnotation(NotEmpty.class);
                String value = (String) declaredField.get(obj);

                if (value == null || value.isBlank()) { // @NotEmpty 검증
                    throw new RuntimeException(annotation.message());
                }
            }

            if (declaredField.isAnnotationPresent(Range.class)) { // @Range 적용된 필드
                Range annotation = declaredField.getAnnotation(Range.class);
                long value = declaredField.getLong(obj);

                if (value < annotation.min() || value > annotation.max()) { // @Range 검증
                    throw new RuntimeException(annotation.message());
                }
            }
        }
    }
}
