package ru.architecture.lru;

import java.util.Iterator;

public class MainUnsafe {
    public static void main(String[] args) {
        CalculatorWithCache calculator = new CalculatorWithCache(0, 5);

        for (int i = 0; i < 10; i++) {
            System.out.println(calculator.calculate(i));
        }

        for (int i = 9; i >= 0; i--) {
            System.out.println(calculator.calculate(i));
        }
        Iterator<Integer> iterator = calculator.getKeySet().iterator();
        //проверим, что оставшиеся в кэше ключи соотвествуют нашим теоритическим представлениям
        assert iterator.next() == 4;
        assert iterator.next() == 3;
        assert iterator.next() == 2;
        assert iterator.next() == 1;
        assert iterator.next() == 0;
        assert !iterator.hasNext();
    }
}
