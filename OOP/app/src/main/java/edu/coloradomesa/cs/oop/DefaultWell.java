package edu.coloradomesa.cs.oop;

public class DefaultWell implements Well {
    private double depth;

    private static double checkDepth(double depth) {
        if (depth > 0.0) {
            return depth;
        }
            String message = "Invalid depth " + depth  + "." +
                    " Must be positive.";
            throw new IllegalArgumentException(message);
    }
    @Override
    public double getDepth() {
        return depth;
    }

    @Override
    public void setDepth(double depth) {
        this.depth = checkDepth(depth);
    }

    private double level;
    private static double levelCheck(double level, double depth) {
        if (0 <= level && level <= depth) {
            return level;
        }
        String message = "Invalid depth  " + depth + "." +
                " Must be positive.";
        throw new IllegalArgumentException(message);
    }

    @Override
    public double getLevel() {
        return level;
    }

    @Override
    public void setLevel(double level) {
        this.level = levelCheck(level,depth);
    }


    private String owner="";
    private String checkOwner(String owner) {
        if (owner != null) {
            return owner;
        }
        String message = "Invalid owner (null).  Must mot be null.";
        throw new IllegalArgumentException(message);
    }
    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = checkOwner(owner);
    }

    public DefaultWell(double depth) {
        this.depth= checkDepth(depth);
        this.level = 0.0;
        this.owner = "";
    }
}
