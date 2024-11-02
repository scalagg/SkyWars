plugins {
    id("java")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://repo.codemc.io/repository/maven-public/") }
    maven { url = uri("https://maven.citizensnpcs.co/repo") }
}


dependencies {
    compileOnly("org.projectlombok:lombok:1.18.28") // or latest version
    annotationProcessor("org.projectlombok:lombok:1.18.28")

    implementation(project(":shared"))

    compileOnly("gg.scala.basics.plugin:scala-basics-plugin:1.0.5")
    compileOnly("com.github.decentsoftware-eu:decentholograms:2.8.6")
    compileOnly("com.github.koca2000:NoteBlockAPI:1.6.1")
    compileOnly("net.citizensnpcs:citizens-main:2.0.29-SNAPSHOT") {
        exclude(group = "*", module = "*")
    }
    compileOnly("gg.scala.cgs:lobby:1.4.2")

}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
