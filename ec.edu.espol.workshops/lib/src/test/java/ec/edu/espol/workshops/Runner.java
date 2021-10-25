repositories {
    mavenCentral()
}

dependencies {
    testImplementation 'junit:junit:4.12'
    testImplementation 'io.cucumber:cucumber-java:6.8.1'
    testImplementation 'io.cucumber:cucumber-junit:6.8.1'
}

configurations {
    cucumberRuntime {
        extendsFrom testImplementation
    }
}
task cucumber() {
    dependsOn assemble, testClasses
    doLast {
        javaexec {
            main = "io.cucumber.core.cli.Main"
            classpath = configurations.cucumberRuntime + sourceSets.main.output + sourceSets.test.output
            args = ['--plugin', 'pretty','--monochrome','--plugin', 'html:build/reports/cucumber-report.html','--glue', 'ec/edu/espol/workshops', 'src/test/resources/ec/edu/espol/workshops/']
        }
    }
}
