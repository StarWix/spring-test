package com.test.services;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.util.function.Function;

@Service
public class MainService {
    private volatile String userInput;

    private final ApplicationContext context;
    private final Thread[] type1Threads;
    private final Thread[] type2Threads;

    public MainService(final ApplicationContext context) {
        this.context = context;

        type1Threads = run(5, (index) -> "Thread of type1. Thread #" + index);
        type2Threads = run(5, (index) -> "Thread of type2. Thread #" + index + ". User output: " + userInput);
    }

    private static Thread[] run(final int numberOfThreads, final Function<Integer, String> outputString) {
        final Thread[] threads = new Thread[numberOfThreads];
        for (int i = 0; i < threads.length; i++) {
            final int threadIndex = i;
            threads[i] = new Thread(() -> {
                while (true) {
                    System.out.println(outputString.apply(threadIndex));
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            threads[i].setDaemon(true);
            threads[i].start();
        }
        return threads;
    }

    public void update(final String userInput) {
        if (userInput.equals("q")) {
            SpringApplication.exit(context);
            return;
        }
        this.userInput = userInput;
    }
}
