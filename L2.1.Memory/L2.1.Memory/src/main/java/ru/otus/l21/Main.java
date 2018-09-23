package ru.otus.l21;

import java.lang.management.ManagementFactory;

public class Main {

    public static void main(String... args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int size = 20_000_000;

        System.out.println("Starting the loop");
        while (true) {
            System.gc();
            Thread.sleep(10);

            Runtime runtime = Runtime.getRuntime();
            long mem = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("1st probe: " + mem + " bytes");

            Object[] array = new Object[size];
            System.out.println("New array of size: " + array.length + " created");


            long mem2 = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("2nd probe: " + (mem2 - mem) / size + " bytes");

            for (int i = 0; i < size; i++) {
                array[i] = new Object();
                //array[i] = new String(""); //String pool
                //array[i] = new String(new char[0]); //without String pool
                //array[i] = new MyClass();
            }
            System.out.println("Created " + size + " objects.");


            long mem3 = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("3rd probe: " + (mem3 - mem2) / size + " bytes");

            Thread.sleep(1000); //wait for 1 sec
        }
    }
}
