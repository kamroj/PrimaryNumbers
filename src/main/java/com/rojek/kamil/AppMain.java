package com.rojek.kamil;

import com.rojek.kamil.primary_numbers.PrimaryNumbersGenerator;
import com.rojek.kamil.primary_numbers.PrimaryNumbersGetter;

import java.util.*;

/**
 * @author Kamil Rojek
 */
public class AppMain {
    public static void main(String[] args) {
        PrimaryNumbersGenerator firstGenerator = PrimaryNumbersGenerator
                                                .createNumberGenerator(0, 1000, "1st Generator");
        PrimaryNumbersGenerator secondGenerator = PrimaryNumbersGenerator
                                                .createNumberGenerator(1000, 2000, "2st Generator");

        List<PrimaryNumbersGenerator> generators = new LinkedList<>(List.of(firstGenerator, secondGenerator));
        PrimaryNumbersGetter getter = new PrimaryNumbersGetter(50, generators);

        firstGenerator.start();
        secondGenerator.start();
        getter.start();

    }
}
