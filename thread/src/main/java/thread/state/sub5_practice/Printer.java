package thread.state.sub5_practice;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyThreadLog.threadLog;

public class Printer {

    /**
     * input 으로 문서이름을 여러 개 입력하면, 3초에 하나씩 FIFO 로 문서가 로그로 출력되는 Printer 코드이다.
     *
     * 참고.
     * 현재 while(!Thread.interrupted()) + isEmpty() + yield() 구조는
     * CPU를 낭비할 수 있는 busy waiting 이다. (다른 스레드에게 양보를 한다고 힌트를 주긴 했지만, RUNNABLE 상태로 CPU 점유)
     */

    public static void main(String[] args) {
        PrinterTask printerTask = new PrinterTask();
        Thread printer = new Thread(printerTask, "printer");
        printer.start();

        Scanner userInput = new Scanner(System.in);
        while (true) {
            threadLog("Enter the document you want to print. exit (q): ");
            String input = userInput.nextLine();

            if (input.equals("q")) { // q 입력 시, printer 스레드에 interrupt 발생시키고 main 스레드도 종료
                printer.interrupt();
                break;
            }

            printerTask.addJob(input);
        }
    }

    private static class PrinterTask implements Runnable {
        Queue<String> documentQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {

            threadLog("printer started");
            while (!Thread.interrupted()) {
                if (documentQueue.isEmpty()) {
                    Thread.yield(); // 프린트할 문서가 없으면, 다른 스레드에 양보하도록 힌트를 준다. (여기서의 다른 스레드란, 제3의 스레드이다.)
                    continue;
                }

                try {
                    String document = documentQueue.poll();
                    threadLog("print start: " + document + ", Waiting for print: " + documentQueue);
                    Thread.sleep(3000); // 출력에 3초가 걸린다고 가정
                    threadLog("print complete: " + document);
                } catch (InterruptedException e) {
                    threadLog("interrupted occured..");
                    break;
                }
            }
            threadLog("printer exit");
        }

        public void addJob(String input) {
            documentQueue.offer(input);
        }
    }
}
