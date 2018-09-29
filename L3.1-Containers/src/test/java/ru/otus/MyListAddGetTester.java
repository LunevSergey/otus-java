package ru.otus;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MyListAddGetTester {

    private List<Integer> testList;

    @Before
    public void setUo() {
        testList = new MyList<>();
    }

    @After
    public void tierDown() {
        testList = null;
    }

    @Test
    public void testAdd() {
        testList.add(1);
        Assert.assertEquals(1, (int) testList.get(0));
    }

    @Test
    public void testAddFiftyTimes() {
        for (int i = 0; i < 50; i++) {
            testList.add(i);
            Assert.assertEquals(i, (int) testList.get(i));
        }
    }

    @Test
    public void testAddRandomIndex(){
        List<Object> list = new MyList<>();
        for (int i = 0; i < 1000; i++){
            Object element = new Object();
            int index = (int) (Math.random() * 1000);
            list.add(index, element);
        }
    }
}
