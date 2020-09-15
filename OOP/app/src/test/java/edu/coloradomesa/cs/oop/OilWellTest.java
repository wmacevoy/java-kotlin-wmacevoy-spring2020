package edu.coloradomesa.cs.oop;

import org.junit.Test;

import static org.junit.Assert.*;

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
    public void setCapped() {
    }
}