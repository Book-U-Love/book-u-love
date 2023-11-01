import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies{
    implementation("io.jsonwebtoken:jjwt-api:0.11.5");
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5");
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5");
}

tasks.withType<BootJar> {
    enabled = false
}

tasks.withType<BootBuildImage> {
    enabled = false
}
