package com.rojek.kamil;

import com.rojek.kamil.primary_numbers.PrimaryNumbersGenerator;
import com.rojek.kamil.primary_numbers.PrimaryNumbersGetter;
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
    public void check_if_generated_primary_numbers_are_correct() throws InterruptedException, IOException {
        //Arrange
        PrimaryNumbersGenerator firstGenerator = PrimaryNumbersGenerator
                .createNumberGenerator(0, 1000, "1st Generator");
        PrimaryNumbersGenerator secondGenerator = PrimaryNumbersGenerator
                .createNumberGenerator(1000, 2000, "2st Generator");

        List<PrimaryNumbersGenerator> generators = new LinkedList<>(List.of(firstGenerator, secondGenerator));
        PrimaryNumbersGetter getter = new PrimaryNumbersGetter(50, generators);

        firstGenerator.start();
        secondGenerator.start();
        getter.start();
        getter.join();


        BufferedReader expected = new BufferedReader(new FileReader("./PNAssertion"));
        BufferedReader output = new BufferedReader(new FileReader("./PrimaryNumbers"));

        //Assert
        String expectedLine;
        while ((expectedLine = expected.readLine()) != null) {
            Assert.assertEquals(expectedLine, output.readLine());
        }

    }
}