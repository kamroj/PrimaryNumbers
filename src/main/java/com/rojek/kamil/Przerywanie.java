package com.rojek.kamil;

/**
 * @author Kamil Rojek
 */
class Przerywanie {
    public static void main(String[] args) {

        Thread thread1st = new Thread(new Watek());



        thread1st.start();
        thread1st.interrupt();

    }

    static class Watek implements Runnable {

        @Override
        public void run() {
            while (true) {
//                try {
//                    System.out.printf("Working %s%n", Thread.currentThread().getName());
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.printf("Working %s%n", Thread.currentThread().getName());
                if (Thread.interrupted()) {
                    System.out.println("I was interrupted!");
                    break;
                }
            }
        }
    }
}
