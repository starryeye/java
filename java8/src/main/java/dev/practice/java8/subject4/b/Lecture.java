package dev.practice.java8.subject4.b;

import java.util.Optional;

public class Lecture {

    private Integer id;
    private String title;
    private boolean closed;
    private Progress progress;

    public Lecture(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /**
     * Optional 은 리턴 타입으로 쓰는 것을 추천한다.
     */
    public Optional<Progress> getProgress() {

        /**
         * null 일 수 있는 인스턴스를 리턴할 때는 Optional.ofNullable 을 사용하자
         *
         * Optional.of 는 null 이 아닌 인스턴스를 집어넣을 때 사용하는 것이다. 만약 null 을 집어넣으면 즉시 NPE 발생한다.
         */
        return Optional.ofNullable(progress);
    }

    /**
     * Optional 을 메서드 파라미터에 쓰게 된다면...
     * 아래와 같이 작성할 것이다..
     * 하지만 의미가 없다.
     * 왜냐하면..
     * 외부에서 setProgress(Optional.ofNullable(null)) 로 호출하는 것이 아닌..
     * setProgress(null) 로 호출하면 progress.ifPresent() 수행할때 NPE 가 발생할 것이다.
     * 그러면 또 if(progress != null) 코드를 추가로 작성해줘야하는 것이다.
     */
//    public void setProgress(Optional<Progress> progress) {
//        progress.ifPresent(p -> this.progress = p);
//    }
}
