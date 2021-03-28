package com.fyui001.KotlinToken.config

import io.github.cdimascio.dotenv.dotenv

fun readEnvConfig(envFileName: String = ".env" ): Config {
    val dotenv = dotenv {
        filename = envFileName
        ignoreIfMissing = true
    }
    return Config(
        dotenv["DB_HOST"],
        dotenv["DB_USER"],
        dotenv["DB_PASSWORD"],
        dotenv["DB_DATABASE"],
        dotenv["DB_PORT"]
    )
}