package ru.architecture.lru;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CalculatorWithCacheThreadSafe extends Calculator {
    private final Map<Integer, Integer> cache;
    private final ReadWriteLock lock;


    public CalculatorWithCacheThreadSafe(int offset, int cacheSize) {
        super(offset);
        assert cacheSize > 0;
        cache = new LinkedMapWithCapacity<>(cacheSize);
        lock = new ReentrantReadWriteLock();
    }

    /*Используем ReadWriteLock, чтобы запись не мешала другим потокам убедиться,
    что значения нет в кеше и произвести затратные вычислиения*/
    @Override
    public int calculate(int x) {
        int result;
        Integer value;
        lock.readLock().lock();
        try {
            value = cache.getOrDefault(x, null);
        } finally {
            lock.readLock().unlock();
        }
        if(value != null) {
            result = value;
        } else {
            result = super.calculate(x);
            lock.writeLock().lock();
            try {
                cache.put(x, result);
            } finally {
                lock.writeLock().unlock();
            }
        }
        return result;
    }

    public void printKeys() {
        for (Integer key: cache.keySet()) {
            System.out.println(key + " ");
        }
    }
}
