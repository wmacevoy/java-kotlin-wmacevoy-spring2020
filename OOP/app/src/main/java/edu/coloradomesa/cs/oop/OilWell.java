package edu.coloradomesa.cs.oop;

public class OilWell extends DefaultWell { // class is a blueprint

    public OilWell(double depth) {
        super(depth);
    }


    public double getOilLevel() {
        return getLevel();
    }

    // method (setter)
    public void setOilLevel(double oilLevel) {
        setLevel(oilLevel);
    }
}
