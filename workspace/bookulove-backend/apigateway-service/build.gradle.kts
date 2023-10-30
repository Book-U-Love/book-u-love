plugins {
    id("com.palantir.docker") version "0.35.0"
}

configure<com.palantir.gradle.docker.DockerExtension> {
    dependsOn(tasks.findByPath("build"))
    name = "${rootProject.name}-${project.name}:${version}"
    setDockerfile(file("../Dockerfile"))
    files("build/libs/${project.name}-${version}.jar")
    buildArgs(mapOf("JAR_FILE" to "${project.name}-${version}.jar"))
}