package dev.practice.java8.subject4_Optional.a_Optional없을때;

import java.util.List;

public class App {

    /**
     * Optional..
     * Java 8 에 추가된 인터페이스이다.
     * 값 하나만을 담고 있을 수도.. 비어있을 수도.. 있다.
     */

    public static void main(String[] args) {

        Lecture java = new Lecture(1, "Java", true);
        Lecture spring = new Lecture(2, "Spring", true);
        Lecture jpa = new Lecture(3, "JPA", false);
        Lecture redis = new Lecture(4, "Redis", false);
        Lecture kafka = new Lecture(5, "Kafka", false);
        Lecture springBoot = new Lecture(6, "Spring Boot", false);
        List<Lecture> backend = List.of(java, spring, jpa, redis, kafka, springBoot);

        //getProgress 를 하면 null 이 리턴 된다. reference type 은 기본 값이 null 이다.
        //null 에다가 getStudyDuration() 으로.. null 을 참조하려고 하였으므로 NPE
//        Duration studyDuration = java.getProgress().getStudyDuration();

        //따라서.. 아래와 같이 처리해줘야한다.
        Progress progress = java.getProgress();
        if(progress != null) {
            System.out.println(progress.getStudyDuration());
        }
        //혹은, getProgress() 메서드 내부에 if(this.progress == null) throw new IllegalStateException(); 을 해주거나..


        //db 패키지로 가서 Optional 을 사용해보자..

    }
}
