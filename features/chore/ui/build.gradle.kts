plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sensorfields.chore.android.ui.chore"
    compileSdk = property("android.compileSdk") as Int
    defaultConfig {
        minSdk = property("android.minSdk") as Int
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    buildFeatures {
        compose = true
    }
}

kotlin {
    jvmToolchain(17)
    explicitApi()
}

dependencies {
    implementation(projects.utils)
    implementation(projects.ui)
    api(projects.domain)
    coreLibraryDesugaring(libs.android.tools.desugarJdkLibs)
}
