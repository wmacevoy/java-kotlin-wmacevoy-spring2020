package edu.coloradomesa.cs.collections;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Property {
    private Map<Location, OilWell> oilWells = new TreeMap<Location, OilWell>();
    private Map<Location, WaterWell> waterWells = new TreeMap<Location, WaterWell>();

    public void addOilWellAt(Location at, OilWell oilWell) {
        oilWells.put(at, oilWell);
    }

    public void addWaterWellAt(Location at, WaterWell waterWell) {
        waterWells.put(at, waterWell);
    }

    public Set<Location> getOilWellLocations() {
        return oilWells.keySet();
    }

    public Set<Location> getWaterWellLocations() {
        return waterWells.keySet();
    }

    public WaterWell getWaterWellAt(Location at) {
        return waterWells.get(at);
    }

    public OilWell getOilWellAt(Location at) {
        return oilWells.get(at);
    }

    public Set<Location> waterWellsNearInMeters(Location center, double cutoffInMeters) {
        TreeSet<Location> nearby = new TreeSet<Location>();
        Location.addNearbyLocationsInMeters(nearby, center, cutoffInMeters, waterWells.keySet());
        return nearby;
    }

    public Set<Location> oilWellsNearInMeters(Location center, double cutoffInMeters) {
        TreeSet<Location> nearby = new TreeSet<Location>();
        Location.addNearbyLocationsInMeters(nearby, center, cutoffInMeters, oilWells.keySet());
        return nearby;
    }
}
