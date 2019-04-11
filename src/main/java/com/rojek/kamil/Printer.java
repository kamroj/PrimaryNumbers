package com.rojek.kamil;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author Kamil Rojek
 */
class Printer {
    static void toFile(List<Integer> primaryNumbers, String path) {
        File file = new File(path);

        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fw)) {

            for (Integer primaryNumber : primaryNumbers) {
                System.out.println(primaryNumber);
                bw.write(primaryNumber.toString());
                bw.append("\n");
            }
        } catch (IOException e) {
            e.getCause();
        }

        System.out.printf("File has been saved to: %s%n", path);
    }

    static void toConsole(List<Integer> primaryNumbers){
        for (Integer number: primaryNumbers) {
            System.out.println(number);
        }
    }
}
