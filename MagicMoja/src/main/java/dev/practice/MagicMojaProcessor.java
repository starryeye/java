package dev.practice;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
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
        }

        return true; // 다음 process 로 넘기지 않음 (필터 체인 방식과 비슷)
    }
}
