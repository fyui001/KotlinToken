package com.fyui001.KotlinToken.models

import kotlin.random.Random
import org.jetbrains.exposed.dao.*
import com.fyui001.KotlinToken.database.DatabaseConnector
import com.fyui001.KotlinToken.config.Config
import org.jetbrains.exposed.sql.transactions.transaction

class Token() {

    private val db = DatabaseConnector()
    private val charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    private var length = 64

    constructor(config: Config) : this() {
        this.db.connect(config.dbUser, config.dbPassword, config.dbHost, config.dbPort, config.dbDatabase)
    }

    object tokens: IntIdTable() {
        val token = varchar("token", length = 255)
        val delFlg = integer("del_flg")
    }

    class Token(id: EntityID<Int>) : IntEntity(id) {
        companion object : IntEntityClass<Token>(tokens)
        var token by tokens.token
        var delFlg by tokens.delFlg
    }

    private fun genToken(length: Int = this.length, charSet: String = this.charSet): String {
        var s = ""
        for (i in 0 until length) {
            val rand = Random.nextInt(charSet.length)
            s += charSet[rand]
        }
        return s
    }

    fun createTokens(count: Int): Boolean {

        var i: Int = 0
        var arrToken: MutableMap<String, Int> = mutableMapOf()

        while (i <= (count - 1)) {
            val token = genToken()
            if (arrToken.isEmpty()) {
                arrToken = mutableMapOf(token to 0)
                ++i
                continue
            }
            if (arrToken[token] == 0) {
                continue
            }
            arrToken[token] = 0
            ++i
        }
        println(arrToken.count())
        transaction {
            arrToken.forEach{ (genToken, flag) ->
                Token.new {
                    token = genToken
                    delFlg = flag
                }
            }
        }
        return true
    }
}