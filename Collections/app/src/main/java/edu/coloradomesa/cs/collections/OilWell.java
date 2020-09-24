package edu.coloradomesa.cs.collections;

public class OilWell extends DefaultWell { // class is a blueprint

    public OilWell(double depth) {
        super(depth);
    }

    public OilWell(double depth, double level) {
        super(depth, level);
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
