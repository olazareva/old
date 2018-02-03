package ua.lazareva.datastructures.list;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class AbstractListTest {

    private List<Object> listWithThreeElements;
    private List<Object> listWithTenElements;
    private List<Object> listWithZeroElements;


    @Before
    public void before() {
        listWithThreeElements = getList();
        for (int i = 0; i < 3; i++) {
            listWithThreeElements.add(i);
        }
        assertEquals(3, listWithThreeElements.size());

        listWithTenElements = getList();
        for (int i = 0; i < 10; i++) {
            listWithTenElements.add(i);
        }
        assertEquals(10, listWithTenElements.size());

        listWithZeroElements = getList();
        assertEquals(0, listWithZeroElements.size());
    }


    @Test
    public void testGet() {
        for (int i = 0; i < listWithThreeElements.size(); i++) {
            assertEquals(i, listWithThreeElements.get(i));
        }
        for (int i = 0; i < listWithTenElements.size(); i++) {
            assertEquals(i, listWithTenElements.get(i));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetException() {
        listWithZeroElements.get(0);
    }

    @Test
    public void testAdd() {
        listWithThreeElements.add(-1, 0);
        assertEquals(-1, listWithThreeElements.get(0));

        listWithThreeElements.add(-1, 1);
        assertEquals(-1, listWithThreeElements.get(1));

        listWithThreeElements.add(-1, 5);
        assertEquals(-1, listWithThreeElements.get(5));

        try {
            listWithThreeElements.add(-1, 7);
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }
        for (int i = 2; i < listWithThreeElements.size() - 1; i++) {
            assertEquals(i - 2, listWithThreeElements.get(i));
        }

        try {
            listWithZeroElements.add(-1, 1);
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }

        listWithZeroElements.add(-1, 0);
        assertEquals(-1, listWithZeroElements.get(0));
    }

    @Test
    public void testIsEmpty() {
        listWithThreeElements.clear();
        listWithTenElements.clear();
        assertEquals(0, listWithThreeElements.size());
        assertEquals(0, listWithTenElements.size());
        assertEquals(0, listWithZeroElements.size());
    }

    @Test
    public void testSet() {
        for (int i = 0; i < listWithThreeElements.size(); i++) {
            listWithThreeElements.set(-1 * i, i);
            assertEquals(-1 * i, listWithThreeElements.get(i));
        }
        for (int i = 0; i < listWithTenElements.size(); i++) {
            listWithTenElements.set(-1 * i, i);
            assertEquals(-1 * i, listWithTenElements.get(i));
        }

    }

    @Test
    public void testContains() {
        assertFalse(listWithThreeElements.contains(8));
        assertTrue(listWithThreeElements.contains(2));
    }

    @Test
    public void testLastIndexOf() {
        for (int i = 0; i < 10; i++) {
            listWithZeroElements.add(i % 3);
        }
        assertEquals(9, listWithZeroElements.lastIndexOf(0));
        assertEquals(8, listWithZeroElements.lastIndexOf(2));
        assertEquals(7, listWithZeroElements.lastIndexOf(1));

    }


    @Test
    public void testIndexOf() {
        for (int i = 0; i < 10; i++) {
            listWithZeroElements.add(i % 3);
        }
        assertEquals(0, listWithZeroElements.indexOf(0));
        assertEquals(1, listWithZeroElements.indexOf(1));
        assertEquals(2, listWithZeroElements.indexOf(2));

    }

    @Test
    public void testRemove() {
        listWithTenElements.remove((Object) 8);
        assertEquals(9, listWithTenElements.get(8));
        assertEquals(9, listWithTenElements.size());

        listWithTenElements.remove((Object) 9);
        assertEquals(7, listWithTenElements.get(7));
        assertEquals(8, listWithTenElements.size());

        listWithTenElements.remove((Object) 0);
        assertEquals(1, listWithTenElements.get(0));
        assertEquals(7, listWithTenElements.size());
    }

    @Test
    public void testRemovebyIndex() {
        listWithTenElements.remove(8);
        assertEquals(9, listWithTenElements.get(8));
        assertEquals(9, listWithTenElements.size());

        listWithTenElements.remove(8);
        assertEquals(7, listWithTenElements.get(7));
        assertEquals(8, listWithTenElements.size());

        listWithTenElements.remove(0);
        assertEquals(1, listWithTenElements.get(0));
        assertEquals(7, listWithTenElements.size());
    }

    protected abstract List<Object> getList();

}
