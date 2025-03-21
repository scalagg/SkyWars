import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    `maven-publish`
    kotlin("jvm") version "2.0.0"
    kotlin("kapt") version "2.0.0"
    id("org.ajoberstar.grgit") version "4.1.1"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

var currentBranch: String = grgit.branch.current().name
if (currentBranch != "master") {
    println("Starting in development mode")
}

allprojects {
    group = "gg.scala.skywars"
    version = "1.1.0${
        if (currentBranch == "develop") "-dev" else ""
    }"

    repositories {
        configureScalaRepository()
        mavenCentral()
        mavenLocal()
        maven {
            name = "lunarclient"
            url = uri("https://repo.lunarclient.dev")
        }
        maven("https://repo.glaremasters.me/repository/concuncan/")
        maven("https://repo.dmulloy2.net/repository/public/")
    }
}

    subprojects {
        apply(plugin = "org.jetbrains.kotlin.jvm")
        apply(plugin = "org.jetbrains.kotlin.kapt")
        apply(plugin = "com.github.johnrengelman.shadow")
        apply(plugin = "maven-publish")

    dependencies {
        compileOnly(kotlin("stdlib"))

        compileOnly("net.kyori:adventure-api:4.14.0")
        compileOnly("gg.scala.spigot:server:1.1.0")

        compileOnly("gg.scala.commons:bukkit:4.0.0")
        kapt("gg.scala.commons:bukkit:4.0.0")

        compileOnly("gg.scala.lemon:bukkit:1.9.1")
        compileOnly("gg.scala.cgs:common:1.4.2")


        compileOnly("gg.scala.store:spigot:0.1.9")
        compileOnly("gg.scala.store:shared:0.1.9")

        compileOnly("gg.scala.cloudsync:spigot:1.0.3")
        compileOnly("gg.scala.game.extensions:scala-core-game-extensions:1.3.0")

        compileOnly("com.lunarclient:apollo-api:1.1.6")
        compileOnly("com.grinderwolf:slimeworldmanager-plugin:2.2.1")
    }

    kapt {
        keepJavacAnnotationProcessors = true
    }

    tasks.withType<JavaCompile> {
        options.compilerArgs.add("-parameters")
        options.fork()
        options.encoding = "UTF-8"
    }

    kotlin {
        jvmToolchain(jdkVersion = 17)
    }

    tasks {
        withType<ShadowJar> {
            archiveClassifier.set("")
            exclude(
                "**/*.kotlin_metadata",
                "**/*.kotlin_builtins"
            )

            archiveFileName.set(
                "ScSkyWars-${project.name}.jar"
            )
        }

        withType<JavaCompile> {
            options.compilerArgs.add("-parameters")
            options.fork()
            options.encoding = "UTF-8"
        }

        withType<KotlinCompile> {
            kotlinOptions.javaParameters = true
            kotlinOptions.jvmTarget = "17"
        }

        getByName("build") {
            dependsOn(
                "shadowJar",
                "publishMavenJavaPublicationToScalaRepository"
            )
        }
    }

    publishing {
        repositories.configureScalaRepository(dev = currentBranch != "master")

        publications {
            register(
                name = "mavenJava",
                type = MavenPublication::class,
                configurationAction = shadow::component
            )
        }
    }
}

fun RepositoryHandler.configureScalaRepository(dev: Boolean = false)
{
    maven("${property("artifactory_contextUrl")}/gradle-${if (dev) "dev" else "release"}") {
        name = "scala"
        credentials {
            username = property("artifactory_user").toString()
            password = property("artifactory_password").toString()
        }
    }
}



