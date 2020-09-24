package edu.coloradomesa.cs.collections;

public interface Well {
    double getDepth();
    void setDepth(double depth);

    double getLevel();
    void setLevel(double level);

    default boolean isEmpty() {
        return getLevel() <= 0.0;
    }

    String getOwner();
    void setOwner(String owner);
}
