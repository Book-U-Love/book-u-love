plugins {
    id("com.palantir.docker") version "0.35.0"
}

val axonVersion = "4.6.0"

dependencies{
    implementation("io.jsonwebtoken:jjwt-api:0.11.5");
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5");
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5");
    implementation(group = "org.axonframework", name = "axon-configuration", version = axonVersion)
    implementation(group = "org.axonframework", name = "axon-spring-boot-starter", version = axonVersion)
}

configure<com.palantir.gradle.docker.DockerExtension> {
    dependsOn(tasks.findByPath("build"))
    name = "${rootProject.name}-${project.name}:${version}"
    setDockerfile(file("../Dockerfile"))
    buildArgs(mapOf("JAR_FILE" to "${project.name}-${version}.jar"))
}