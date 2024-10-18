plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.hilt)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.sensorfields.chore.android.ui.chore.create"
    compileSdk = 34
    defaultConfig {
        minSdk = 29
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

detekt {
    buildUponDefaultConfig = true
}

dependencies {
    implementation(projects.utils)
    implementation(projects.ui)
    api(projects.domain)
    implementation(projects.features.chore.ui)

    coreLibraryDesugaring(libs.android.tools.desugarJdkLibs)

    detektPlugins(libs.detekt.plugin.twitter.compose)

    implementation(libs.google.hilt.android)
    ksp(libs.androidx.hilt.compiler)
    ksp(libs.google.hilt.compiler)

    androidTestApi(libs.androidx.test.runner)
    androidTestApi(libs.androidx.compose.ui.test.junit4)
    debugApi(libs.androidx.compose.ui.test.manifest)
    androidTestApi(libs.google.hilt.testing)
    kspAndroidTest(libs.google.hilt.compiler)
}
