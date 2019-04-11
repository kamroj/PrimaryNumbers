package com.rojek.kamil;


import java.util.*;

/**
 * @author Kamil Rojek
 */
class PrimaryNumbersGetter implements Runnable{
    private final int quantity;
    private final List<PrimaryNumbersGenerator> generators;
    private List<Integer> primaryNumbers;

    PrimaryNumbersGetter(int quantity, List<PrimaryNumbersGenerator> threadsPool) {
        this.quantity = quantity;
        this.generators = threadsPool;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            getPrimaryNumbersFromThreads();

            if (primaryNumbers.isEmpty()) {
                System.out.println("Nie ma więcej generatorów. KOŃCZĘ PRACE!");
                break;
            }

            for (Integer i : primaryNumbers) {
                System.out.println("GENERATOR :" + i);
            }
        }
    }

    private void getPrimaryNumbersFromThreads(){
        primaryNumbers = new LinkedList<>();

        for (PrimaryNumbersGenerator generator: generators) {
            System.out.printf("Taking %d primary numbers from thread: %s%n", quantity, generator.getName());

            List<Integer> temp = new LinkedList<>(generator.getPrimaryNumbers(quantity));

            if(temp.isEmpty()){
                generators.remove(generator);
                System.out.printf("Generator has been removed: %s%n", generator.getName());
            }

            primaryNumbers.addAll(temp);
        }
    }
}
