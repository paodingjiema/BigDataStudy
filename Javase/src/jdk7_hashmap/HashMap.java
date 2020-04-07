///*
// * Copyright (c) 1997, 2010, Oracle and/or its affiliates. All rights reserved.
// * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// */
//
//package jdk7_hashmap;
//import java.io.*;
//import java.util.*;
//public class HashMap<K,V>
//    extends AbstractMap<K,V>
//    implements Map<K,V>, Cloneable, Serializable
//{
//
//    static final int DEFAULT_INITIAL_CAPACITY = 16;
//
//    static final int MAXIMUM_CAPACITY = 1 << 30;
//
//    static final float DEFAULT_LOAD_FACTOR = 0.75f;
//
//    transient Entry<K,V>[] table;
//
//    transient int size;
//
//
//    int threshold;
//
//
//    final float loadFactor;
//
//
//    transient int modCount;
//
//
//    static final int ALTERNATIVE_HASHING_THRESHOLD_DEFAULT = Integer.MAX_VALUE;
//
//
//    private static class Holder {
//
//            // Unsafe mechanics
//
//        static final sun.misc.Unsafe UNSAFE;
//
//
//        static final long HASHSEED_OFFSET;
//
//
//        static final int ALTERNATIVE_HASHING_THRESHOLD;
//
//        static {
//            String altThreshold = java.security.AccessController.doPrivileged(
//                new sun.security.action.GetPropertyAction(
//                    "jdk.map.althashing.threshold"));
//
//            int threshold;
//            try {
//                threshold = (null != altThreshold)
//                        ? Integer.parseInt(altThreshold)
//                        : ALTERNATIVE_HASHING_THRESHOLD_DEFAULT;
//
//                // disable alternative hashing if -1
//                if (threshold == -1) {
//                    threshold = Integer.MAX_VALUE;
//                }
//
//                if (threshold < 0) {
//                    throw new IllegalArgumentException("value must be positive integer.");
//                }
//            } catch(IllegalArgumentException failed) {
//                throw new Error("Illegal value for 'jdk.map.althashing.threshold'", failed);
//            }
//            ALTERNATIVE_HASHING_THRESHOLD = threshold;
//
//            try {
//                UNSAFE = sun.misc.Unsafe.getUnsafe();
//                HASHSEED_OFFSET = UNSAFE.objectFieldOffset(
//                    HashMap.class.getDeclaredField("hashSeed"));
//            } catch (NoSuchFieldException | SecurityException e) {
//                throw new Error("Failed to record hashSeed offset", e);
//            }
//        }
//    }
//
//
//    transient boolean useAltHashing;
//
//
//    transient final int hashSeed = sun.misc.Hashing.randomHashSeed(this);
//
//
//    public HashMap(int initialCapacity, float loadFactor) {
//        if (initialCapacity < 0)
//            throw new IllegalArgumentException("Illegal initial capacity: " +
//                                               initialCapacity);
//        if (initialCapacity > MAXIMUM_CAPACITY)
//            initialCapacity = MAXIMUM_CAPACITY;
//        if (loadFactor <= 0 || Float.isNaN(loadFactor))
//            throw new IllegalArgumentException("Illegal load factor: " +
//                                               loadFactor);
//
//        // Find a power of 2 >= initialCapacity
//        int capacity = 1;
//        while (capacity < initialCapacity)
//            capacity <<= 1;
//
//        this.loadFactor = loadFactor;
//        threshold = (int)Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);
//        table = new Entry[capacity];
//        useAltHashing = sun.misc.VM.isBooted() &&
//                (capacity >= Holder.ALTERNATIVE_HASHING_THRESHOLD);
//        init();
//    }
//
//
//    public HashMap(int initialCapacity) {
//        this(initialCapacity, DEFAULT_LOAD_FACTOR);
//    }
//
//
//    public HashMap() {
//        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
//    }
//
//
//    public HashMap(Map<? extends K, ? extends V> m) {
//        this(Math.max((int) (m.size() / DEFAULT_LOAD_FACTOR) + 1,
//                      DEFAULT_INITIAL_CAPACITY), DEFAULT_LOAD_FACTOR);
//        putAllForCreate(m);
//    }
//
//    // internal utilities
//
//
//    void init() {
//    }
//
//
//    final int hash(Object k) {
//        int h = 0;
//        if (useAltHashing) {
//            if (k instanceof String) {
//                return sun.misc.Hashing.stringHash32((String) k);
//            }
//            h = hashSeed;
//        }
//
//        h ^= k.hashCode();
//
//
//        h ^= (h >>> 20) ^ (h >>> 12);
//        return h ^ (h >>> 7) ^ (h >>> 4);
//    }
//
//
//    static int indexFor(int h, int length) {
//        return h & (length-1);
//    }
//
//
//    public int size() {
//        return size;
//    }
//
//
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//
//    public V get(Object key) {
//        if (key == null)
//            return getForNullKey();
//        Entry<K,V> entry = getEntry(key);
//
//        return null == entry ? null : entry.getValue();
//    }
//
//
//    private V getForNullKey() {
//        for (Entry<K,V> e = table[0]; e != null; e = e.next) {
//            if (e.key == null)
//                return e.value;
//        }
//        return null;
//    }
//
//
//    public boolean containsKey(Object key) {
//        return getEntry(key) != null;
//    }
//
//
//    final Entry<K,V> getEntry(Object key) {
//        int hash = (key == null) ? 0 : hash(key);
//        for (Entry<K,V> e = table[indexFor(hash, table.length)];
//             e != null;
//             e = e.next) {
//            Object k;
//            if (e.hash == hash &&
//                ((k = e.key) == key || (key != null && key.equals(k))))
//                return e;
//        }
//        return null;
//    }
//
//
//
//    public V put(K key, V value) {
//        if (key == null)
//            return putForNullKey(value);
//        int hash = hash(key);
//        int i = indexFor(hash, table.length);
//        for (Entry<K,V> e = table[i]; e != null; e = e.next) {
//            Object k;
//            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
//                V oldValue = e.value;
//                e.value = value;
//                e.recordAccess(this);
//                return oldValue;
//            }
//        }
//
//        modCount++;
//        addEntry(hash, key, value, i);
//        return null;
//    }
//
//
//    private V putForNullKey(V value) {
//        for (Entry<K,V> e = table[0]; e != null; e = e.next) {
//            if (e.key == null) {
//                V oldValue = e.value;
//                e.value = value;
//                e.recordAccess(this);
//                return oldValue;
//            }
//        }
//        modCount++;
//        addEntry(0, null, value, 0);
//        return null;
//    }
//
//
//    private void putForCreate(K key, V value) {
//        int hash = null == key ? 0 : hash(key);
//        int i = indexFor(hash, table.length);
//
//
//        for (Entry<K,V> e = table[i]; e != null; e = e.next) {
//            Object k;
//            if (e.hash == hash &&
//                ((k = e.key) == key || (key != null && key.equals(k)))) {
//                e.value = value;
//                return;
//            }
//        }
//
//        createEntry(hash, key, value, i);
//    }
//
//    private void putAllForCreate(Map<? extends K, ? extends V> m) {
//        for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
//            putForCreate(e.getKey(), e.getValue());
//    }
//
//
//    void resize(int newCapacity) {
//        Entry[] oldTable = table;
//        int oldCapacity = oldTable.length;
//        if (oldCapacity == MAXIMUM_CAPACITY) {
//            threshold = Integer.MAX_VALUE;
//            return;
//        }
//
//        Entry[] newTable = new Entry[newCapacity];
//        boolean oldAltHashing = useAltHashing;
//        useAltHashing |= sun.misc.VM.isBooted() &&
//                (newCapacity >= Holder.ALTERNATIVE_HASHING_THRESHOLD);
//        boolean rehash = oldAltHashing ^ useAltHashing;
//        transfer(newTable, rehash);
//        table = newTable;
//        threshold = (int)Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
//    }
//
//
//    void transfer(Entry[] newTable, boolean rehash) {
//        int newCapacity = newTable.length;
//        for (Entry<K,V> e : table) {
//            while(null != e) {
//                Entry<K,V> next = e.next;
//                if (rehash) {
//                    e.hash = null == e.key ? 0 : hash(e.key);
//                }
//                int i = indexFor(e.hash, newCapacity);
//                e.next = newTable[i];
//                newTable[i] = e;
//                e = next;
//            }
//        }
//    }
//
//
//    public void putAll(Map<? extends K, ? extends V> m) {
//        int numKeysToBeAdded = m.size();
//        if (numKeysToBeAdded == 0)
//            return;
//
//
//        if (numKeysToBeAdded > threshold) {
//            int targetCapacity = (int)(numKeysToBeAdded / loadFactor + 1);
//            if (targetCapacity > MAXIMUM_CAPACITY)
//                targetCapacity = MAXIMUM_CAPACITY;
//            int newCapacity = table.length;
//            while (newCapacity < targetCapacity)
//                newCapacity <<= 1;
//            if (newCapacity > table.length)
//                resize(newCapacity);
//        }
//
//        for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
//            put(e.getKey(), e.getValue());
//    }
//
//
//    public V remove(Object key) {
//        Entry<K,V> e = removeEntryForKey(key);
//        return (e == null ? null : e.value);
//    }
//
//
//    final Entry<K,V> removeEntryForKey(Object key) {
//        int hash = (key == null) ? 0 : hash(key);
//        int i = indexFor(hash, table.length);
//        Entry<K,V> prev = table[i];
//        Entry<K,V> e = prev;
//
//        while (e != null) {
//            Entry<K,V> next = e.next;
//            Object k;
//            if (e.hash == hash &&
//                ((k = e.key) == key || (key != null && key.equals(k)))) {
//                modCount++;
//                size--;
//                if (prev == e)
//                    table[i] = next;
//                else
//                    prev.next = next;
//                e.recordRemoval(this);
//                return e;
//            }
//            prev = e;
//            e = next;
//        }
//
//        return e;
//    }
//
//
//    final Entry<K,V> removeMapping(Object o) {
//        if (!(o instanceof Map.Entry))
//            return null;
//
//        Map.Entry<K,V> entry = (Map.Entry<K,V>) o;
//        Object key = entry.getKey();
//        int hash = (key == null) ? 0 : hash(key);
//        int i = indexFor(hash, table.length);
//        Entry<K,V> prev = table[i];
//        Entry<K,V> e = prev;
//
//        while (e != null) {
//            Entry<K,V> next = e.next;
//            if (e.hash == hash && e.equals(entry)) {
//                modCount++;
//                size--;
//                if (prev == e)
//                    table[i] = next;
//                else
//                    prev.next = next;
//                e.recordRemoval(this);
//                return e;
//            }
//            prev = e;
//            e = next;
//        }
//
//        return e;
//    }
//
//
//    public void clear() {
//        modCount++;
//        Entry[] tab = table;
//        for (int i = 0; i < tab.length; i++)
//            tab[i] = null;
//        size = 0;
//    }
//
//
//    public boolean containsValue(Object value) {
//        if (value == null)
//            return containsNullValue();
//
//        Entry[] tab = table;
//        for (int i = 0; i < tab.length ; i++)
//            for (Entry e = tab[i] ; e != null ; e = e.next)
//                if (value.equals(e.value))
//                    return true;
//        return false;
//    }
//
//
//    private boolean containsNullValue() {
//        Entry[] tab = table;
//        for (int i = 0; i < tab.length ; i++)
//            for (Entry e = tab[i] ; e != null ; e = e.next)
//                if (e.value == null)
//                    return true;
//        return false;
//    }
//
//
//    public Object clone() {
//        HashMap<K,V> result = null;
//        try {
//            result = (HashMap<K,V>)super.clone();
//        } catch (CloneNotSupportedException e) {
//            // assert false;
//        }
//        result.table = new Entry[table.length];
//        result.entrySet = null;
//        result.modCount = 0;
//        result.size = 0;
//        result.init();
//        result.putAllForCreate(this);
//
//        return result;
//    }
//
//    static class Entry<K,V> implements Map.Entry<K,V> {
//        final K key;
//        V value;
//        Entry<K,V> next;
//        int hash;
//
//
//        Entry(int h, K k, V v, Entry<K,V> n) {
//            value = v;
//            next = n;
//            key = k;
//            hash = h;
//        }
//
//        public final K getKey() {
//            return key;
//        }
//
//        public final V getValue() {
//            return value;
//        }
//
//        public final V setValue(V newValue) {
//            V oldValue = value;
//            value = newValue;
//            return oldValue;
//        }
//
//        public final boolean equals(Object o) {
//            if (!(o instanceof Map.Entry))
//                return false;
//            Map.Entry e = (Map.Entry)o;
//            Object k1 = getKey();
//            Object k2 = e.getKey();
//            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
//                Object v1 = getValue();
//                Object v2 = e.getValue();
//                if (v1 == v2 || (v1 != null && v1.equals(v2)))
//                    return true;
//            }
//            return false;
//        }
//
//        public final int hashCode() {
//            return (key==null   ? 0 : key.hashCode()) ^
//                   (value==null ? 0 : value.hashCode());
//        }
//
//        public final String toString() {
//            return getKey() + "=" + getValue();
//        }
//
//
//        void recordAccess(HashMap<K,V> m) {
//        }
//
//
//        void recordRemoval(HashMap<K,V> m) {
//        }
//    }
//
//
//    void addEntry(int hash, K key, V value, int bucketIndex) {
//        if ((size >= threshold) && (null != table[bucketIndex])) {
//            resize(2 * table.length);
//            hash = (null != key) ? hash(key) : 0;
//            bucketIndex = indexFor(hash, table.length);
//        }
//
//        createEntry(hash, key, value, bucketIndex);
//    }
//
//
//    void createEntry(int hash, K key, V value, int bucketIndex) {
//        Entry<K,V> e = table[bucketIndex];
//        table[bucketIndex] = new Entry<>(hash, key, value, e);
//        size++;
//    }
//
//    private abstract class HashIterator<E> implements Iterator<E> {
//        Entry<K,V> next;        // next entry to return
//        int expectedModCount;   // For fast-fail
//        int index;              // current slot
//        Entry<K,V> current;     // current entry
//
//        HashIterator() {
//            expectedModCount = modCount;
//            if (size > 0) { // advance to first entry
//                Entry[] t = table;
//                while (index < t.length && (next = t[index++]) == null)
//                    ;
//            }
//        }
//
//        public final boolean hasNext() {
//            return next != null;
//        }
//
//        final Entry<K,V> nextEntry() {
//            if (modCount != expectedModCount)
//                throw new ConcurrentModificationException();
//            Entry<K,V> e = next;
//            if (e == null)
//                throw new NoSuchElementException();
//
//            if ((next = e.next) == null) {
//                Entry[] t = table;
//                while (index < t.length && (next = t[index++]) == null)
//                    ;
//            }
//            current = e;
//            return e;
//        }
//
//        public void remove() {
//            if (current == null)
//                throw new IllegalStateException();
//            if (modCount != expectedModCount)
//                throw new ConcurrentModificationException();
//            Object k = current.key;
//            current = null;
//            HashMap.this.removeEntryForKey(k);
//            expectedModCount = modCount;
//        }
//    }
//
//    private final class ValueIterator extends HashIterator<V> {
//        public V next() {
//            return nextEntry().value;
//        }
//    }
//
//    private final class KeyIterator extends HashIterator<K> {
//        public K next() {
//            return nextEntry().getKey();
//        }
//    }
//
//    private final class EntryIterator extends HashIterator<Map.Entry<K,V>> {
//        public Map.Entry<K,V> next() {
//            return nextEntry();
//        }
//    }
//
//    // Subclass overrides these to alter behavior of views' iterator() method
//    Iterator<K> newKeyIterator()   {
//        return new KeyIterator();
//    }
//    Iterator<V> newValueIterator()   {
//        return new ValueIterator();
//    }
//    Iterator<Map.Entry<K,V>> newEntryIterator()   {
//        return new EntryIterator();
//    }
//
//
//    // Views
//
//    private transient Set<Map.Entry<K,V>> entrySet = null;
//
//
//    public Set<K> keySet() {
//        Set<K> ks = keySet;
//        return (ks != null ? ks : (keySet = new KeySet()));
//    }
//
//    private final class KeySet extends AbstractSet<K> {
//        public Iterator<K> iterator() {
//            return newKeyIterator();
//        }
//        public int size() {
//            return size;
//        }
//        public boolean contains(Object o) {
//            return containsKey(o);
//        }
//        public boolean remove(Object o) {
//            return HashMap.this.removeEntryForKey(o) != null;
//        }
//        public void clear() {
//            HashMap.this.clear();
//        }
//    }
//
//
//    public Collection<V> values() {
//        Collection<V> vs = values;
//        return (vs != null ? vs : (values = new Values()));
//    }
//
//    private final class Values extends AbstractCollection<V> {
//        public Iterator<V> iterator() {
//            return newValueIterator();
//        }
//        public int size() {
//            return size;
//        }
//        public boolean contains(Object o) {
//            return containsValue(o);
//        }
//        public void clear() {
//            HashMap.this.clear();
//        }
//    }
//
//
//    public Set<Map.Entry<K,V>> entrySet() {
//        return entrySet0();
//    }
//
//    private Set<Map.Entry<K,V>> entrySet0() {
//        Set<Map.Entry<K,V>> es = entrySet;
//        return es != null ? es : (entrySet = new EntrySet());
//    }
//
//    private final class EntrySet extends AbstractSet<Map.Entry<K,V>> {
//        public Iterator<Map.Entry<K,V>> iterator() {
//            return newEntryIterator();
//        }
//        public boolean contains(Object o) {
//            if (!(o instanceof Map.Entry))
//                return false;
//            Map.Entry<K,V> e = (Map.Entry<K,V>) o;
//            Entry<K,V> candidate = getEntry(e.getKey());
//            return candidate != null && candidate.equals(e);
//        }
//        public boolean remove(Object o) {
//            return removeMapping(o) != null;
//        }
//        public int size() {
//            return size;
//        }
//        public void clear() {
//            HashMap.this.clear();
//        }
//    }
//
//
//    private void writeObject(ObjectOutputStream s)
//        throws IOException
//    {
//        Iterator<Map.Entry<K,V>> i =
//            (size > 0) ? entrySet0().iterator() : null;
//
//        // Write out the threshold, loadfactor, and any hidden stuff
//        s.defaultWriteObject();
//
//        // Write out number of buckets
//        s.writeInt(table.length);
//
//        // Write out size (number of Mappings)
//        s.writeInt(size);
//
//        // Write out keys and values (alternating)
//        if (size > 0) {
//            for(Map.Entry<K,V> e : entrySet0()) {
//                s.writeObject(e.getKey());
//                s.writeObject(e.getValue());
//            }
//        }
//    }
//
//    private static final long serialVersionUID = 362498820763181265L;
//
//
//    private void readObject(ObjectInputStream s)
//         throws IOException, ClassNotFoundException
//    {
//        // Read in the threshold (ignored), loadfactor, and any hidden stuff
//        s.defaultReadObject();
//        if (loadFactor <= 0 || Float.isNaN(loadFactor))
//            throw new InvalidObjectException("Illegal load factor: " +
//                                               loadFactor);
//
//        // set hashSeed (can only happen after VM boot)
//        Holder.UNSAFE.putIntVolatile(this, Holder.HASHSEED_OFFSET,
//                sun.misc.Hashing.randomHashSeed(this));
//
//        // Read in number of buckets and allocate the bucket array;
//        s.readInt(); // ignored
//
//        // Read number of mappings
//        int mappings = s.readInt();
//        if (mappings < 0)
//            throw new InvalidObjectException("Illegal mappings count: " +
//                                               mappings);
//
//        int initialCapacity = (int) Math.min(
//                // capacity chosen by number of mappings
//                // and desired load (if >= 0.25)
//                mappings * Math.min(1 / loadFactor, 4.0f),
//                // we have limits...
//                HashMap.MAXIMUM_CAPACITY);
//        int capacity = 1;
//        // find smallest power of two which holds all mappings
//        while (capacity < initialCapacity) {
//            capacity <<= 1;
//        }
//
//        table = new Entry[capacity];
//        threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);
//        useAltHashing = sun.misc.VM.isBooted() &&
//                (capacity >= Holder.ALTERNATIVE_HASHING_THRESHOLD);
//
//        init();  // Give subclass a chance to do its thing.
//
//        // Read the keys and values, and put the mappings in the HashMap
//        for (int i=0; i<mappings; i++) {
//            K key = (K) s.readObject();
//            V value = (V) s.readObject();
//            putForCreate(key, value);
//        }
//    }
//
//    // These methods are used when serializing HashSets
//    int   capacity()     { return table.length; }
//    float loadFactor()   { return loadFactor;   }
//}
