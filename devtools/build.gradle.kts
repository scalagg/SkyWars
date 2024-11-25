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
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
