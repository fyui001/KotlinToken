package com.fyui001.KotlinToken.main

import com.fyui001.KotlinToken.config.readEnvConfig
import com.fyui001.KotlinToken.database.runDatabaseMigrate
import com.fyui001.KotlinToken.models.Token
import org.koin.core.KoinComponent
import picocli.CommandLine

class KotlinTokenMain {
    class AppCommand {
        @CommandLine.Option(names = ["--config"])
        var configName: String? = null

        @CommandLine.Option(names = ["--db-migrate"])
        var isDbMigrate: Boolean = false
    }
    companion object :  KoinComponent {
        @JvmStatic
        fun main(args: Array<String>) {
            val command = AppCommand()
            CommandLine(command).parseArgs(*args)
            val config = if (command.configName !== null) {
                readEnvConfig(command.configName!!)
            } else {
                readEnvConfig()
            }

            when {
                command.isDbMigrate -> {
                    runDatabaseMigrate(
                        config.dbHost,
                        config.dbUser,
                        config.dbPassword,
                        config.dbDatabase,
                        config.dbPort
                    )
                }
            }
            val token = Token(config)
            token.createTokens(10000)
        }
    }
}