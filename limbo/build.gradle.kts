java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.refinedev.xyz/repository/maven-releases/") }
    maven { url = uri("https://maven.refinedev.xyz/repository/public-repo/") }
    maven { url = uri("https://repo.mincats.eu/mirrors") }
    maven { url = uri("https://repo.purpurmc.org/snapshots") }
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://repo.codemc.io/repository/maven-public/") }
}

dependencies {
    implementation project (":shared")

    compileOnly("net.kyori:adventure-api:4.14.0")
    compileOnly("org.projectlombok:lombok:1.18.24")
    compileOnly("xyz.refinedev.phoenix:bukkit:1.0")
    compileOnly("xyz.refinedev.retention:loader-api:1.0")
    compileOnly("org.paperspigot:PaperSpigot:1.8.8")
    compileOnly("xyz.refinedev:pxCosmetics:1.0")
    compileOnly("xyz.refinedev:pxLevels:1.0")
    compileOnly("studio.pinkcloud.deploy:spigot:1.0-SNAPSHOT")


}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
