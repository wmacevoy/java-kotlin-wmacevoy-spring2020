package edu.coloradomesa.cs.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

public class BrokenTest {

    @Test
    public void indexOutOfBounds() {
        Broken broken = new Broken();
        broken.indexOutOfBounds();
    }

    @Test
    public void ioExceptionThrows() throws Exception {
        Broken broken = new Broken();
        broken.ioExceptionThrows();
    }

    @Test
    public void ioExceptionCatch() {
        Broken broken = new Broken();
        broken.ioExceptionCatch();
    }
}