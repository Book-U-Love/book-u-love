version = "1.0.0"

val queryDslVersion = "5.0.0"

dependencies{
    val implementation by configurations
    implementation(project(":common"));

    // openfeign
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.0.4")

    // querydsl
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:${queryDslVersion}")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")

    // security
    implementation ("org.springframework.boot:spring-boot-starter-security")
    testImplementation ("org.springframework.security:spring-security-test")
}