package dev.practice.java8.subject4_Optional.a_Optional없을때;

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

    public Progress getProgress() {
//        if(this.progress == null)
//            throw new IllegalStateException();
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}
