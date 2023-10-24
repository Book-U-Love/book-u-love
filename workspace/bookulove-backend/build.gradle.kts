import com.epages.restdocs.apispec.gradle.OpenApi3Extension

fun Project.isJavaProject(): Boolean {
    return properties["lang"] == "java"
}

fun Project.includes(componentName: String): Boolean {
    val property = (properties["include"] ?: "") as String
    return property.endsWith(componentName) || property.contains("$componentName;")
}

buildscript {
    val springBootVersion = "3.1.3"

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

    if (includes("spring-webmvc")) {
        apply(plugin = "org.springframework.boot")
        apply(plugin = "io.spring.dependency-management")
        apply(plugin = "com.epages.restdocs-api-spec")

        ext {
            set("snippetsDir", file("build/generated-snippets"))
        }

        val currentProject = this
        configure<OpenApi3Extension> {
            title = currentProject.name
            description = (currentProject.properties["openapi3-description"] ?: "") as String
            format = "yaml"
        }
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

        if (includes("spring-webmvc")) {
            implementation("org.springframework.boot:spring-boot-starter-web")
            implementation("org.springframework.boot:spring-boot-starter-validation")
            testImplementation("org.springframework.boot:spring-boot-starter-test")
            testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
            testImplementation("com.epages:restdocs-api-spec-mockmvc:0.19.0")
            testImplementation(platform("org.junit:junit-bom:5.9.1"))
            testImplementation("org.junit.jupiter:junit-jupiter")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
