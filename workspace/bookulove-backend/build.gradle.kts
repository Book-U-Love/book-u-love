import com.epages.restdocs.apispec.gradle.OpenApi3Extension
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.plugin.extraProperties
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

val axonVersion = "4.8.0"
val queryDslVersion = "5.0.0"
val springCloudVersion = "2022.0.4"


fun Project.isJavaProject(): Boolean {
    return properties["lang"] == "java"
}

fun Project.includes(componentName: String): Boolean {
    val property = (properties["include"] ?: "") as String
    return property.endsWith(componentName) || property.contains("$componentName;")
}

fun Project.useSpringBoot() {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        val testImplementation by configurations
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.withType<BootBuildImage> {
        imageName.set("bookulove/${this@useSpringBoot.name}:${this@useSpringBoot.version}")
        environment.put("BP_JVM_VERSION", "17")
    }
}

fun Project.useSpringSecurity(){
    dependencies{
        val implementation by configurations
        val testImplementation by configurations
        implementation ("org.springframework.boot:spring-boot-starter-security")
        testImplementation ("org.springframework.security:spring-security-test")
    }
}

fun Project.useSpringDataJPA() {
    dependencies {
        val implementation by configurations
        val runtimeOnly by configurations
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        runtimeOnly("com.mysql:mysql-connector-j")
    }
}

fun Project.useSpringDataRedis() {
    dependencies {
        val implementation by configurations
        implementation("org.springframework.boot:spring-boot-starter-data-redis")
    }
}

fun Project.useJwt(){
    dependencies{
        val implementation by configurations
        implementation("io.jsonwebtoken:jjwt-api:0.11.5");
        implementation("io.jsonwebtoken:jjwt-impl:0.11.5");
        implementation("io.jsonwebtoken:jjwt-jackson:0.11.5");
    }
}

fun Project.useQueryDsl() {

    val queryDslVersion = "5.0.0"

    dependencies {
        val implementation by configurations
        val annotationProcessor by configurations
        implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
        annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")
        annotationProcessor("jakarta.annotation:jakarta.annotation-api")
        annotationProcessor("jakarta.persistence:jakarta.persistence-api")
    }
}

fun Project.useKafka(){
    dependencies{
        val implementation by configurations
        implementation ("org.springframework.kafka:spring-kafka")
    }
}

fun Project.useSpringRESTDocs() {
    apply(plugin = "com.epages.restdocs-api-spec")

    extraProperties {
        extra.set("snippetsDir", file("build/generated-snippets"))
    }

    dependencies {
        val testImplementation by configurations
        testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
        testImplementation("com.epages:restdocs-api-spec-mockmvc:0.19.0")
    }

    configure<OpenApi3Extension> {
        title = this@useSpringRESTDocs.name
        description = (this@useSpringRESTDocs.properties["openapi3-description"] ?: "") as String
        format = "yaml"

    }

    tasks.withType<Test> {
        outputs.dir(ext.get("snippetsDir") as File)
    }
}

fun Project.useWebSocket(){
    dependencies {
        val implementation by configurations
        implementation("org.springframework.boot:spring-boot-starter-websocket")
    }
}

fun Project.useSpringWebMVC() {
    dependencies {
        val implementation by configurations
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.0.4")
    }
}

fun Project.useSpringCloud(springCloudVersion: String) {
    extra["springCloudVersion"] = springCloudVersion

    configure<DependencyManagementExtension> {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
        }
    }

    dependencies {
        val implementation by configurations
        val testImplementation by configurations
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        implementation("org.springframework.cloud:spring-cloud-starter-gateway")
        testImplementation("io.projectreactor:reactor-test")
    }
}

fun Project.useAxon(axonVersion: String) {
    dependencies {
        val implementation by configurations
        implementation("org.axonframework:axon-configuration:${axonVersion}")
        implementation("org.axonframework:axon-spring-boot-starter:${axonVersion}")
    }
}

buildscript {
    val springBootVersion = "3.0.12"

    repositories {
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
        classpath("com.epages:restdocs-api-spec-gradle-plugin:0.19.0")
    }
}

configure(subprojects.filter { it.isJavaProject() }) {
    group = "org.bookulove"
    version = "1.0.0"
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    dependencies {
        val compileOnly by configurations
        val annotationProcessor by configurations
        val testCompileOnly by configurations
        val testImplementation by configurations
        val testAnnotationProcessor by configurations

        compileOnly("org.projectlombok:lombok:1.18.28")
        testCompileOnly("org.projectlombok:lombok:1.18.28")

        annotationProcessor("org.projectlombok:lombok:1.18.28")
        annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.28")

        testImplementation(platform("org.junit:junit-bom:5.9.1"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    if (includes("spring-boot")) {
        useSpringBoot()
    }

    if (includes("spring-data-jpa")) {
        useSpringDataJPA()
    }

    if (includes("spring-data-redis")) {
        useSpringDataRedis()
    }

    if (includes("spring-webmvc")) {
        useSpringWebMVC()
        useSpringRESTDocs()
    }

    if (includes("spring-cloud")) {
        useSpringCloud(springCloudVersion)
    }

    if (includes("axon")) {
        useAxon(axonVersion)
    }

    if(includes("jwt")){
        useJwt()
    }

    if(includes("querydsl")){
        useQueryDsl()
    }

    if(includes("spring-security")){
        useSpringSecurity()
    }

    if(includes("websocket")){
        useWebSocket()
    }

    if(includes("kafka")){
        useKafka()
    }

}
