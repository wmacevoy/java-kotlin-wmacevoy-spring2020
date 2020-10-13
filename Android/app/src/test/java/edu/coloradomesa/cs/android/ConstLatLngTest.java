package edu.coloradomesa.cs.android;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConstLatLngTest {

    @Test
    public void normalizeAngle() {
        assertEquals(ConstLatLng.normalizeAngle(3600.0),0.0,0.0);
        assertEquals(ConstLatLng.normalizeAngle(-3600.0),0.0,0.0);
        assertEquals(ConstLatLng.normalizeAngle(-180.0),180.0,0.0);
        assertEquals(ConstLatLng.normalizeAngle(180.0),180.0,0.0);
    }


    @Test
    public void getLatLng() {
        ConstLatLng p = new ConstLatLng(1,2);
        LatLng q = p.getLatLng();

        assertEquals(1,q.latitude,0.0);
        assertEquals(2,q.longitude,0.0);
    }

    @Test
    public void testToString() {
        ConstLatLng p = new ConstLatLng(1,2);
        String expect = "{\"latitude\":1.000000000000,\"longitude\":2.000000000000}";
        assertEquals(expect,p.toString());
    }

    @Test
    public void testToString2() {
        ConstLatLng p = new ConstLatLng(180,-179);
        String expect = "{\"latitude\":180.000000000000,\"longitude\":-179.000000000000}";
        assertEquals(expect,p.toString());
    }

    @Test
    public void compareTo() {
        assertEquals(-1,new ConstLatLng(1,2).compareTo(new ConstLatLng(2,3)));
        assertEquals(1,new ConstLatLng(2,4).compareTo(new ConstLatLng(2,3)));
        assertEquals(0,new ConstLatLng(2,4).compareTo(new ConstLatLng(2,4)));
    }

    @Test
    public void testEquals() {
        assertTrue(new ConstLatLng(2,4).equals(new ConstLatLng(2,4)));
    }

    @Test
    public void testHashCode() {
        assertNotEquals(new ConstLatLng(1,2).hashCode(),new ConstLatLng(2,3).hashCode());
        assertNotEquals(new ConstLatLng(2,4).hashCode(),new ConstLatLng(2,3).hashCode());
        assertEquals(new ConstLatLng(2,4).hashCode(),new ConstLatLng(2,4).hashCode());
    }

    @Test
    public void json() {
        ConstLatLng p = new ConstLatLng(180,-179);
        String json = "{\"latitude\":180.000000000000,\"longitude\":-179.000000000000}";
        ConstLatLng q = new ConstLatLng(json);
        assertEquals(p,q);

    }
}