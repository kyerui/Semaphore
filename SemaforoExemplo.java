import java.util.concurrent.Semaphore;

public class SemaforoExemplo {

    public static void main(String[] args) {
        // Cria um semáforo com 3 permissões (permite até 3 threads acessarem o recurso simultaneamente)
        Semaphore semaforo = new Semaphore(3);

        // Cria e inicia várias threads que tentarão acessar o recurso
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

                // Adquire uma permissão do semáforo
                semaforo.acquire();

                System.out.println("Thread " + id + " adquiriu uma permissão.");

                // Simula o uso do recurso compartilhado
                Thread.sleep(2000);

                System.out.println("Thread " + id + " está liberando a permissão.");

                // Libera a permissão do semáforo
                semaforo.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}