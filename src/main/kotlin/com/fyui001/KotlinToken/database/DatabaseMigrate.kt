package com.fyui001.KotlinToken.database

import org.flywaydb.core.Flyway

fun runDatabaseMigrate(dbHost: String, dbUser: String, dbPassword: String, dbDatabase: String, dbPort: String) {
    println("====== MIGRATION MODE ======")
    val flyway = Flyway.configure()
        .dataSource("jdbc:mysql://$dbHost:$dbPort/$dbDatabase", dbUser, dbPassword)
        .load()
    println("\n** Current Migration Status **")
    flyway.info().all().forEach {
        println("=> [V${it.version.version}][${it.state.displayName}] ${it.description}")
    }
    println("\nRunning incomplete migration above...")
    flyway.migrate()
    println("Migration done!")
}