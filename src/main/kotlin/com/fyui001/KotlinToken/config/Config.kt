package com.fyui001.KotlinToken.config

data class Config(
    val dbHost: String,
    val dbUser: String,
    val dbPassword: String,
    val dbDatabase: String,
    val dbPort: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Config

        if (dbHost != other.dbHost) return false
        if (dbUser != other.dbUser) return false
        if (dbPassword != other.dbPassword) return false
        if (dbDatabase != other.dbPassword) return false
        if (dbPort != other.dbPort) return false

        return true
    }
}