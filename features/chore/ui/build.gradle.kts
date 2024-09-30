plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.hilt)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.sensorfields.chore.android.ui.chore"
    compileSdk = 34
    defaultConfig {
        minSdk = 29
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

kotlin {
    jvmToolchain(17)
    explicitApi()
}

detekt {
    buildUponDefaultConfig = true
}

dependencies {
    implementation(projects.utils)
    api(projects.domain)

    coreLibraryDesugaring(libs.android.tools.desugarJdkLibs)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.google.hilt.android)
    ksp(libs.google.hilt.compiler)

    implementation(libs.logcat)
}
