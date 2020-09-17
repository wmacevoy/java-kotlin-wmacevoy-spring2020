package edu.coloradomesa.cs.oop;

public class WaterWell extends DefaultWell { // class is a blueprint
    public WaterWell(double depth) {
        super(depth);
    }

    public WaterWell(double depth, double level) {
        super(depth, level);
    }

    public double getWaterLevel() {
        return getLevel();
    }

    // method (setter)
    public void setWaterLevel(double waterLevel) {
        setLevel(waterLevel);
    }

    public boolean isDry() { return isEmpty(); }

    public boolean isPoisoned() {
        return poisoned;
    }

    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
    }

    private boolean poisoned;


}
