package edu.coloradomesa.cs.collections;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OilWellTest {

    @Test
    public void oilLevel() {
        OilWell instance = new OilWell(10.0);
        assertEquals(0.0, instance.getOilLevel(),0.0);
        instance.setOilLevel(5.0);
        assertEquals(5.0, instance.getOilLevel(),0.0);

    }

    @Test
    public void capped() {
        OilWell instance = new OilWell(10.0);
        assertEquals(false, instance.isCapped());
        instance.setCapped(true);
        assertEquals(true, instance.isCapped());

    }

    @Test
    public void makeDepthAndLevelWell() {
        double depth = 10.0;
        double level = 5.0;
        OilWell instance = new OilWell(depth, level);
        assertEquals(depth, instance.getDepth(),0.0);
        assertEquals(level, instance.getLevel(), 0.0);
    }
}