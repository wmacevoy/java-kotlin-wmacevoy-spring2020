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

    public boolean isCapped() {
        return capped;
    }

    public void setCapped(boolean capped) {
        this.capped = capped;
    }

    private boolean capped = false;
}
