package edu.coloradomesa.cs.oop;

import org.junit.Test;

import static org.junit.Assert.*;

public class WaterWellTest {

    @Test(expected = IllegalArgumentException.class)
    public void invalidDepth() {
        double badWellDepth = -35.2;
        String exceptionMessage = "Invalid depth " + badWellDepth + ". Must be positive.";
        try {
            WaterWell instance = new WaterWell(badWellDepth);
        } catch (IllegalArgumentException ex) {
            assertEquals(exceptionMessage, ex.getMessage());
            throw ex;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidWaterLevel() throws Exception {
        double wellDepth = 35.2;
        WaterWell instance = new WaterWell(wellDepth);
        double invalidWaterLevel = -1.0;
        instance.setWaterLevel(invalidWaterLevel);
    }

    @Test
    public void getDepth() {
        double wellDepth = 35.2;
        WaterWell instance = new WaterWell(wellDepth);
        assertEquals("testing bad well.",wellDepth, instance.getDepth(),0.0);
    }

    @Test
    public void isDryTrue() {
        double wellDepth = 35.2;
        WaterWell instance = new WaterWell(wellDepth);
        assertEquals(true, instance.isDry());
    }

    @Test
    public void isDryFalse() {
        double wellDepth = 35.2;
        WaterWell instance = new WaterWell(wellDepth);
        double waterLevel = 0.0;
        instance.setWaterLevel(waterLevel);
        assertEquals(true, instance.isDry());
    }

    @Test
    public void getWaterLevel() {
        double wellDepth = 35.2;
        WaterWell instance = new WaterWell(wellDepth);
        assertEquals(0.0, instance.getWaterLevel(), 0.0);
    }

    @Test
    public void setWaterLevel() {
        double wellDepth = 35.2;
        WaterWell instance = new WaterWell(wellDepth);
        assertEquals(0.0, instance.getWaterLevel(), 0.0);
        double newWaterLevel = 13.5;
        instance.setWaterLevel(newWaterLevel);
        assertEquals(newWaterLevel, instance.getWaterLevel(), 0.0);
    }

    @Test
    public void poisoned() {
        double depth = 35.2;
        WaterWell instance = new WaterWell(depth);
        double waterLevel = 13.5;
        assertEquals(false,instance.isPoisoned());
        instance.setPoisoned(true);
        assertEquals(true,instance.isPoisoned());
    }

    void makeABadWell() {
        WaterWell bad = new WaterWell(-3.9);
        System.out.println("Well depth is " + bad.getDepth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void showCatchingException() {
        makeABadWell();
    }
}