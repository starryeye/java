package util.bounded_buffer;

public class ProducerTask implements Runnable {

    private final BoundedBufferQueue queue;
    private final String requestData;

    public ProducerTask(BoundedBufferQueue queue, String requestData) {
        this.queue = queue;
        this.requestData = requestData;
    }

    @Override
    public void run() {
        queue.put(requestData);
    }
}
