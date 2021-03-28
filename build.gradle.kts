plugins {
    id("java")
    id("application")
    id("org.jetbrains.kotlin.jvm") version Deps.kotlinVersion
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClassName = "com.fyui001.KotlinToken.main.KotlinTokenMain"
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Deps.kotlinVersion}")
    implementation("org.koin:koin-core:${Deps.koinVersion}")
    implementation("org.jetbrains.exposed:exposed:0.17.7")
    implementation("mysql:mysql-connector-java:${Deps.mysqlVersion}")
    implementation("io.github.cdimascio:dotenv-kotlin:6.2.2")
    implementation("org.flywaydb:flyway-core:${Deps.flywayVersion}")
    implementation("info.picocli:picocli:4.2.0")
}
