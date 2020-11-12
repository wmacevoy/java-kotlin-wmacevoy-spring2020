package edu.coloradomesa.cs.database

import android.content.Context
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.jetbrains.exposed.sql.transactions.TransactionManager

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.sql.Connection


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    // Kotlin style getter for test application context
    val context get() = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun useAppContext() {
        assertEquals("edu.coloradomesa.cs.database", context.packageName)
    }



    @Test
    fun kotlinConnect() {
        var db = DatabaseKotlin(context)
        db.initialize()
    }

    @Test
    fun kotlinDB() {
        var dbk = DatabaseKotlin(context)
        dbk.useDB()
    }

    @Test
    fun javaConnect() {
        var dbj = DatabaseJava(context)
        var connection = dbj.connection
        assertNotNull(connection)

    }

    @Test
    fun eqContext() {
        var dbk = DatabaseKotlin(context)
        var dbj = DatabaseJava(context)
        assertEquals(dbk.user,dbj.user)
        assertEquals(dbk.password,dbj.password)
        assertEquals(dbk.host,dbj.host)
        assertEquals(dbk.port,dbj.port)
    }
}