package edu.coloradomesa.cs.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class LocationTest {
    @Test
    public void normalize() {
        assertEquals(0.0, Location.normalizeDegreeAngle(360.0), 0.0);
        assertEquals(-90, Location.normalizeDegreeAngle(270.0),0.0);
        assertEquals(90, Location.normalizeDegreeAngle(-270.0),0.0);
        assertEquals(180.0, Location.normalizeDegreeAngle(-180.0), 0.0);
        assertEquals(180.0, Location.normalizeDegreeAngle(180.0), 0.0);
    }

    @Test
    public void getLatitude() {
        Location loc = new Location("a", 1.0, 2.0);
        assertEquals(loc.getLatitude(), 2.0, 0.0);
    }

    @Test
    public void getLongitude() {
        Location loc = new Location("a", 1.0, 2.0);
        assertEquals(loc.getLongitude(), 1.0, 0.0);
    }

    @Test
    public void getName() {
        Location loc = new Location("a", 1.0, 2.0);
        assertEquals(loc.getName(), "a" );
    }

    @Test
    public void compareTo() {
        ArrayList< Location  > a = new ArrayList <Location>();
        a.add(new Location("a", 1,2));
        a.add(new Location("a",1,3));
        a.add(new Location("b",0,0));
        a.add(new Location("b",1,-2));

        for (int i=0; i<a.size(); ++i) {
            assertTrue(a.get(i).compareTo(a.get(i)) == 0);
            for (int j=i+1; j<a.size(); ++j) {
                assertTrue(a.get(i).compareTo(a.get(j)) < 0);
                assertTrue(a.get(j).compareTo(a.get(i)) > 0);
            }
        }
    }

    public static double N(double degrees,double minutes, double seconds) {
        return degrees+minutes/60.0+seconds/3600.0;
    }

    public static double S(double degrees,double minutes, double seconds) {
        return -(degrees+minutes/60.0+seconds/3600.0);
    }

    public static double E(double degrees,double minutes, double seconds) {
        return (degrees+minutes/60.0+seconds/3600.0);
    }

    public static double W(double degrees,double minutes, double seconds) {
        return -(degrees+minutes/60.0+seconds/3600.0);
    }

    // https://www.wolframalpha.com/input/?i=latitude+and+longitude+of+grand+junction+colorado
    Location gj() {
        String name = "gj";
        double lat = N(39,5,11);
        double lon = W(108,34,7);
        Location loc = new Location(name, lon, lat);
        return loc;
    }

    // https://www.wolframalpha.com/input/?i=latitude+and+longitude+of+fruita+colorado
    Location fruita() {
        String name = "fruita";
        double lat = N(39,9,6);
        double lon = W(108,43,25);
        Location loc = new Location(name, lon, lat);
        return loc;

    }

    // https://www.wolframalpha.com/input/?i=latitude+and+longitude+of+salt+lake+city+utah
    Location slc() {
        String name = "slc";
        double lat = N(40,46,43);
        double lon = W(111,55,53);
        Location loc = new Location(name,lon,lat);
        return loc;
    }

    @Test
    public void approximateNauticalMilesTo() {
        ArrayList< Location  > a = new ArrayList <Location>();
        a.add(new Location("a", 1,2));
        a.add(new Location("a",1,3));
        a.add(new Location("b",0,0));
        a.add(new Location("b",1,-2));
        a.add(gj());
        a.add(slc());

        for (int i=0; i<a.size(); ++i) {
            assertEquals(0.0,a.get(i).approximateNauticalMilesTo(a.get(i)), 1e-6);
            for (int j=0; j<a.size(); ++j) {
                    assertEquals(a.get(i).approximateNauticalMilesTo(a.get(j)),
                            a.get(j).approximateNauticalMilesTo(a.get(i)), 1e-6);
            }
        }

    }
    // https://www.wolframalpha.com/input/?i=distance+from+grand+junction%2C+colorado+to+salt+lake+city+utah
    @Test
    public void distGJtoSLC() {
        Location loc1 = gj();
        Location loc2 = slc();

        double miles = 213.3;
        double meters = 343_352.0;
        double nauticalMiles = 185.4;

        assertEquals(nauticalMiles,loc1.approximateNauticalMilesTo(loc2),nauticalMiles*0.01);
        assertEquals(meters,loc1.approximateMetersTo(loc2),meters*0.01);
        assertEquals(miles,loc1.approximateMilesTo(loc2),miles*0.01);
    }

    @Test
    public void nearby() {
        TreeSet<Location> locs = new TreeSet<Location>();
        locs.add(gj());
        locs.add(slc());
        locs.add(fruita());

        TreeSet<Location> near = new TreeSet<Location>();
        Location.addNearbyLocationsInMiles(near,gj(),20.0,locs);
        assertTrue(near.contains(gj()));
        assertTrue(near.contains(fruita()));
        assertFalse(near.contains(slc()));
    }


    @Test
    public void testHashCode() {

        ArrayList< Location  > a = new ArrayList <Location>();
        a.add(new Location("a", 1,2));
        a.add(new Location("a",1,3));
        a.add(new Location("b",0,0));
        a.add(new Location("b",1,-2));
        a.add(gj());
        a.add(slc());

        for (int i=0; i<a.size(); ++i) {
            for (int j=0; j<a.size(); ++j) {
                if (i != j) {
                    assertTrue(a.get(i).hashCode() != a.get(j).hashCode());
                }
            }
        }
    }
}