package edu.coloradomesa.cs.kotlin

import java.lang.IllegalArgumentException

private fun checkDepth(depth : Double) : Double {
    if (depth > 0.0) {
        return depth;
    }
    var message = "Invalid depth {depth} must be positive"
    throw IllegalArgumentException(message)
}

private fun checkLevel(level : Double, depth : Double) : Double {
    if (0 <= level && level <= depth) {
        return level;
    }
    var message = "Invalid level {level} must be between 0 and {depth}"
    throw IllegalArgumentException(message)
}

class Well {
    var depth : Double
    get() = depth
    set(value) { depth = checkDepth(value) }

    var level : Double
    get() = level
    set(value) { level = checkLevel(value,depth) }

    val isDry : Boolean
    get() = level <= 0.0

    
}