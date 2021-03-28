package com.fyui001.KotlinToken.database

import kotlin.random.Random
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.*
import com.fyui001.KotlinToken.database.DatabaseConnector
import com.fyui001.KotlinToken.config.Config
import org.jetbrains.exposed.sql.transactions.transaction

class Token() {

    private val db = DatabaseConnector()
    private val charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    private var length = 64

    constructor(config: Config) : this() {
        this.db.connect(config.dbUser, config.dbPassword, config.dbHost, config.dbPort, config.dbDatabase)
        println("Token")
    }

    object tokens: Table() {
        val id = integer("id").autoIncrement().primaryKey()
        val token = varchar("token", length = 255)
        val delFlg = integer("del_flg")
    }

    private fun genToken(length: Int = this.length, charSet: String = this.charSet): String {
        var s = ""
        for (i in 0 until length) {
            val rand = Random.nextInt(charSet.length)
            s += charSet[rand]
        }
        return s
    }

    fun main(): Boolean {
        transaction {
            val genToken = genToken()
            tokens.insert {
                it[token] = genToken
                it[delFlg] = 0
            }
        }
        println("Token!")
        return true
    }
}