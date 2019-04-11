package com.rojek.kamil;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Kamil Rojek
 */
class PrimaryNumbersGenerator implements Runnable {
    private final int lowerBound;
    private final int upperBound;
    private Queue<Integer> primaryNumbers;
    private String name;

    private PrimaryNumbersGenerator(int lowerBound, int upperBound, String name) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.name = name;
    }

    static PrimaryNumbersGenerator createNumberGenerator(int lowerBound, int upperBound, String name) {
        if (upperBound <= lowerBound)
            throw new IllegalArgumentException("Upper bound must be bigger than lower bound!");
        return new PrimaryNumbersGenerator(lowerBound, upperBound, name);
    }

    List<Integer> getPrimaryNumbers(int quantity) {
        List<Integer> primaryNumbersPack = new LinkedList<>();

        for (int i = 0; i < quantity; i++) {
            if(primaryNumbers.isEmpty())
                break;
            primaryNumbersPack.add(primaryNumbers.remove());
        }
        return primaryNumbersPack;
    }

    String getName(){
        return name;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.printf("%s is looking for primes from %d to %d%n", threadName, lowerBound, upperBound);
        printPrimeNumbers(lowerBound, upperBound);
    }

    private void printPrimeNumbers(int lowerBound, int upperBound){
        primaryNumbers = new ConcurrentLinkedQueue<>();

        for (int i = lowerBound; i < upperBound; i++) {
            if (isPrime(i)) {
                //System.out.println(i);
                primaryNumbers.add(i);
            }
        }
    }

    private boolean isPrime(int n){
        if(n<2)
            return false;

        for(int i = 2; i*i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
