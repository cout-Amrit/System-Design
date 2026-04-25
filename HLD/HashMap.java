package HLD;

class Entry<K, V> {
    K key;
    V value;
    Entry<K, V> next;

    public Entry(K key, V value, Entry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}

public class HashMap {
    private static final int INITIAL_CAPACITY = (1 << 4); // 16
    private static final int MAXIMUM_CAPACITY = (1 << 30);

    // static final int TREEIFY_THRESHOLD = 8;  // After that Linked List will convert into Red Black Tree in Java8+
    // static final double load_factor = 0.75;  // Once capacity reacches 0.75xcurr_cap, hashmap resizes itself

    // This hash function gives more uniform distribution (chances of collision becomes less.)
    // private static final int hash(K key) {
    //     int h;
    //     return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    // }

    private Entry<K, V>[] hash_table;

    public HashMap() {
        hash_table = new Entry[INITIAL_CAPACITY];
    }

    public HashMap(int capacity) {
        int tableSize = tableSizeFor(capacity);
        hash_table = new Entry[tableSize];
    }

    private int tableSizeFor(int capacity) {
        int n = capacity - 1;
        n |= (n >>> 1);
        n |= (n >>> 2);
        n |= (n >>> 4);
        n |= (n >>> 8);
        n |= (n >>> 16);
        return n <= 0 ? 1 : n > MAXIMUM_CAPACITY ? MAXIMUM_CAPACITY : n+1;
    }

    public void put(K key, V value) {
        int hashcode = key.hashcode() % hash_table.length;
        // int hashcode = (hash_table.length - 1) ^ hash(key);
        Entry<K, V> node = hash_table[hashcode];
        while(node != null) {
            if(node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        hash_table[hashcode] = new Entry<>(key, value, hash_table[hashcode]);
    }

    public V get(K key) {
        int hashcode = key.hashcode() % hash_table.length;
        // int hashcode = (hash_table.length - 1) ^ hash(key);
        Entry<K, V> node = hash_table[hashcode];
        while(node != null) {
            if(node.key.equals(key)) return node.value;
            node = node.next;
        }
    }
}