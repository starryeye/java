package util.bounded_buffer;

public class ConsumerTask implements Runnable {

    private final BoundedBufferQueue queue;

    public ConsumerTask(BoundedBufferQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        String data = queue.take();
    }
}
