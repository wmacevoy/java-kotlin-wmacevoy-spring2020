package edu.coloradomesa.cs.collections;

import java.util.Collection;
import java.util.Set;

import static java.lang.Math.*;

// Immutable!
public class Location implements Comparable < Location > {
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    private String name;
    private double latitude; // degrees
    private double longitude; // degrees;

    public static double normalizeDegreeAngle(double angle) {
        while (angle > 180.0) { angle -= 360.0; }
        while (angle <= -180.0) { angle += 360.0; }
        return angle;
    }

    public Location(String name, double longitude, double latitude) {
        this.name=name;
        this.longitude=normalizeDegreeAngle(longitude);
        this.latitude=normalizeDegreeAngle(latitude);
    }

    @Override
    public int compareTo(Location other) {
        int cmp;
        cmp = this.name.compareTo(other.name);
        if (cmp != 0) {
            return cmp;
        }
        cmp = (this.longitude < other.longitude) ? -1 : (this.longitude > other.longitude) ? 1 : 0;
        if (cmp != 0) {
            return cmp;
        }
        cmp = (this.latitude < other.latitude) ? -1 : (this.latitude > other.latitude) ? 1 : 0;
        if (cmp != 0) {
            return cmp;
        }
        return 0;
    }

    public double approximateNauticalMilesTo(Location to) {
        // https://en.wikipedia.org/wiki/Great-circle_distance
        // 1 nautical mile is 1 minute of arc latitude
        // assumes earth is a sphere (1% error)

        double lambda1 = toRadians(longitude);
        double lambda2 = toRadians(to.longitude);
        double deltaLambda = lambda2 - lambda1;
        double phi1 = toRadians(latitude);
        double phi2 = toRadians(to.latitude);

        double sinPhi1 = sin(phi1);
        double sinPhi2 = sin(phi2);
        double cosPhi1 = cos(phi1);
        double cosPhi2 = cos(phi2);
        double sinDeltaLambda = sin(deltaLambda);
        double cosDeltaLambda = cos(deltaLambda);

        double a = cosPhi2 * sinDeltaLambda;
        double b = cosPhi1 * sinPhi2 - sinPhi1 * cosPhi2 * cosDeltaLambda;
        double c = sinPhi1 * sinPhi2 + cosPhi1 * cosPhi2 * cosDeltaLambda;
        double delta = toDegrees(atan(sqrt(pow(a, 2) + pow(b, 2))) / c) * 60.0;

        return delta;
    }

    // https://en.wikipedia.org/wiki/Nautical_mile
    public static final double METERS_PER_NAUTICAL_MILE=1852.0;
    // https://en.wikipedia.org/wiki/Mile
    public static final double METERS_PER_MILE=1609.344;

    public double approximateMetersTo(Location to) {
        return approximateNauticalMilesTo(to)*METERS_PER_NAUTICAL_MILE;
    }

    public double approximateMilesTo(Location to) {
        return approximateMetersTo(to)/METERS_PER_MILE;
    }

    @Override
    public int hashCode() {
        return 7*name.hashCode()+13*Double.hashCode(this.longitude)+17*Double.hashCode(this.latitude);
    }

    public static void addNearbyLocationsInNauticalMiles(Collection<Location> results, Location center, double nauticalMiles, Collection<Location> search)  {
        for (Location at : search) {
            if (center.approximateNauticalMilesTo(at) <= nauticalMiles) {
                results.add(at);
            }
        }
    }

    public static void addNearbyLocationsInMeters(Collection<Location> results, Location center, double meters, Collection<Location> search)  {
        double nauticalMiles = meters/METERS_PER_NAUTICAL_MILE;
        addNearbyLocationsInNauticalMiles(results,center,nauticalMiles, search);
    }

    public static void addNearbyLocationsInMiles(Collection<Location> results, Location center, double miles, Collection<Location> search)  {
        double meters = miles*METERS_PER_MILE;
        addNearbyLocationsInMeters(results,center,meters, search);
    }
}
