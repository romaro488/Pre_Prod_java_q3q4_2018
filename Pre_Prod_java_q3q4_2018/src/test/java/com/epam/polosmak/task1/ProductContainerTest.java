package com.epam.polosmak.task1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductContainerTest {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int NO_SUCH_ELEMENT = -1;

    private ProductContainer<String> productContainer;

    public static Predicate<String> condition(int cond) {
        return p -> Integer.parseInt(p) > cond;
    }

    @Before
    public void before() {
        productContainer = new ProductContainer<>();
    }

    @After
    public void after() {
        productContainer = null;
    }

    private void fillContainer(int size) {
        for (int i = 0; i < size; i++) {
            productContainer.add(String.valueOf(i));
        }
    }

    @Test
    public void size_sizeIs10_true() {
        fillContainer(10);
        int expectedSize = 10;
        int actualSize = productContainer.size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void get_indexExist_true() {
        fillContainer(10);
        String expectedValue = "9";
        String actualValue = productContainer.get(9);
        assertEquals(expectedValue, actualValue);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_indexIsNotExist_IndexOutOfBoundsException() {
        fillContainer(4);
        productContainer.get(10);
    }

    @Test
    public void set_indexExist_returnElementPreviouslyAtThePosition() {
        fillContainer(5);
        int indexOfElementToReplace = 1;
        String expectedValue = productContainer.get(indexOfElementToReplace);
        String actualValue = productContainer.set(indexOfElementToReplace, "some value");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void set_indexExist_returnElementAtThePosition() {
        fillContainer(5);
        int indexOfElementToReplaced = 1;
        String toBeStored = "some value";
        productContainer.set(indexOfElementToReplaced, toBeStored);
        assertEquals(toBeStored, productContainer.get(indexOfElementToReplaced));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void set_indexDoesNotExist_IndexOutOfBoundsException() {
        fillContainer(4);
        productContainer.set(10, "some value");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void set_indexIsNotExist_IndexOutOfBoundsException() {
        productContainer.set(1, "some value");
    }

    @Test
    public void add_addIntoTheEnd_true() {
        assertTrue(productContainer.add("some value"));
    }

    @Test
    public void test_addIntoExistIndex_true() {
        fillContainer(5);
        int indexOfTobeInserted = 2;
        String elementToBeInserted = "10";
        productContainer.add(indexOfTobeInserted, elementToBeInserted);
        String actualValue = productContainer.get(2);
        assertEquals(elementToBeInserted, actualValue);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_addIntoIndexIsNotExist_IndexOutOfBoundsException() {
        fillContainer(4);
        productContainer.add(10, "some value");
    }

    @Test
    public void addAll_addIntoTheEnd_true() {
        fillContainer(4);
        List<String> needToAdd = new ArrayList<String>();
        needToAdd.add("8");
        needToAdd.add("9");
        productContainer.addAll(needToAdd);
        String[] expected = {"0", "1", "2", "3", "8", "9"};
        String[] actual = new String[6];
        actual = productContainer.toArray(actual);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void addAll_addIntoExistIndex_true() {
        fillContainer(4);
        List<String> needToAdd = new ArrayList<String>();
        needToAdd.add("8");
        needToAdd.add("9");
        productContainer.addAll(1, needToAdd);
        String[] expected = {"0", "8", "9", "1", "2", "3"};
        String[] actual = new String[6];
        actual = productContainer.toArray(expected);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAll_addIntoNoneExistIndex_true() {
        fillContainer(4);
        List<String> needToAdd = new ArrayList<String>();
        needToAdd.add("8");
        needToAdd.add("9");
        productContainer.addAll(10, needToAdd);
    }

    @Test
    public void remove_existIndex_true() {
        fillContainer(4);
        String expectedValue = "2";
        String actualValue = productContainer.remove(2);
        assertEquals(expectedValue, actualValue);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void remove_isNotExistIndex_IndexOutOfBoundException() {
        productContainer.remove(2);
    }

    @Test
    public void remove_objectIsNotExist_false() {
        assertFalse(productContainer.remove("shorts"));
    }

    @Test
    public void remove_objectExist_true() {
        fillContainer(4);
        assertTrue(productContainer.remove("1"));
    }

    @Test
    public void removeAll_elementsExistInContainer_true() {
        fillContainer(4);
        List<String> needToRemove = new ArrayList<String>();
        needToRemove.add("1");
        needToRemove.add("2");
        productContainer.addAll(1, needToRemove);
        String[] expected = {"0", "3"};
        String[] actual = new String[2];
        productContainer.removeAll(needToRemove);
        actual = productContainer.toArray(actual);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeAll_elementsNotExistInContainer_true() {
        fillContainer(4);
        List<String> needToRemove = new ArrayList<String>();
        needToRemove.add("not exist in productContainer");
        needToRemove.add("not exist in productContainer");

        productContainer.addAll(1, needToRemove);
        String[] expected = {"0", "1", "2", "3"};
        String[] actual = new String[4];
        productContainer.removeAll(needToRemove);
        actual = productContainer.toArray(expected);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void contains_elementExist_true() {
        fillContainer(4);
        assertTrue(productContainer.contains("1"));
    }

    @Test
    public void contains_elementNotExist_false() {
        fillContainer(4);
        assertFalse(productContainer.contains("element not exist"));
    }

    @Test
    public void containsAll_elementsExist_true() {
        fillContainer(4);
        List<String> needToCheck = new ArrayList<>();
        needToCheck.add("0");
        needToCheck.add("1");
        assertTrue(productContainer.containsAll(needToCheck));
    }

    @Test
    public void containsAll_elementsNotExist_true() {
        fillContainer(4);
        List<String> needToCheck = new ArrayList<>();
        needToCheck.add("element not exist");
        needToCheck.add("1");
        assertFalse(productContainer.containsAll(needToCheck));
    }

    @Test
    public void isEmpty_containerIsEmpty_true() {
        assertTrue(productContainer.isEmpty());
    }

    @Test
    public void isEmpty_containerIsNotEmpty_true() {
        fillContainer(5);
        assertFalse(productContainer.isEmpty());
    }

    @Test
    public void indexOf_elementExist_returnIndexOfElement() {
        fillContainer(4);
        int expectedValue = 1;
        int actualValue = productContainer.indexOf("1");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void lastIndexOf_existElements_returnIndex() {
        fillContainer(4);
        int expected = 3;
        int actual = productContainer.lastIndexOf("3");
        assertEquals(expected, actual);
    }

    @Test
    public void lastIndexOf_notExistElements_returnIndex() {
        fillContainer(4);
        int actual = productContainer.lastIndexOf("10");
        assertEquals(NO_SUCH_ELEMENT, actual);
    }

    @Test
    public void indexOf_elementNotExist_returnNoSuchElement() {
        fillContainer(4);
        int actualValue = productContainer.indexOf("no such element");
        assertEquals(NO_SUCH_ELEMENT, actualValue);
    }

    @Test
    public void retainAll_elementsExist_returnRetailArray() {
        fillContainer(4);
        List<String> needToRetail = new ArrayList<String>();
        needToRetail.add("1");
        needToRetail.add("2");
        String expectedArray[] = {"1", "2"};
        productContainer.retainAll(needToRetail);
        String[] actualArray = new String[2];
        actualArray = productContainer.toArray(actualArray);
    }

    @Test
    public void retainAll_elementsNotExist_returnOldArray() {
        fillContainer(4);
        List<String> needToRetail = new ArrayList<String>();
        needToRetail.add("not exist");
        needToRetail.add("not exist");
        String expectedArray[] = {"0", "1", "2", "3"};
        productContainer.retainAll(needToRetail);
        String[] actualArray = new String[4];
        actualArray = productContainer.toArray(actualArray);
    }

    @Test
    public void clear_clearContainer_returnSizeEqualsZero() {
        fillContainer(4);
        int expected = 0;
        productContainer.clear();
        int actual = productContainer.size();
        assertEquals(expected, actual);
    }

    @Test
    public void toArray_withoutArgument_returnArray() {
        fillContainer(4);
        Object[] expected = {"0", "1", "2", "3"};
        Object[] actual = productContainer.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void toArray_withArgument_returnArray() {
        fillContainer(4);
        String expected[] = {"0", "1", "2", "3"};
        String actual[] = new String[4];
        actual = productContainer.toArray(actual);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void toArray_withLengthLessThenSize_returnArray() {
        fillContainer(4);
        String expected[] = {"0", "1", "2", "3"};
        String actual[] = new String[2];
        actual = productContainer.toArray(actual);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void hasNext_existElements_true() {
        fillContainer(5);
        Iterator<String> containerIterator = productContainer.iterator();
        assertTrue(containerIterator.hasNext());
    }

    @Test
    public void hasNext_noneExistElements_false() {
        Iterator<String> containerIterator = productContainer.iterator();
        assertFalse(containerIterator.hasNext());
    }

    @Test
    public void hasNext_withConditionAndContainerExsitElements_true() {
        fillContainer(5);
        Iterator<String> containerIterator = productContainer.iterator(condition(2));
        assertTrue(containerIterator.hasNext());
    }

    @Test
    public void hasNext_withConditionAndContainerNotExsitElements_true() {
        Iterator<String> containerIterator = productContainer.iterator(condition(3));
        assertFalse(containerIterator.hasNext());
    }

    @Test
    public void next_containerExistElementsAndHasNextHasCalled_true() {
        fillContainer(10);
        Iterator<String> containerIterator = productContainer.iterator();
        String expectedValue = "0";
        String actualValue = containerIterator.next();
        assertEquals(expectedValue, actualValue);
    }

    @Test(expected = NoSuchElementException.class)
    public void next_notExistElements_NoSuchElementException() {
        Iterator<String> containerIterator = productContainer.iterator();
        containerIterator.next();
    }

    @Test
    public void next_withCondition_true() {
        fillContainer(5);
        Iterator<String> containerIterator = productContainer.iterator(condition(2));
        String expectedValue = "3";
        containerIterator.hasNext();
        String actualValue = containerIterator.next();
        assertEquals(expectedValue, actualValue);
    }

    @Test(expected = NoSuchElementException.class)
    public void next_withConditionAndNotExistElement_NoSuchElementException() {
        Iterator<String> containerIterator = productContainer.iterator(condition(2));
        containerIterator.next();
    }

    @Test(expected = IllegalStateException.class)
    public void remove_hasNextHasNotCalled_IllegalStateException() {
        Iterator<String> containerIterator = productContainer.iterator();
        containerIterator.remove();
    }

    @Test
    public void remove_hasNextHasCalled_removeElement() {
        fillContainer(4);
        Iterator<String> containerIterator = productContainer.iterator();
        containerIterator.hasNext();
        containerIterator.next();
        containerIterator.remove();
        String expected[] = {"1", "2", "3"};
        String actual[] = new String[3];
        actual = productContainer.toArray(actual);
        Assert.assertArrayEquals(expected, actual);
    }
}
