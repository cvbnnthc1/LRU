package ru.architecture.lru.map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedMapWithCapacity<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    /*linked map с параметром accessOrder=true обеспечивает хранение ключей
    в списке в том порядке, в котором они добавлялись*/
    public LinkedMapWithCapacity(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    //при добавлении нового значения удаляем самую старую запись, если size>capacity
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
