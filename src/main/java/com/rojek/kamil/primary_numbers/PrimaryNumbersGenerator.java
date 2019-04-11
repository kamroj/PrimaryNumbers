package com.rojek.kamil.primary_numbers;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Kamil Rojek
 */
public class PrimaryNumbersGenerator extends Thread {
    private final int lowerBound;
    private final int upperBound;
    private Queue<Integer> primaryNumbers;

    private PrimaryNumbersGenerator(int lowerBound, int upperBound, String name) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        primaryNumbers = new ConcurrentLinkedQueue<>();
        setName(name);
    }

    public static PrimaryNumbersGenerator createNumberGenerator(int lowerBound, int upperBound, String name) {
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

    boolean isWorking(){
        return this.isAlive();
    }
    boolean hasRequiredPrimeNumbers(int quantity){
        return primaryNumbers.size() >= quantity;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.printf("%s is looking for primes from %d to %d%n", threadName, lowerBound, upperBound);
        collectPrimeNumbers(lowerBound, upperBound);
    }

    private void collectPrimeNumbers(int lowerBound, int upperBound){
        for (int i = lowerBound; i < upperBound; i++) {
            if (isPrime(i)) {
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
