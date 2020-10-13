package domain.external;

public interface CacheMemory<K, V> {

    boolean isMemoryExpired(K key);

    V getValue(K key);

    void putValue(K key, V value);

}
