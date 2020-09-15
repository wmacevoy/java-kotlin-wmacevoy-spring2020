package edu.coloradomesa.cs.objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class WellTest {

    @Test(expected = IllegalArgumentException.class)
    public void invalidDepth() {
        double badWellDepth = -35.2;
        String exceptionMessage = "Invalid depth " + badWellDepth + ". Must be positive.";
        try {
            Well instance = new Well(-badWellDepth);
        } catch (IllegalArgumentException ex) {
            assertEquals(exceptionMessage, ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidWaterLevel() throws Exception {
        double wellDepth = 35.2;
        Well instance = new Well(wellDepth);
        double invalidWaterLevel = -1.0;
        instance.setWaterLevel(invalidWaterLevel);
    }

    @Test
    public void getDepth() {
        double wellDepth = 35.2;
        Well instance = new Well(wellDepth);
        assertEquals("testing bad well.",wellDepth, instance.getDepth(),0.0);
    }

    @Test
    public void isDryTrue() {
        double wellDepth = 35.2;
        Well instance = new Well(wellDepth);
        assertEquals(false, instance.isDry());
    }

    @Test
    public void isDryFalse() {
        double wellDepth = 35.2;
        Well instance = new Well(wellDepth);
        double waterLevel = 10.0;
        instance.setWaterLevel(waterLevel);
        assertEquals(false, instance.isDry());
    }

    @Test
    public void getWaterLevel() {
        double wellDepth = 35.2;
        Well instance = new Well(wellDepth);
        assertEquals(0.0, instance.getWaterLevel(), 0.0);
    }

    @Test
    public void setWaterLevel() {
        double wellDepth = 35.2;
        Well instance = new Well(wellDepth);
        assertEquals(0.0, instance.getWaterLevel(), 0.0);
        double newWaterLevel = 13.5;
        instance.setWaterLevel(newWaterLevel);
        assertEquals(newWaterLevel, instance.getWaterLevel(), 0.0);
    }

    @Test
    public void poisoned() {
        double depth = 35.2;
        Well instance = new Well(depth);
        double waterLevel = 13.5;
        assertEquals(false,instance.isPoisoned());
        instance.setPoisoned(true);
        assertEquals(true,instance.isPoisoned());
    }

    void makeABadWell() {
        Well bad = new Well(-3.9);
        System.out.println("Well depth is " + bad.getDepth());
    }

    @Test
    public void showCatchingException() {
        try {
            makeABadWell();
        } catch (IllegalArgumentException ex) {
            System.out.println("caught exception: " + ex);
            ex.printStackTrace();
        } finally {
            // executed whether and exception happened or not.
        }
    }
}