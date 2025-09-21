package reflection.sub5_practice;

import java.lang.reflect.Field;

public class AvoidNull2 {

    /**
     * 요구사항.
     * Article, Comment 객체에 어떤 이유에서 필드 값이 null 로 채워져있고..
     * 해당 객체의 데이터를 어딘가 저장해야하는데.. 이를 빈값으로라도 채워서 null 인 필드를 없애야 한다고치자..
     *
     *
     * reflection 을 사용하여 "동적" 방식으로 개발한다면...
     * 필드 이름을 한땀한땀 코드에 녹일 필요없이 효율적인 개발이 가능하다.
     */

    public static void main(String[] args) throws IllegalAccessException {

        // given
        Article article = new Article(1L, null, null);
        Comment comment = new Comment(1L, null);
        System.out.println("======= before =======");
        System.out.println("article = " + article);
        System.out.println("comment = " + comment);


        // null 회피 코드
        nullFieldToDefault(article);
        nullFieldToDefault(comment);


        System.out.println("======= after =======");
        System.out.println("article = " + article);
        System.out.println("comment = " + comment);
    }

    public static void nullFieldToDefault(Object target) throws IllegalAccessException {

        // target 클래스 타입의 Class 객체(메타데이터)를 이용하여, target 클래스의 필드 정보를 얻는다.
        Class<?> targetClassMetadata = target.getClass();
        Field[] declaredFields = targetClassMetadata.getDeclaredFields();

        for (Field field : declaredFields) { // target 클래스 타입의 필드 순회
            field.setAccessible(true); // private 접근제어자라도 변경가능하도록 설정

            if (field.get(target) != null) { // target 인스턴스의 해당 필드 값이 null 이 아니면 스킵
                continue;
            }

            // target 인스턴스의 해당 필드 값이 null 이면..
            if (field.getType() == String.class) {
                field.set(target, ""); // 기본값 셋팅
            } else if (field.getType() == Long.class) {
                field.set(target, 0L); // 기본값 셋팅
            }
        }
    }
}
