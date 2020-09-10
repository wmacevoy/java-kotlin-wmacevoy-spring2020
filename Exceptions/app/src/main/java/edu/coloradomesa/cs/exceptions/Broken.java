package edu.coloradomesa.cs.exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Broken {
    public void indexOutOfBounds() {
        int [] a = new int[10];

        a[10]=11;

    }

    public void ioExceptionThrows() throws FileNotFoundException {
        FileInputStream in = new FileInputStream("missing file.dat");
    }

    public void ioExceptionCatch() {
        try {
            FileInputStream in = new FileInputStream("missing file.dat");
        } catch (FileNotFoundException e) {
            InputStream is = System.in;
            // ...
        }
    }

    public void cutHair() throws BadHaircutException {
        ///
        throw new BadHaircutException("wow that's a bad haircut!");
    }
}
