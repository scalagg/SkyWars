plugins {
    id("java")
    id("maven-publish")
    kotlin("jvm") version "1.9.10"
    kotlin("kapt") version "1.9.10"
    id("com.jfrog.artifactory") version "4.29.2"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.ajoberstar.grgit") version "4.1.1"
}


version = "1.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

var currentBranch: String = grgit.branch.current().name

allprojects {
    apply(plugin = "maven-publish")
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "com.jfrog.artifactory")

    group = "gg.scala.skywars"
    version = "1.1.0${
        if (currentBranch == "develop") "-dev" else ""
    }"

    repositories {
        mavenLocal()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven {
            name = "lunarclient"
            url = uri("https://repo.lunarclient.dev")
        }

        maven {
            url = uri("${property("artifactory_contextUrl")}/gradle-release")

            credentials {
                username = property("artifactory_user").toString()
                password = property("artifactory_password").toString()
            }
        }
        maven {
            url = uri("https://repo.mincats.eu/mirrors")
            name = "mincats-mirrors"
        }
        maven {
            url = uri("https://repo.purpurmc.org/snapshots")
            name = "purpur"
        }
        maven {
            url = uri("https://jitpack.io")
            name = "jitpack"
        }
        maven {
            url = uri("https://repo.codemc.io/repository/maven-public/")
            name = "codemc-repo"
        }
        maven("https://repo.glaremasters.me/repository/concuncan/")

    }

    dependencies {
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

    tasks.shadowJar {
        archiveClassifier.set("")

        exclude("**/*.kotlin_metadata")
        exclude("**/*.kotlin_module")

        archiveFileName.set("skywars-${project.name}.jar")
    }

    publishing {
        publications {
            create<MavenPublication>("shadow") {
                project.shadow.component(this)
            }
        }

        repositories {
            maven {
                name = "scala"
                url = uri("${property("artifactory_contextUrl")}/gradle-release")

                credentials {
                    username = property("artifactory_user") as String
                    password = property("artifactory_password") as String
                }
            }
        }
    }

    tasks.build {
        dependsOn(
            tasks.shadowJar,
            //tasks.named("publishShadowPublicationToScalaRepository")
        )
    }

}




