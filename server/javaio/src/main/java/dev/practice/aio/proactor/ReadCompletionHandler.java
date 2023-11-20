package dev.practice.aio.proactor;

import java.nio.channels.CompletionHandler;

public class ReadCompletionHandler implements CompletionHandler<Integer, Void> {
    

    @Override
    public void completed(Integer result, Void attachment) {

    }

    @Override
    public void failed(Throwable exc, Void attachment) {

    }
}
