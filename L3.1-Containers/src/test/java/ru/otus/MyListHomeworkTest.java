package ru.otus;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyListHomeworkTest {

    @Test
    public void testAddAll() {
        List<Integer> testList = new MyList<>();
        List<Integer> sourseList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int value = (int) (Math.random() * 1000);
            sourseList.add(value);
        }
        testList.addAll(sourseList);
        for (int i = 0; i < sourseList.size(); i++) {
            Assert.assertEquals(sourseList.get(i), testList.get(i));
        }
    }

    @Test
    public void testAddAll2() {
        List<Integer> testList = new MyList<>();
        int startSize = (int) (Math.random() * 100);
        for (int i = 0; i < startSize; i++) {
            int value = (int) (Math.random() * 1000);
            testList.add(value);
        }
        List<Integer> sourseList = new ArrayList<>();
        int starValue2 = (int) (Math.random() * 100);
        for (int i = 0; i < starValue2; i++) {
            int value = (int) (Math.random() * 1000);
            sourseList.add(value);
        }
        testList.addAll(sourseList);
        for (int i = 0; i < sourseList.size(); i++) {
            Assert.assertEquals(sourseList.get(i), testList.get(i + startSize));
        }
    }

    @Test
    public void testCopy() {
        List<Integer> source = new ArrayList<>();
        int starValue2 = 1;
        for (int i = 0; i < starValue2; i++) {
            int value = (int) (Math.random() * 1000);
            source.add(value);
        }
        List<Integer> test = new MyList<>();
        for (int i = 0; i < 100; i++) {
            test.add(1);
        }
        Collections.copy(test, source);
        for (int i = 0; i < source.size(); i++) {
            Assert.assertEquals(source.get(i), test.get(i));
        }
    }

    @Test
    public void testSort() {
        List<Integer> test = new MyList<>();
        for (int i = 0; i < 100; i++) {
            int value = (int) (Math.random() * 500);
            test.add(value);
        }
        Collections.sort(test, Integer::compareTo);
        int oldValue = Integer.MIN_VALUE;
        int newValue = Integer.MAX_VALUE;
        for (int i = 0; i < test.size(); i++) {
            newValue = test.get(i);
            Assert.assertTrue(newValue >= oldValue);
            oldValue = newValue;
        }
    }

    @Test
    public void testSort2() {
        List<Integer> source = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int value = (int) (Math.random() * 500);
            source.add(value);
        }
        List<Integer> test = new MyList<>();
        for (int i = 0; i < 100; i++) {
            test.add(1);
        }
        Collections.copy(test, source);
        Collections.sort(source);
        Collections.sort(test);
        for (int i = 0; i < source.size(); i++) {
            Assert.assertEquals(source.get(i), test.get(i));
        }
    }
}
