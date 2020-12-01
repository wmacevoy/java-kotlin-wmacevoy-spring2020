package edu.coloradomesa.cs.threads;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {
    String randomString() {
        int len = (int)(Math.random()*10+1);
        StringBuilder sb = new StringBuilder(len);
        for (int i=0; i<len; ++i) {
            char c = (char)('a' + (int)(Math.random()*26));
            sb.append(c);
        }
        return sb.toString();
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
        while (!main.done()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}