plugins {
    id("com.palantir.docker") version "0.35.0"
}

val axonVersion = "4.6.0"

dependencies{
    implementation(group = "org.axonframework", name = "axon-configuration", version = axonVersion)
    implementation(group = "org.axonframework", name = "axon-spring-boot-starter", version = axonVersion)
}

configure<com.palantir.gradle.docker.DockerExtension> {
    dependsOn(tasks.findByPath("build"))
    name = "${rootProject.name}-${project.name}:${version}"
    files("build/libs/${project.name}-${version}.jar")
    buildArgs(mapOf("JAR_FILE" to "${project.name}-${version}.jar"))
}