plugins {
    id("java")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":shared"))
    compileOnly("studio.pinkcloud.deploy:spigot:1.0-SNAPSHOT")
    compileOnly(files("libs/worldedit-1.8.8.jar"))
    compileOnly(files("libs/FastAsyncWorldEdit.jar"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.register("copyJar") {
    dependsOn("build")
    doLast {
        val jarFile = file("build/libs/${project.name}-${project.version}.jar")
        val targetDir = file("../target")
        targetDir.mkdirs()
        jarFile.copyTo(file("${targetDir}/skywars-${project.name}-${project.version}.jar"), overwrite = true)
    }
}
