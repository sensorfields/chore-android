plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.metro)
    alias(libs.plugins.google.ksp)
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
            "-Xannotation-default-target=param-property",
        )
    }
}

dependencies {
    implementation(projects.utils)
    implementation(projects.ui)
    api(projects.domain)
    api(projects.features.dashboard.ui)
    implementation(projects.features.stats.ui)
    implementation(projects.features.settings.ui)

    coreLibraryDesugaring(libs.android.tools.desugarJdkLibs)
}
