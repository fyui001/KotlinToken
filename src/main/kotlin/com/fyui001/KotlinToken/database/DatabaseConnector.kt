package com.fyui001.KotlinToken.database

import org.jetbrains.exposed.sql.Database
import java.sql.*

class DatabaseConnector {
    fun connect(user: String, password: String, host: String, port: String, database: String) {
        try {
            Database.connect({
                DriverManager.getConnection(
                    "jdbc:mysql://$host:$port/$database",
                    user,
                    password
                )
            })
        } catch (e: Exception) {
            println(e)
        }
    }
}