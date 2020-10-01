package edu.coloradomesa.cs.collections;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Property {
    private Map<Location, OilWell> oilWells = new TreeMap<Location, OilWell>();
    private Map<Location, WaterWell> waterWells = new TreeMap<Location, WaterWell>();

    private List<String> owners = new ArrayList<String> (100);

    // [null, null, null, ....] (100 nulls)
    // ^ size = 0
    // owners.add("bob");
    // ["bob",null,....]
    //        ^ size = 1
    //   * -- heirarchy assignment (OOP)
    //  / \
    //  *  *

    // private List<byte>  privateKeySignature = ArrayList<byte> (256);
    // sizeof(Byte) == 20 bytes. (16 for an object, 1 for a byte, round to 4-byte boundary, 20, or maybe 24).
    private List<Byte>  privateKeySignature = new ArrayList<Byte> (256);
    void boolExample() {
        ArrayList < Boolean > yns = new ArrayList<>();
        yns.add(Boolean.valueOf(true)); // re-use Boolean.TRUE and Boolean.FALSE, not extra objects
        yns.add(new Boolean(true));
        yns.add(new Boolean(false));
        yns.add(new Boolean(true));
        yns.add(new Boolean(true));

    }

    public void addToKeySignature(byte value) {
        privateKeySignature.add(value);
        privateKeySignature.add(Byte.valueOf(value));
// done as        privateKeySignature.add((Object) new Byte(value));
        byte thirdValue = privateKeySignature.get(2);
        byte thirdValueLonghandWay = ((Byte) (Object) privateKeySignature.get(2)).byteValue();
        privateKeySignature.sort((a,b) -> a-b);
        privateKeySignature.sort((a,b)->a.compareTo(b));
        List<String> lines = Arrays.asList("spring", "node", "mkyong");

        lines.parallelStream().filter(p->p.endsWith("g")).forEachOrdered(System.out::println);              // convert list to stream

        List<String> result = lines.parallelStream()                // convert list to stream
                .filter(line -> !"mkyong".equals(line))     // we dont like mkyong
                .collect(Collectors.toList());              // collect the output and convert streams to a List

        result.forEach(System.out::println);
    }
    // List<Object> - Type Erasure
    // std::vector < unsigned char > pks ( 256 );

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
