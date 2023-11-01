dependencies {
    val implementation by configurations
    implementation(project(":common"));
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
}