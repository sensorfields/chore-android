plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.hilt)
}

android {
    namespace = "com.sensorfields.chore.android.ui.home"
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
    compilerOptions {
        freeCompilerArgs.addAll(
            "-XXLanguage:+PropertyParamAnnotationDefaultTargetMode",
        )
    }
}

dependencies {
    implementation(projects.utils)
    implementation(projects.ui)
    api(projects.domain)
    implementation(projects.features.dashboard.ui)
    implementation(projects.features.stats.ui)
    implementation(projects.features.settings.ui)

    implementation(libs.bundles.navigation)

    coreLibraryDesugaring(libs.android.tools.desugarJdkLibs)

    implementation(libs.google.hilt.android)
    ksp(libs.androidx.hilt.compiler)
    ksp(libs.google.hilt.compiler)
}
