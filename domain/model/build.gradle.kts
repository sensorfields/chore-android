plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.detekt)
}

kotlin {
    jvmToolchain(17)
    explicitApi()
}

detekt {
    buildUponDefaultConfig = true
}
