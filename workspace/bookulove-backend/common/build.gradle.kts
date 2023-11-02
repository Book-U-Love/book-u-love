import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies{
    // openfeign
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.0.4")
}

tasks.withType<BootJar> {
    enabled = false
}

tasks.withType<BootBuildImage> {
    enabled = false
}
