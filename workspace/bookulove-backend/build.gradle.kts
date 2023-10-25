import com.epages.restdocs.apispec.gradle.OpenApi3Extension
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

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

fun Project.useSpringRESTDocs() {
    apply(plugin = "com.epages.restdocs-api-spec")

    ext {
        set("snippetsDir", file("build/generated-snippets"))
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

fun Project.useSpringWebMVC() {
    dependencies {
        val compileOnly by configurations
        val implementation by configurations
        val annotationProcessor by configurations
        val testCompileOnly by configurations
        val testImplementation by configurations
        val testAnnotationProcessor by configurations
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-validation")
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

buildscript {
    val springBootVersion = "3.1.5"

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
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    dependencies {
        val compileOnly by configurations
        val implementation by configurations
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
}
