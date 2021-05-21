// cassandra zstd
val pluginVersion = "0.0.2"

val cassandraVersion = "3.11.10"
val zstdVersion = "1.4.9-5"

plugins {
    java
}

tasks.wrapper {
    gradleVersion = "7.0.2"
    distributionType = Wrapper.DistributionType.ALL
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.apache.cassandra:cassandra-all:$cassandraVersion")
    implementation("com.github.luben:zstd-jni:$zstdVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    version = pluginVersion
}

tasks {
    register("copyLibs", Copy::class) {
        into("$buildDir/libs")
        from(configurations.runtimeClasspath)
    }
    get("build").dependsOn("copyLibs")
}
