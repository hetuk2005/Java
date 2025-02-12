import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

class ProducerConsumer {
    private static final int BUFFER_SIZE = 5;
    private final Queue<Integer> buffer = new LinkedList<>();
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore empty = new Semaphore(BUFFER_SIZE);
    private final Semaphore full = new Semaphore(0);

    class Producer extends Thread {
        public void run() {
            int item = 1;
            while (true) {
                try {
                    empty.acquire();  // Wait if buffer is full
                    mutex.acquire();  // Lock buffer access

                    buffer.add(item);
                    System.out.println("Produced: " + item);
                    item++;

                    mutex.release();  // Unlock buffer
                    full.release();   // Signal consumer that buffer has data
                    Thread.sleep(1000); // Simulate production time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer extends Thread {
        public void run() {
            while (true) {
                try {
                    full.acquire();   // Wait if buffer is empty
                    mutex.acquire();  // Lock buffer access

                    int item = buffer.poll();
                    System.out.println("Consumed: " + item);

                    mutex.release();  // Unlock buffer
                    empty.release();  // Signal producer that space is available
                    Thread.sleep(1500); // Simulate consumption time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        pc.new Producer().start();
        pc.new Consumer().start();
    }
}