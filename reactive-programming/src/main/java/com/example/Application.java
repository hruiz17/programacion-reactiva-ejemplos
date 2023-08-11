package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) {
        simularReactive();
    }

    public static void simularReactive() {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);

        for (int u = 1; u <= 10; u++) {
            var user = "User " + u;
            System.out.println("Submitting task " + u);
            threadPool.submit(() -> {
                Service.getFlux()
                        .map(i -> user + " - " + i)
                        .subscribe(System.out::println);
            });
        }
    }

    public static void simularPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        for (int u = 1; u <= 10; u++) {
            var usuario = "Usuario " + u;
            threadPool.submit(() -> {
                Service.getStream()
                        .mapToObj(i -> usuario + ": " + i)
                        .forEach(System.out::println);
            });
        }

        Service.getStream().forEach(System.out::println);
    }

}
