package ua.lazareva.datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashMapTest {
    private HashMap<Object, Object> hashMap;

    @Before
    public void before() {
        hashMap = new HashMap<>();

        assertEquals("nullvalue", hashMap.put(null, "nullvalue"));

        for (int i = 0; i < 9; i++) {
            assertEquals("value" + i, hashMap.put("key" + i, "value" + i));
        }
        assertEquals(10, hashMap.size());
    }

    @Test
    public void testGet() {
        assertEquals("nullvalue", hashMap.get(null));

        for (int i = 0; i < hashMap.size() - 1; i++) {
            assertEquals("value" + i, hashMap.get("key" + i));
        }
        assertNull(hashMap.get("key" + 10));
    }

    @Test
    public void testContainsKey() {
        for (int i = 0; i < hashMap.size() - 1; i++) {
            assertTrue(hashMap.containsKey("key" + i));
        }
        assertTrue(hashMap.containsKey(null));
        assertFalse(hashMap.containsKey("key" + 10));

    }

    @Test
    public void testContainsValue() {
        for (int i = 0; i < hashMap.size() - 1; i++) {
            assertTrue(hashMap.containsValue("value" + i));
        }
        assertFalse(hashMap.containsValue("value" + 10));

    }

    @Test
    public void testIsEmpty() {
        hashMap.clear();
        assertEquals(0, hashMap.size());
    }

    @Test
    public void testRemove() {
        assertNull(hashMap.remove("key9"));

        assertEquals("nullvalue", hashMap.remove(null));
        assertFalse(hashMap.containsKey("key9"));

        assertEquals("value5", hashMap.remove("key5"));
        assertFalse(hashMap.containsKey("key9"));

        assertEquals("value0", hashMap.remove("key0"));
        assertFalse(hashMap.containsKey("key9"));

        assertNull(hashMap.remove("key10"));
        assertEquals(7, hashMap.size());

    }

    @Test
    public void testPut() {
        assertEquals("value8", hashMap.put("key8", "newvalue"));
        assertEquals("nullvalue", hashMap.put(null, "newvalue"));
    }

    @Test
    public void testPutIfAbsent() {
        assertNull(hashMap.putIfAbsent("key8", "newvalue8"));
        assertEquals("value10", hashMap.putIfAbsent("key10", "value10"));
    }

    @Test
    public void testPutAll() {
        HashMap<Object, Object> hashMapToAdd = new HashMap<>();
        for (int i = 5; i < 15; i++) {
            assertEquals("hashMapToAdd" + i, hashMapToAdd.put("key" + i, "hashMapToAdd" + i));
        }
        hashMap.putAll(hashMapToAdd);
        assertEquals(16, hashMap.size());
        for (int i = 1; i < 5; i++) {
            assertEquals("value" + i, hashMap.get("key" + i));
        }
        assertEquals("nullvalue", hashMap.get(null));
        for (int i = 5; i < hashMap.size() - 1; i++) {
            assertEquals("hashMapToAdd" + i, hashMap.get("key" + i));
        }
    }

    @Test
    public void testPutAllIfAbsent() {
        HashMap<Object, Object> hashMapToAdd = new HashMap<>();
        for (int i = 5; i < 15; i++) {
            assertEquals("hashMapToAdd" + i, hashMapToAdd.put("key" + i, "hashMapToAdd" + i));
        }
        hashMap.putAllIfAbsent(hashMapToAdd);
        assertEquals(16, hashMap.size());
        for (int i = 1; i < 9; i++) {
            assertEquals("value" + i, hashMap.get("key" + i));
        }
        assertEquals("nullvalue", hashMap.get(null));
        for (int i = 10; i < hashMap.size() - 1; i++) {
            assertEquals("hashMapToAdd" + i, hashMap.get("key" + i));
        }
    }

}
