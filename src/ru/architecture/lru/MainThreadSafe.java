package ru.architecture.lru;

import ru.architecture.lru.calculator.CalculatorWithCacheThreadSafe;

import java.util.function.Consumer;

public class MainThreadSafe {
    public static void main(String[] args) {
        CalculatorWithCacheThreadSafe calculator = new CalculatorWithCacheThreadSafe(0, 5);
        Consumer<Integer> consumer = (x) -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + calculator.calculate(i + x));
            }
            for (int i = 9; i >= 0; i--) {
                System.out.println(Thread.currentThread().getName() + " " + calculator.calculate(i + x));
            }
        };

        Thread thread1 = new Thread(() -> consumer.accept(0));
        Thread thread2 = new Thread(() -> consumer.accept(1));
        Thread thread3 = new Thread(() -> consumer.accept(3));
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Смотрим по выводу, что в кэше нужные ключи в нужном порядке
        calculator.printKeys();
    }
}
