package com.rojek.kamil;

import java.util.*;

/**
 * @author Kamil Rojek
 */
public class AppMain {
    public static void main(String[] args) throws InterruptedException {
        PrimaryNumbersGenerator firstGenerator = PrimaryNumbersGenerator
                                                .createNumberGenerator(0, 1000, "1st Generator");
        PrimaryNumbersGenerator secondGenerator = PrimaryNumbersGenerator
                                                .createNumberGenerator(1000, 2000, "2st Generator");

        List<PrimaryNumbersGenerator> generators = new LinkedList<>(List.of(firstGenerator, secondGenerator));
        PrimaryNumbersGetter getter = new PrimaryNumbersGetter(50, generators);

        Thread firstPrimaryGeneratorThread = new Thread(firstGenerator, firstGenerator.getName());
        Thread secondPrimaryGeneratorThread = new Thread(secondGenerator, secondGenerator.getName());
        Thread primaryNumbersGetterThread = new Thread(getter, "Getter");

        firstPrimaryGeneratorThread.start();
        secondPrimaryGeneratorThread.start();
        secondPrimaryGeneratorThread.join();
        primaryNumbersGetterThread.start();
    }
}
