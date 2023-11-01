version = "1.0.0"

val queryDslVersion = "5.0.0"

dependencies{
    val implementation by configurations
    implementation(project(":common"));
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.0.4")
    annotationProcessor("com.querydsl:querydsl-apt:${queryDslVersion}")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")
}