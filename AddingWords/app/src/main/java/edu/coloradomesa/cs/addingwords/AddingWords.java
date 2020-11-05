package edu.coloradomesa.cs.addingwords;

// solves kattis problem: https://open.kattis.com/problems/addingwords
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class AddingWords {
    Scanner in;
    PrintStream out;

    public AddingWords(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public static void main(String [] args) throws Exception {
        AddingWords addingWords = new AddingWords(new Scanner(System.in), System.out);
        addingWords.run();
    }

    public void run() throws Exception {
        while (readCommand()) {
            doCommand();
        }
    }

    String[] command = new String[0];
    TreeMap < String, Integer > vars = new TreeMap< String , Integer >();
    void doClear() {
        vars.clear();
    }

    void doDef() {
        vars.put(command[1],Integer.parseInt(command[2]));
    }

    public Integer op(Integer a, int addSub, String value) {
        if (a == null) return null;
        if (vars.containsKey(value)) {
            return a  + addSub*vars.get(value);
        } else {
            return null;
        }
    }

    void doCalc() {
        Integer answer = op(0,1,command[1]);
        out.print(command[1]);
        int at = 2;
        while (at < command.length) {
            switch (command[at]) {
                case "+":
                    answer = op(answer, 1, command[at+1]);
                    out.print(" " + command[at] + " " + command[at+1]);
                break;
                case "-":
                    answer = op(answer,  -1, command[at+1]);
                    out.print(" " + command[at] + " " + command[at+1]);
                break;
                case "=":
                    String name = null;
                    if (answer != null) {
                        for (String var : vars.keySet()) {
                            if (vars.get(var).equals(answer)) {
                                name = var;
                                break;
                            }
                        }
                    }
                    out.println(" = " + ((name != null) ? name : "unknown"));
                    break;
            }
            at += 2;
        }
    }


    public void doCommand() {
        switch(command[0]) {
            case "def" : doDef(); break;
            case "calc": doCalc(); break;
            case "clear": doClear(); break;
        }
    }

    public boolean readCommand() {
        if (in.hasNextLine()) {
            String line = in.nextLine();
            command = line.split(" ");
            return true;
        } else {
            return false;
        }
    }
}
