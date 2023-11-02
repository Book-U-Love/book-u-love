dependencies {
    val implementation by configurations
    implementation(project(":common"));

    // openfeign
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.0.4")
}