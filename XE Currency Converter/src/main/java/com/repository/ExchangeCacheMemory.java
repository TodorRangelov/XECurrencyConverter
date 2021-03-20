package com.repository;

public interface ExchangeCacheMemory<K, V> {

    boolean isMemoryExpired(K key);

    V getValue(K key);

    void putValue(K key, V value);

}
