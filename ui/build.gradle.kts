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

detekt {
    buildUponDefaultConfig = true
}

dependencies {
    coreLibraryDesugaring(libs.android.tools.desugarJdkLibs)

    detektPlugins(libs.detekt.plugin.twitter.compose)

    api(libs.kotlin.stdlib)
    api(libs.kotlinx.coroutines.android)
    api(libs.kotlinx.collections.immutable)
    api(libs.kotlinx.serialization.json)

    api(libs.androidx.lifecycle.common.java8)
    api(libs.androidx.lifecycle.viewmodelKtx)
    api(libs.androidx.lifecycle.viewmodel.savedstate)
    api(libs.androidx.lifecycle.viewmodel.compose)
    api(libs.androidx.lifecycle.runtime.compose)
    ksp(libs.androidx.hilt.compiler)
    api(libs.androidx.hilt.viewmodel.compose)
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.runtime.android)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)
    api(libs.androidx.compose.ui.tooling.preview)
    debugApi(libs.androidx.compose.ui.tooling)

    api(libs.google.hilt.android)
    ksp(libs.google.hilt.compiler)

    api(libs.logcat)
    api(libs.coil.coil)
    api(libs.coil.compose)
}
