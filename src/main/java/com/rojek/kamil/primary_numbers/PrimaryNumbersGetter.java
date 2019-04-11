package com.rojek.kamil.primary_numbers;


import java.util.LinkedList;
import java.util.List;

/**
 * @author Kamil Rojek
 */
public class PrimaryNumbersGetter extends Thread{
    private final int quantity;
    private final List<PrimaryNumbersGenerator> generators;
    private final List<Integer> primaryNumbers;

    public PrimaryNumbersGetter(int quantity, List<PrimaryNumbersGenerator> threadsPool) {
        this.quantity = quantity;
        this.generators = threadsPool;
        primaryNumbers = new LinkedList<>();
    }

    @Override
    public void run() {
        while(true) {
            getPrimaryNumbersFromThreads();

            if (generators.isEmpty()) {
                System.out.println("Nie ma więcej generatorów. KOŃCZĘ PRACE!");
                break;
            }
        }
        //Printer.toConsole(primaryNumbers);
        Printer.toFile(primaryNumbers, "./PrimaryNumbers");
    }

    private void getPrimaryNumbersFromThreads(){
        for (PrimaryNumbersGenerator generator: generators) {
            System.out.printf("Taking %d primary numbers from thread: %s%n", quantity, generator.getName());

            while (true) {
                if (generator.hasRequiredPrimeNumbers(quantity)) {
                    primaryNumbers.addAll(generator.getPrimaryNumbers(quantity));
                    break;
                } else if (!generator.isWorking() && !generator.hasRequiredPrimeNumbers(quantity)) {
                    primaryNumbers.addAll(generator.getPrimaryNumbers(quantity));

                    System.out.printf("Generator has been removed: %s%n", generator.getName());
                    generators.remove(generator);
                    break;
                }
            }
        }
    }
}
