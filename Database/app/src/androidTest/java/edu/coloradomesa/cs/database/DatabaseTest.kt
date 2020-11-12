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

object Users : Table() {
    val id = varchar("id", 10) // Column<String>
    val name = varchar("name", length = 50) // Column<String>
    val cityId = (integer("city_id") references Cities.id).nullable() // Column<Int?>

    override val primaryKey = PrimaryKey(id, name = "PK_User_ID") // name is optional here
}




/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    val context get() = InstrumentationRegistry.getInstrumentation().targetContext
    @Test
    fun useAppContext() {
        assertEquals("edu.coloradomesa.cs.database", context.packageName)
    }



    @Test
    fun kotlinConnect() {
        var db = DB()
        db.context = context
        db.createTables()
        db.showTables()
        db.dropTables()
    }

    @Test
    fun kotlinDB() {
        var db = DB()
        db.context = context
        db.useDB()
    }

    @Test
    fun javaConnecct() {
        var database = DatabaseJava()
        database.setContext(context)
        var connection = database.connection
        assertNotNull(connection)

    }
}