package edu.coloradomesa.cs.oop;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DefaultWellTest {

    @Test(expected = IllegalArgumentException.class)
    public void invalidConstructedDepth() {
        double badWellDepth = -35.2;
        String exceptionMessage = "Invalid depth " + badWellDepth + ". Must be positive.";
        try {
            DefaultWell instance = new DefaultWell(badWellDepth);
        } catch (IllegalArgumentException ex) {
            assertEquals(exceptionMessage, ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidSetDepth() {
        double goodWellDepth = 10.0;
        double badWellDepth = -35.2;
        DefaultWell instance = null;
        try {
            instance = new DefaultWell(goodWellDepth);
        } catch (IllegalArgumentException ex) {
            fail(); // construction should pass
        }
        String exceptionMessage = "Invalid depth " + badWellDepth + ". Must be positive.";
        try {
            instance.setDepth(badWellDepth);
        } catch (IllegalArgumentException ex) {
            assertEquals(exceptionMessage, ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidLevel() throws Exception {
        double wellDepth = 35.2;
        DefaultWell instance = new DefaultWell(wellDepth);
        double invalidLevel = -1.0;
        instance.setLevel(invalidLevel);
    }

    @Test
    public void getDepth() {
        double wellDepth = 35.2;
        DefaultWell instance = new DefaultWell(wellDepth);
        assertEquals("testing bad well.",wellDepth, instance.getDepth(),0.0);
    }

    @Test
    public void isEmptyTrue() {
        double wellDepth = 35.2;
        DefaultWell instance = new DefaultWell(wellDepth);
        assertEquals(true, instance.isEmpty());
    }

    @Test
    public void isEmptyFalse() {
        double wellDepth = 35.2;
        DefaultWell instance = new DefaultWell(wellDepth);
        double Level = 0.0;
        instance.setLevel(Level);
        assertEquals(true, instance.isEmpty());
    }

    @Test
    public void getLevel() {
        double wellDepth = 35.2;
        DefaultWell instance = new DefaultWell(wellDepth);
        assertEquals(0.0, instance.getLevel(), 0.0);
    }

    @Test
    public void setLevel() {
        double wellDepth = 35.2;
        DefaultWell instance = new DefaultWell(wellDepth);
        assertEquals(0.0, instance.getLevel(), 0.0);
        double newLevel = 13.5;
        instance.setLevel(newLevel);
        assertEquals(newLevel, instance.getLevel(), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void owner() {
        double depth = 35.2;
        DefaultWell instance = new DefaultWell(depth);
        assertEquals("", instance.getOwner());
        instance.setOwner("alice");
        assertEquals("alice", instance.getOwner());
        try {
            instance.setOwner(null);
            fail();
        } catch (IllegalArgumentException ex) {
            assertEquals("Invalid owner (null).  Must mot be null.", ex.getMessage());
            throw ex;
        }
    }

    void makeABadWell() {
        DefaultWell bad = new DefaultWell(-3.9);
        System.out.println("Well depth is " + bad.getDepth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void showCatchingException() {
        makeABadWell();
    }
}