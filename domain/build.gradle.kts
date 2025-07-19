plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.hilt)
}

android {
    namespace = "com.sensorfields.chore.android.domain"
    compileSdk = property("android.compileSdk") as Int
    defaultConfig {
        minSdk = property("android.minSdk") as Int
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

kotlin {
    jvmToolchain(17)
    explicitApi()
}

dependencies {
    implementation(projects.utils)
    implementation(projects.data)
    coreLibraryDesugaring(libs.android.tools.desugarJdkLibs)
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.google.hilt.android)
    ksp(libs.google.hilt.compiler)

    testImplementation(projects.dataTest)
}
