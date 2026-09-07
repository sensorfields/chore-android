plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.metro)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "com.sensorfields.chore.android.domain"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
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
    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.serialization.json)
}
