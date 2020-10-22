package edu.coloradomesa.cs.addingwords;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.ByteOrder;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AddingWordsTest {

    @Test
    public void doClear() {
        AddingWords aw = new AddingWords(null,null);
        aw.vars.put("bob",3);
        aw.doClear();
        assertFalse(aw.vars.containsKey("bob"));
    }

    @Test
    public void doDef() {
        AddingWords aw = new AddingWords(null, null);
        aw.command = new String[]{"def", "bob", "3"};
        aw.doDef();
        assertEquals(3, (int) aw.vars.get("bob"));
    }

    @Test
    public void op() {
        AddingWords aw = new AddingWords(null, null);
        aw.vars.put("bob",3);
        aw.vars.put("alice",1);
        assertEquals( (Integer) 4, (Integer) aw.op(3,1, "alice"));
        assertEquals( null, aw.op(null,1, "alice"));
        assertEquals( null, aw.op(3,1, "cindy"));
    }

    @Test
    public void doCalc() throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bos);
        AddingWords aw = new AddingWords(null, ps);
        aw.vars.put("bob",3);
        aw.vars.put("alice",1);
        aw.vars.put("cindy",-2);
        aw.command = new String[] { "calc","alice","-","bob","=" };
        aw.doCalc();
        ps.close();
        String result = bos.toString("UTF-8");
        assertEquals("alice - bob = cindy" + System.lineSeparator(), result);
    }

    @Test
    public void doCalcUnknown() throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bos);
        AddingWords aw = new AddingWords(null, ps);
        // aw.vars.put("bob",3);
        aw.vars.put("alice",1);
        aw.command = new String[] { "calc","alice","-","bob","=" };
        aw.doCalc();
        ps.close();
        String result = bos.toString("UTF-8");
        assertEquals("alice - bob = unknown" + System.lineSeparator(), result);
    }

    @Test
    public void doCommand() {
    }

    @Test
    public void readCommand() {
        String input = "calc alice - bob =" + System.lineSeparator();
        Scanner in = new Scanner(input);
        AddingWords aw = new AddingWords(in, null);
        assertTrue(aw.readCommand());
        assertArrayEquals(new String[]{"calc", "alice", "-", "bob", "="}, aw.command);
    }

    @Test
    public void sampleData() throws Exception {
        String input = "def foo 3\n" +
                "calc foo + bar =\n" +
                "def bar 7\n" +
                "def programming 10\n" +
                "calc foo + bar =\n" +
                "def is 4\n" +
                "def fun 8\n" +
                "calc programming - is + fun =\n" +
                "def fun 1\n" +
                "calc programming - is + fun =\n" +
                "clear";
        String expected = "foo + bar = unknown\n" +
                "foo + bar = programming\n" +
                "programming - is + fun = unknown\n" +
                "programming - is + fun = bar\n";

        Scanner in = new Scanner(input);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bos);
        AddingWords aw = new AddingWords(in, ps);
        aw.run();
        ps.close();
        String result = bos.toString("UTF-8");
        assertEquals(expected , result);


    }
}