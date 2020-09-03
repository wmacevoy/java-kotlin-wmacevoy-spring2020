package edu.coloradomesa.cs.objects;

public class Well { // class is a blueprint

    public Well(double depth) {
        if (depth > 0.0) {
            this.depth = depth;
        } else {
            String message = "Invalid depth " + depth  + "." +
                    " Must be positive.";
            throw new IllegalArgumentException(message);

        }
    }
    // property
    private double depth; // is a property of every well.

    // method (getter)
    public double getDepth() {
        return depth;
    }

    // computed property
    public boolean isDry() {
        return waterLevel <= 0.0;
    }

    private double waterLevel; // is a property of every well.

    public double getWaterLevel() {
        return waterLevel;
    }

    // method (setter)
    public void setWaterLevel(double waterLevel /* this is an argument */) {
        if (0 <= waterLevel && waterLevel <= depth) {
            this.waterLevel = waterLevel;
        } else {
            String message = "Invalid water level " + waterLevel + "." +
                    " Must be between 0.0 and " + depth + " (inclusive).";
            throw new IllegalArgumentException(message);
        }
    }

    public boolean isPoisoned() {
        return poisoned;
    }

    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
    }

    public boolean poisoned;


}
