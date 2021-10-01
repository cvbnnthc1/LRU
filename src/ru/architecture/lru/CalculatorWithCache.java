package ru.architecture.lru;

import java.util.*;

public class CalculatorWithCache extends Calculator {
    private final Map<Integer, Integer> cache;

    public CalculatorWithCache(int offset, int cacheSize) {
        super(offset);
        assert cacheSize > 0;
        cache = new LinkedMapWithCapacity<>(cacheSize);
    }


    @Override
    public int calculate(int x) {
        int result;
        if(cache.containsKey(x)) {
            result = cache.get(x);
        } else {
            result = super.calculate(x);
            cache.put(x, result);
        }
        return result;
    }

    public Set<Integer> getKeySet() {
        return cache.keySet();
    }

}
