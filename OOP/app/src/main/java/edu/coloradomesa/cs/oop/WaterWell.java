package edu.coloradomesa.cs.oop;

import java.util.ArrayList;

public class WaterWell extends DefaultWell { // class is a blueprint
    interface Brick {
        boolean isDry();
    }

    // inner class
    public class MiddleBrick implements Brick {
        double heightAboveWellBottom;
        MiddleBrick(double heightAboveWellBottom) {
            this.heightAboveWellBottom = heightAboveWellBottom;
        }
        public boolean isDry() {
            return getWaterLevel() < heightAboveWellBottom;
        }
    }

    public class WetBrick implements Brick {
        @Override public boolean isDry() {
            return false;
        }
    }

    ArrayList < Brick > bricks = new ArrayList< Brick >();

    public WaterWell(double depth) {
        super(depth);
        bricks.add(new Brick() {
            @Override public boolean isDry() {
                return false;
            }
        });
        for (int i=1; i<=depth + 3;  ++i) {
                bricks.add(new MiddleBrick(i));
        }
        bricks.add(()->true);
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
