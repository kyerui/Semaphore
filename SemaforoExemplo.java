import java.util.concurrent.Semaphore;

public class SemaforoExemplo {

    public static void main(String[] args) {
        Semaphore semaforo = new Semaphore(3);

        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(new Tarefa(semaforo, i));
            thread.start();
        }
    }

    static class Tarefa implements Runnable {
        private Semaphore semaforo;
        private int id;

        public Tarefa(Semaphore semaforo, int id) {
            this.semaforo = semaforo;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread " + id + " está tentando adquirir uma permissão.");

                semaforo.acquire();

                System.out.println("Thread " + id + " adquiriu uma permissão.");

                Thread.sleep(2000);

                System.out.println("Thread " + id + " está liberando a permissão.");

                semaforo.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}