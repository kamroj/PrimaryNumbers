package com.rojek.kamil;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Kamil Rojek
 */
public class PrimaryNumbersTest {

    @Test(invocationCount = 1000)
    public void check_if_genereted_primary_numbers_are_correct() throws InterruptedException, IOException {
        //Arrange
        PrimaryNumbersGenerator firstGenerator = PrimaryNumbersGenerator
                .createNumberGenerator(0, 1000, "1st Generator");
        PrimaryNumbersGenerator secondGenerator = PrimaryNumbersGenerator
                .createNumberGenerator(1000, 2000, "2st Generator");

        List<PrimaryNumbersGenerator> generators = new LinkedList<>(List.of(firstGenerator, secondGenerator));
        PrimaryNumbersGetter getter = new PrimaryNumbersGetter(50, generators);

        Thread firstPrimaryGeneratorThread = new Thread(firstGenerator, firstGenerator.getName());
        Thread secondPrimaryGeneratorThread = new Thread(secondGenerator, secondGenerator.getName());
        Thread primaryNumbersGetterThread = new Thread(getter, "Getter");

        //Act
        firstPrimaryGeneratorThread.start();
        secondPrimaryGeneratorThread.start();
        secondPrimaryGeneratorThread.join();
        primaryNumbersGetterThread.start();
        primaryNumbersGetterThread.join();

        BufferedReader expected = new BufferedReader(new FileReader("./PNAssertion"));
        BufferedReader output = new BufferedReader(new FileReader("./PrimeNumbers"));

        //Assert
        String expectedLine;
        while ((expectedLine = expected.readLine()) != null) {
            Assert.assertEquals(expectedLine, output.readLine());
        }

    }
}