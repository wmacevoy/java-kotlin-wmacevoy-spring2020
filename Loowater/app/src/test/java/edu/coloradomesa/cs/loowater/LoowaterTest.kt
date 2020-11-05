package edu.coloradomesa.cs.loowater

import org.junit.Test

import org.junit.Assert.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.*

class LoowaterTest {

    @Test
    fun read() {
        var inputString = "2 3\n" +
                "5\n" +
                "4\n" +
                "7\n" +
                "8\n" +
                "4\n" +
                "2 1\n" +
                "5\n" +
                "5\n" +
                "10\n" +
                "0 0\n"

        var inputScanner = Scanner(inputString)

        var loowater = Loowater()
        loowater.input = inputScanner
        loowater.read()

        assertArrayEquals(arrayOf(5,4), loowater.heads.toArray())
        assertArrayEquals(arrayOf(7,8,4), loowater.knights.toArray())
    }
}