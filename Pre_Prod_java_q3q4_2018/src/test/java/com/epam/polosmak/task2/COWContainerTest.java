package com.epam.polosmak.task2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class COWContainerTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private com.epam.polosmak.task2.COWContainer<Integer> COWContainer = new COWContainer<>();

    @Before
    public void generateLists() {
        COWContainer.add(0);
        COWContainer.add(1);
        COWContainer.add(2);
        COWContainer.add(3);
        COWContainer.add(4);
        COWContainer.add(5);
        COWContainer.add(6);
    }

    @Test
    public void getByIndexTest() {
        Assert.assertEquals(new Integer(0), COWContainer.get(0));
        Assert.assertEquals(new Integer(3), COWContainer.get(3));
    }

    @Test
    public void sizeTest() {
        Assert.assertEquals(7, COWContainer.size());
    }

    @Test
    public void isEmptyTest() {
        Assert.assertFalse(COWContainer.isEmpty());
    }

    @Test
    public void containsTest() {
        Assert.assertFalse(COWContainer.contains(1587));
        Assert.assertTrue(COWContainer.contains(0));
        Assert.assertTrue(COWContainer.contains(2));
        Assert.assertFalse(COWContainer.contains(777));
    }

    @Test
    public void addObjectTest() {
        Assert.assertTrue(COWContainer.add(2));
    }

    @Test
    public void removeObjectTest() {
        Assert.assertFalse(COWContainer.remove(new Integer(32)));
        Assert.assertTrue(COWContainer.remove(new Integer(1)));
    }

    @Test
    public void addAllTest() {
        int sizeBefore = this.COWContainer.size();
        List<Integer> list = new ArrayList<>();
        list.add(123);
        list.add(213);
        list.add(4124);
        Assert.assertTrue(this.COWContainer.addAll(list));
        Assert.assertEquals(3, this.COWContainer.size() - sizeBefore);
    }

    @Test
    public void addAllByIndex() {
        int sizeBefore = this.COWContainer.size();
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(13);
        list.add(8);

        Assert.assertTrue(this.COWContainer.addAll(4, list));
        Assert.assertEquals(10, this.COWContainer.size());
    }

    @Test
    public void clearTest() {
        COWContainer.clear();
        Assert.assertEquals(0, COWContainer.size());
    }

    @Test
    public void setTest() {
        COWContainer.set(1, 888412145);
        Assert.assertEquals(new Integer(888412145), COWContainer.get(1));
    }

    @Test
    public void addByIndexTest() {
        int size = COWContainer.size();
        COWContainer.add(1, 8);
        Assert.assertTrue(COWContainer.size() > size);
    }

    @Test
    public void removeByIndexTest() {
        Assert.assertEquals(new Integer(1), COWContainer.remove(1));
    }

    @Test
    public void indexOfTest() {
        Assert.assertEquals(1, COWContainer.indexOf(1));
        Assert.assertEquals(4, COWContainer.indexOf(4));
        Assert.assertEquals(-1, COWContainer.lastIndexOf(321));
    }

    @Test
    public void containsAllTest() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        Assert.assertTrue(COWContainer.containsAll(list1));
        list1.add(10);
        Assert.assertFalse(COWContainer.containsAll(list1));
    }
}
