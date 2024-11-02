plugins {
    id("java")
    id("maven-publish")
    kotlin("jvm") version "1.9.10"
    kotlin("kapt") version "1.9.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.ajoberstar.grgit") version "4.1.1"
    id("io.freefair.lombok") version "8.0.1" // make sure to use the latest version
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
    apply(plugin = "io.freefair.lombok")

    group = "gg.pixelarcade"
    version = "1.0.9${
        if (currentBranch == "develop") "-dev" else ""
    }"

    repositories {
        mavenLocal()
        mavenCentral()
        maven(url = "https://jitpack.io")
        configureScalaRepository()
        maven {
            url = uri("https://maven.refinedev.xyz/repository/maven-releases/")
            name = "refine-releases"
        }
        maven {
            url = uri("https://maven.refinedev.xyz/repository/public-repo/")
            name = "refine-public"
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
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.34")
        annotationProcessor("org.projectlombok:lombok:1.18.34")

        compileOnly("net.kyori:adventure-api:4.14.0")
        compileOnly("org.paperspigot:PaperSpigot:1.8.8")

        compileOnly("gg.scala.commons:bukkit:4.0.0")
        kapt("gg.scala.commons:bukkit:4.0.0")

        compileOnly("gg.scala.lemon:bukkit:1.7.0")
        compileOnly("gg.scala.cgs:common:1.4.2")


        compileOnly("gg.scala.store:spigot:0.1.9")
        compileOnly("gg.scala.store:shared:0.1.9")

        compileOnly("gg.scala.cloudsync:spigot:1.0.3")
    }
}




tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-processorpath")
    options.annotationProcessorPath = configurations.annotationProcessor.get()
}

tasks.register<Jar>("shade") {
    dependsOn("jar")
    archiveClassifier.set("")
    from(sourceSets.main.get().output)
    doLast {
        println("Shading dependencies complete.")
    }
}

// Additional Git properties plugin configuration
tasks.register("generateGitProperties") {
    doLast {
        println("Generating git.properties file.")
    }
}
tasks.getByName("build")
    .dependsOn(
        "shadowJar",
        "publishMavenJavaPublicationToScalaRepository"
    )

fun RepositoryHandler.configureScalaRepository(dev: Boolean = false) {
    maven("${property("artifactory_contextUrl")}/gradle-${if (dev) "dev" else "release"}") {
        name = "scala"
        credentials {
            username = property("artifactory_user").toString()
            password = property("artifactory_password").toString()
        }
    }
}