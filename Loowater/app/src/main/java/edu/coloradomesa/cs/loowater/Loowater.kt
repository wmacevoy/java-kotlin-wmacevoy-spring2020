package edu.coloradomesa.cs.loowater

import java.io.PrintStream
import java.util.*

class Loowater {
    var input : Scanner = Scanner(System.`in`)
    var output : PrintStream = System.out
    var knights : ArrayList<Int> = ArrayList<Int>()
    var heads : ArrayList<Int> = ArrayList<Int>()
    fun read() : Boolean {
        var nHeads = input.nextInt()
        var nKnights = input.nextInt()

        heads.clear()
        for (i in 0 until nHeads) {
            heads.add(input.nextInt())
        }

        knights.clear()
        for (i in 0 until nKnights) {
            knights.add(input.nextInt())
        }
        return nKnights != 0 && nHeads != 0
    }

}