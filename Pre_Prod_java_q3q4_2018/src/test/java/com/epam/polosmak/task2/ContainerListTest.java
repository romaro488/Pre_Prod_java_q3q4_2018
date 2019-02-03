package com.epam.polosmak.task2;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class ContainerListTest {

    private final int NO_SUCH_ELEMENT = -1;
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private List<String> unmodifiable;
    private List<String> modifiable;
    private ContainerList<String> conList;

    @Before
    public void before() {
        unmodifiable = new ArrayList<>();
        unmodifiable.addAll(Arrays.asList("1", "2", "3"));
        modifiable = new ArrayList<>();
        modifiable.addAll(Arrays.asList("5", "6", "7"));
        conList = new ContainerList(unmodifiable, modifiable);
    }

    @Test
    public void sizeShouldReturnSumOfUnmodifiableAndModifiableList() {
        int expected = unmodifiable.size() + modifiable.size();
        assertEquals(expected, conList.size());
    }

    @Test
    public void isEmptyShouldReturnFalseWhenItCalled() {
        assertFalse(conList.isEmpty());
    }

    @Test
    public void containsShouldReturnTrueIfElementExist() {
        String needToCheck = "1";
        assertTrue(conList.contains(needToCheck));
    }

    @Test
    public void getShouldThrowIndecOutOfBoundIfIndexDoesNotExist() {
        exception.expect(IndexOutOfBoundsException.class);
        int indexOutOfRange = 100;
        conList.get(indexOutOfRange);
    }

    @Test
    public void getShouldReturnElementIfIndexExist() {
        String expectedValue = "1";
        int indexOfElement = 0;
        assertEquals(expectedValue, conList.get(indexOfElement));
    }

    @Test
    public void containsShouldReturnFalseIfElementDoesNotExist() {
        String needToCheck = "doesnt exist";
        assertFalse(conList.contains(needToCheck));
    }

    @Test
    public void addShouldReturnTrueWhenAddElementToList() {
        String needToAdded = "new element";
        assertTrue(conList.add(needToAdded));
    }

    @Test
    public void addShoulReturnAddElementIfAddIntoModifiableListIndex() {
        int modifiableListIndex = 4;
        int sizeBefore = conList.size();
        conList.add(modifiableListIndex, "new element");
        assertTrue(sizeBefore != conList.size());
    }

    @Test
    public void addShoulThrowIndexOutOfModifiableListIfAddIntoUnmodifiableListIndex() {
        exception.expect(IllegalStateException.class);
        int unmodifiableListIndex = 1;
        conList.add(unmodifiableListIndex, "new element");
    }

    @Test
    public void addAllShouldReturnTrueWhenAddCollectionToList() {
        assertTrue(conList.addAll(Arrays.asList("some new elements", "some new elements")));
    }

    @Test
    public void addAllShouldReturnTrueWhenAddIntoValiablePosition() {
        int valiableIndex = 4;
        assertTrue(conList.addAll(valiableIndex, Arrays.asList("some new elements", "some new elements")));
    }

    @Test
    public void addAllShouldThrowIndexOutOfModifiableListIfAddIntoUnmodiafiableList() {
        exception.expect(IllegalStateException.class);
        int unmodifiableListIndex = 1;
        conList.addAll(unmodifiableListIndex, Arrays.asList("some new elements", "some new elements"));
    }

    @Test
    public void addAllShouldReturnNullPointerExcepptionIfCollectionIsNull() {
        exception.expect(NullPointerException.class);
        conList.addAll(null);
    }

    @Test
    public void removeShouldRemoveElementWhenRemoveFromModifiableList() {
        String needToRemoved = "5";
        assertTrue(conList.remove(needToRemoved));
    }

    @Test
    public void removeShouldThrowIndexOutOfModifiableListIfTryToRemoveFromUnmodifiableList() {
        exception.expect(IllegalStateException.class);
        String needToRemoved = "1";
        conList.remove(needToRemoved);
    }

    @Test
    public void removeAllShouldReturnFalseWhenRemoveFromModifiableList() {
        assertTrue(conList.removeAll(Arrays.asList("5", "6")));
    }

    @Test
    public void removeShouldReturnFalseIfElementDoesNotExist() {
        assertFalse(conList.remove("not exist"));
    }

    @Test
    public void removeAllShouldThrowIndexOutOfModifiableListIfTryToRemoveFromUnmodifiableList() {
        exception.expect(IllegalStateException.class);
        String elementInUnmodifiable = "2";
        List<String> needToRemoved = new ArrayList<>();
        needToRemoved.add("5");
        needToRemoved.add(elementInUnmodifiable);
        conList.removeAll(needToRemoved);
    }

    @Test
    public void removeAllShouldReturnFalseIfCollectionDoesntExistInWrapper() {
        assertFalse(conList.removeAll(Arrays.asList("not exist in wrapper", "not exist in wrapper")));
    }

    @Test
    public void removeAllShouldReturnNullPointerExcepptionIfCollectionIsNull() {
        exception.expect(NullPointerException.class);
        conList.removeAll(null);
    }

    @Test
    public void containsAllShouldThrowNullPointerExceptionWhenCollectionIsNull() {
        exception.expect(NullPointerException.class);
        conList.containsAll(null);
    }

    @Test
    public void containsAllShouldReturnTrueWhenCollectionIsExistInList() {
        List<String> needToCheck = Arrays.asList("2", "5");
        assertTrue(conList.containsAll(needToCheck));
    }

    @Test
    public void containsAllShouldReturnFalseIfCollectionDoesNotExist() {
        List<String> needToCheck = Arrays.asList("not exist", "not exist");
        assertFalse(conList.containsAll(needToCheck));
    }

    @Test
    public void retainAllShouldReturnNullPointerExceptionIfCollectionIsNull() {
        exception.expect(NullPointerException.class);
        conList.retainAll(null);
    }

    @Test
    public void retainAllShouldRemoveAllElementsNotExistInCollection() {
        List<String> needToRetain = Arrays.asList("5", "6");
        int sizeBefore = conList.size();
        conList.retainAll(needToRetain);
        assertTrue(sizeBefore > conList.size());
    }

    @Test
    public void indexOfShouldReturnIndexOfElementIfElementExist() {
        String elementExist = "2";
        int expectedIndex = 1;
        assertEquals(expectedIndex, conList.indexOf(elementExist));
    }

    @Test
    public void indexOfShouldReturnNoSuchElementIfElementDoesNotExist() {
        String notExistElement = "not exist";
        assertEquals(NO_SUCH_ELEMENT, conList.indexOf(notExistElement));
    }

    @Test
    public void setShouldThrowIndexOutOfModifiableListWhenIndexInUnmodifiableList() {
        exception.expect(IllegalStateException.class);
        int indexInUnmodifiableList = 1;
        conList.set(indexInUnmodifiableList, "new value");
    }

    @Test
    public void setShouldThrowIndexOutOfBoundsExceptionWhenIndexDoesNotExist() {
        exception.expect(IndexOutOfBoundsException.class);
        int isNotExistIndex = 10;
        conList.set(isNotExistIndex, "new value");
    }

    @Test
    public void setShouldReturnPreviousElementWhenSetElementInModifiableList() {
        int indexInModifiableList = 4;
        String expectedValue = "6";
        assertEquals(expectedValue, conList.set(indexInModifiableList, "new value"));
    }

    @Test
    public void hasNextShoudReturnTrueIfElementsStillExist() {
        Iterator<String> iterator = conList.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void nextShouldReturnElementIfHasNextIsTrue() {
        Iterator<String> iterator = conList.iterator();
        String expectedValue = "1";
        assertEquals(expectedValue, iterator.next());
    }

    @Test
    public void nextShouldThrowIndexOoutOfBoundExceptionIfHasNextIsFalse() {
        exception.expect(IndexOutOfBoundsException.class);
        conList = new ContainerList<>(new ArrayList<>(), new ArrayList<>());
        Iterator<String> iterator = conList.iterator();
        iterator.next();
    }

    @Test
    public void hasNextShoudReturnFalseIfReachedToEnd() {
        conList = new ContainerList<>(new ArrayList<>(), new ArrayList<>());
        Iterator<String> iterator = conList.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void nextWithConditionShouldThrowIndexOoutOfBoundExceptionIfHasNextIsFalse() {
        exception.expect(IndexOutOfBoundsException.class);
        conList = new ContainerList<>(new ArrayList<>(), new ArrayList<>());
        Iterator<String> iterator = conList.iterator();
        iterator.next();
    }

    @Test
    public void removeShouldThrowIndexOutOfModifiableListIfRemoveElementInUnmodifiableList() {
        exception.expect(IllegalStateException.class);
        Iterator<String> iterator = conList.iterator();
        iterator.next();
        iterator.remove();
    }
}