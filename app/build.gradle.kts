plugins {
    id("java")
    //id("application")
    id ("checkstyle")
    id("org.sonarqube") version "6.2.0.5505"
    id("jacoco")
}

sonar {
    properties {
        property("sonar.projectKey", "TelAndr_java-project-78")
        property("sonar.organization", "telandr1987")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

sonarqube {
    properties {
        property ("sonar.projectKey", "TelAndr_java-project-78")
        property ("sonar.host.url", "https://sonarcloud.io")
        property ("sonar.login", "${System.getenv("SONAR_TOKEN")}") // Используйте переменную окружения для вашего токена
        property ("sonar.coverage.jacoco.xmlReportPaths", file("build/reports/jacoco/test/jacocoTestReport.xml"))
    }
}

jacoco {    toolVersion = "0.8.11"}
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }
}

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.test)
    violationRules {
        rule {
            limit {
                counter = "LINE"
                value = "COVERED_RATIO"
                minimum = 0.80.toBigDecimal()
            }
        }
    }
}
tasks.check {
    dependsOn(tasks.jacocoTestCoverageVerification)
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

//application {
    // Входная точка
//    mainClass.set("hexlet.code.App")
//}

dependencies {
    implementation("org.projectlombok:lombok:1.18.26")
    implementation("org.testng:testng:7.1.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}