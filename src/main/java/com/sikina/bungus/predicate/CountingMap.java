package com.sikina.bungus.predicate;

import java.util.HashMap;
import java.util.Map;

/**
 * CountingMap is a hashmap that lets you decrement counts cleanly.
 * @param <K> key
 */
public class CountingMap<K> extends HashMap<K, Integer> {
    public CountingMap() {}

    public CountingMap(CountingMap<K> toClone) {
        super();
        this.putAll(toClone);
    }

    public CountingMap(Map<K, Integer> toClone) {
        super();
        this.putAll(toClone);
    }

    public void decrement(K key) {
        int count = getOrDefault(key, 0) - 1;
        if (count <= 0) {
            remove(key);
        } else {
            put(key, count);
        }
    }
}
