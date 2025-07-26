package util.bounded_buffer;

public interface BoundedBufferQueue {

    void put(String data);
    String take();
}
