package edu.coloradomesa.cs.database

import android.content.Context
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNull
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseKotlin {
    constructor(context : Context? = null) {
        this.context = context
    }
    var context : Context?

    val user
        get() = context!!.getString(R.string.databaseUser)

    val host
        get() = context!!.getString(R.string.databaseHost)

    val password
        get() = context!!.getString(R.string.databasePassword)

    val port
        get() = Integer.parseInt(context!!.getString(R.string.databasePort))

    val name
        get() = context!!.getString(R.string.databaseName)

    var connected = false

    fun connect() {
        if (!connected) {
            val url = "jdbc:postgresql://${host}:${port}/${name}"
            val driver = "org.postgresql.Driver";
            Database.connect(url, driver, user, password)
            connected = true
        }
    }

    fun createTables()
    {
        connect()
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Cities, Users)
        }
    }

    fun dropTables() {
        connect()
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.drop(Users, Cities)
        }
    }

    fun showTables() {
        connect()
        transaction {
            addLogger(StdOutSqlLogger)
            val query = "show tables"
            val conn = TransactionManager.current().exec(query)
        }
    }

    fun insertCity(nameValue : String) : Int {
        val idValue = Cities.insert {
            it[name] = nameValue
        } get Cities.id
        return idValue
    }

    fun insertUser(idValue : String, nameValue : String, cityIdValue : Int?) : String {
        val idValue2 = Users.insert {
            it[Users.id] = idValue
            it[Users.name] = nameValue
            it[Users.cityId] = cityIdValue
        } get Users.id
        return idValue2
    }

    fun getCityIdByName(cityName : String) : Int {
        return Cities.select { Cities.name eq cityName }.single()[Cities.id]
    }

    fun initialize() {
        connect()
        dropTables()
        createTables()
        transaction {
            val saintPetersburgId = insertCity("St. Petersburg")
            val munichId = insertCity("Munich")
            val pragueId = insertCity("Prague")
            insertUser("andrey", "Andrey", saintPetersburgId)
            insertUser("sergey", "Sergey", munichId)
            insertUser("eugine", "Eugene", munichId)
            insertUser("alex", "Alex", null)
            insertUser("smth", "Somthing", null)
        }
    }

    fun useDB() {
        initialize()
        transaction {

            Users.update({ Users.id eq "alex" }) {
                it[name] = "Alexey"
            }

            Users.deleteWhere { Users.name like "%thing" }

            println("All cities:")

            for (city in Cities.selectAll()) {
                println("${city[Cities.id]}: ${city[Cities.name]}")
            }

            println("Manual join:")
            (Users innerJoin Cities).slice(Users.name, Cities.name).select {
                (Users.id.eq("andrey") or Users.name.eq("Sergey")) and
                        Users.id.eq("sergey") and Users.cityId.eq(Cities.id)
            }.forEach {
                println("${it[Users.name]} lives in ${it[Cities.name]}")
            }

            println("Join with foreign key:")


            (Users innerJoin Cities).slice(Users.name, Users.cityId, Cities.name)
                .select { Cities.name.eq("St. Petersburg") or Users.cityId.isNull() }.forEach {
                    if (it[Users.cityId] != null) {
                        println("${it[Users.name]} lives in ${it[Cities.name]}")
                    } else {
                        println("${it[Users.name]} lives nowhere")
                    }
                }

            println("Functions and group by:")

            ((Cities innerJoin Users).slice(Cities.name, Users.id.count()).selectAll()
                .groupBy(Cities.name)).forEach {
                    val cityName = it[Cities.name]
                    val userCount = it[Users.id.count()]

                    if (userCount > 0) {
                        println("$userCount user(s) live(s) in $cityName")
                    } else {
                        println("Nobody lives in $cityName")
                    }
                }
        }
    }
}
