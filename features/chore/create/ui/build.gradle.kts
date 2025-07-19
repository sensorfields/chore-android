plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.hilt)
}

android {
    namespace = "com.sensorfields.chore.android.ui.chore.create"
    compileSdk = property("android.compileSdk") as Int
    defaultConfig {
        minSdk = property("android.minSdk") as Int
        testInstrumentationRunner =
            "com.sensorfields.chore.android.ui.chore.create.AndroidTestRunner"
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
    implementation(projects.features.chore.ui)

    coreLibraryDesugaring(libs.android.tools.desugarJdkLibs)

    implementation(libs.google.hilt.android)
    ksp(libs.androidx.hilt.compiler)
    ksp(libs.google.hilt.compiler)

    androidTestApi(libs.androidx.test.runner)
    androidTestApi(libs.androidx.compose.ui.test.junit4)
    debugApi(libs.androidx.compose.ui.test.manifest)
    androidTestApi(libs.google.hilt.testing)
    kspAndroidTest(libs.google.hilt.compiler)
}
