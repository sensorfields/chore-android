plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.sensorfields.chore.android.data.test"
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
    api(projects.data)
    coreLibraryDesugaring(libs.android.tools.desugarJdkLibs)
    api(libs.kotlinx.coroutines.test)
    api(libs.androidx.test.coreKtx)
    api(libs.androidx.test.ext.junitKtx)
    api(libs.junit)
    api(libs.robolectric)
    api(libs.google.truth)
}
