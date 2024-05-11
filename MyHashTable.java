public class MyHashTable<K, V> {

     class HashNode<K, V> {
        K key;
        V value;
        public HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

     HashNode<K, V>[] chainArray;
    private int m = 11;
    private int size;

    public MyHashTable() {
        chainArray = new HashNode[m];
        size = 0;
    }

    public MyHashTable(int m) {
        this.m = m;
        chainArray = new HashNode[m];
        size = 0;
    }

    private int hash(K key) {
        int hash1 = key.hashCode();
        int hash2 = hash1 ^ (hash1 >>> 16);
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);
        if (chainArray[index] == null) {
            chainArray[index] = newNode;
        } else {
            HashNode<K, V> temp = chainArray[index];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> temp = chainArray[index];
        while (temp != null) {
            if (temp.key.equals(key)) {
                return temp.value;
            }
            temp = temp.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> prev = null;
        HashNode<K, V> current = chainArray[index];
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    chainArray[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public boolean contains(V value) {
        for (int i = 0; i < m; i++) {
            HashNode<K, V> temp = chainArray[i];
            while (temp != null) {
                if (temp.value.equals(value)) {
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (int i = 0; i < m; i++) {
            HashNode<K, V> temp = chainArray[i];
            while (temp != null) {
                if (temp.value.equals(value)) {
                    return temp.key;
                }
                temp = temp.next;
            }
        }
        return null;
    }
}
