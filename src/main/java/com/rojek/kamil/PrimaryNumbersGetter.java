package com.rojek.kamil;


import java.util.LinkedList;
import java.util.List;

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
        primaryNumbers = new LinkedList<>();
    }

    @Override
    public void run() {
        while(true) {
            boolean isEmpty = getPrimaryNumbersFromThreads();

            if (isEmpty) {
                System.out.println("Nie ma więcej generatorów. KOŃCZĘ PRACE!");
                break;
            }
        }
        Printer.toConsole(primaryNumbers);
        Printer.toFile(primaryNumbers, "./PrimaryNumbers");
    }

    private boolean getPrimaryNumbersFromThreads(){
        List<Integer> temp = new LinkedList<>();

        for (PrimaryNumbersGenerator generator: generators) {
            System.out.printf("Taking %d primary numbers from thread: %s%n", quantity, generator.getName());

            temp.clear();
            temp.addAll(generator.getPrimaryNumbers(quantity));

            if(temp.isEmpty()){
                generators.remove(generator);
                System.out.printf("Generator has been removed: %s%n", generator.getName());
                continue;
            }

            primaryNumbers.addAll(temp);
        }
        return temp.isEmpty();
    }

//    private void writeResultsToFile() {
//        File file = new File("./Storage.txt");
//        try (FileWriter fw = new FileWriter(file);
//             BufferedWriter bw = new BufferedWriter(fw)) {
//
//            for (Integer primaryNumber : primaryNumbers) {
//                System.out.println(primaryNumber);
//                bw.write(primaryNumber.toString());
//                bw.append("\n");
//            }
//        } catch (IOException e) {
//            e.getCause();
//        }
//    }
}
