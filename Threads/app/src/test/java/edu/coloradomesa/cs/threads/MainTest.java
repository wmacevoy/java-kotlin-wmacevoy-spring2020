package edu.coloradomesa.cs.threads;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class MainTest {
    int random(int min, int max) {
        int range = max - min + 1;
        int value = min + (range > 1 ? ThreadLocalRandom.current().nextInt(range) : 0);
        return value;
    }

    String random(int minLen, int maxLen, int[] codePoints) {
        int len = random(minLen,maxLen);
        StringBuilder sb = new StringBuilder(len);
        for (int i=0; i<len; ++i) {
            int cp = codePoints[random(0,codePoints.length-1)];
            sb.appendCodePoint(cp);
        }
        String value = sb.toString();
        return value;
    }

    static final String LETTER_STRING = "123abd😀🤪🧐";
    static final int[] LETTERS = LETTER_STRING.codePoints().toArray();

    String randomString() {
        return random(1,10,LETTERS);
    }

    Message random() {
        return new Message(randomString(),randomString());
    }

    @Test
    public void sends() {
        Main main = new Main();
        int n = 10;
        for (int i=0; i<n; ++i) {
            main.setAddress(randomString());
            main.setContent(randomString());
            main.send();
        }
        main.waitUntilDone();
    }
}