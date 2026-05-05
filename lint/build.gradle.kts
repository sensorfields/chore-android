plugins {
    alias(libs.plugins.kotlin.jvm)
}

tasks.jar {
    manifest {
        attributes["Lint-Registry-v2"] = "com.sensorfields.chore.android.lint.LintIssueRegistry"
    }
}

kotlin {
    jvmToolchain(17)
    explicitApi()
}

dependencies {
    compileOnly(libs.android.tools.lint.api)
    compileOnly(libs.kotlin.stdlib)
}
