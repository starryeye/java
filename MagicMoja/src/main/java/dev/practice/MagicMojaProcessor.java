package dev.practice;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Set;

@AutoService(value = Processor.class) // MagicMojaProcessor 를 컴파일 타임의 프로세서로 편하게 등록, google auto-service 라이브러리 사용
public class MagicMojaProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(Magic.class.getName()); // 어떤 어노테이션을 처리할 것인가...
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        /**
         * 해당 어노테이션이 적절한 곳에 붙어있는지 확인작업
         */
        // @Magic 어노테이션이 붙어있는 element 를 모두 가져온다. (element 는 소스코드를 구성하는 요소들이다.)
        // package dev.practice; 는 패키지 element
        // public class MagicMojaProcessor extends AbstractProcessor 는 클래스 element
        // public Set<String> getSupportedAnnotationTypes() 는 메서드 element ...
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Magic.class);
        for (Element element : elements) {
            // 해당 element 가 인터페이스 element 가 아니면 컴파일 에러 발생 (인터페이스에 @Magic 이 붙어있을 경우를 작업해줄것이므로)
            if(element.getKind() != ElementKind.INTERFACE) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Magic annotation can not be used on : " + element.getSimpleName()); // 컴파일 에러 발생시킴
            }else {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing : " + element.getSimpleName()); // 컴파일 시 로그 남기기
            }

            TypeElement typeElement = (TypeElement) element;
            ClassName className = ClassName.get(typeElement); // javapoet 라이브러리, ClassName 으로 클래스에 대한 여러가지 정보를 조회할 수 있다.

            MethodSpec pullOut = MethodSpec.methodBuilder("pullOut") // javapoet 라이브러리, 메서드를 생성해냄, 조작 대상은 pullOut 메서드
                    .addModifiers(Modifier.PUBLIC) // 조작 대상의 접근제어자는 public
                    .returns(String.class) // 조작 대상의 리턴 타입은 String
                    .addStatement("return $S", "Rabbit!!") // 조작 행위
                    .build();

            // 이때 생성하는 클래스는 구현체이다. 이 라이브러리를 사용하는 쪽에서는 어떤 인터페이스만 정의해놓고 있을 것이고.. 구현체의 코드는 없다.
            TypeSpec magicMoja = TypeSpec.classBuilder("MagicMoja") // javapoet 라이브러리, 클래스슬 생성해냄, 생성할 클래스 이름..
                    .addModifiers(Modifier.PUBLIC) // 생성할 클래스의 접근제어자는 public
                    .addSuperinterface(className) // 생성할 클래스는 className(@Moja 어노테이션 붙어있는 인터페이스) 이라는 인터페이스를 상속한다.
                    .addMethod(pullOut) // 생성할 클래스의 메서드 추가하기
                    .build();

            Filer filer = processingEnv.getFiler();
            try {
                JavaFile.builder(className.packageName(), magicMoja) // javapoet 라이브러리, 위에서 만든 magicMoja 를 해당 패키지 안에 만들겠다.
                        .build()
                        .writeTo(filer); // 실제 소스코드 생성
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "FATAL ERROR : " + e); // 실패시 컴파일 에러 프린트
            }

        }

        return true; // 다음 process 로 넘기지 않음 (필터 체인 방식과 비슷)
    }
}
