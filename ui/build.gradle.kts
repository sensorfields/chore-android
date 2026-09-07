plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.metro)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "com.sensorfields.chore.android.ui"
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
    coreLibraryDesugaring(libs.android.tools.desugarJdkLibs)

    api(libs.kotlinx.coroutines)
    api(libs.kotlinx.collections.immutable)
    api(libs.kotlinx.serialization.json)

    api(libs.androidx.lifecycle.runtime.compose)
    api(libs.androidx.lifecycle.viewmodel.navigation)
    api(libs.androidx.navigation.runtime)
    api(libs.androidx.navigation.ui)
    api(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)
    api(libs.androidx.compose.ui.tooling.preview)
    debugApi(libs.androidx.compose.ui.tooling)

    api(libs.metro.viewmodel.compose)

    api(libs.logcat)
    api(libs.coil.coil)
    api(libs.coil.compose)
}
