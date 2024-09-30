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
    compileSdk = 34
    defaultConfig {
        minSdk = 29
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
    implementation(projects.domain)

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
    api(libs.androidx.navigation.compose)
    ksp(libs.androidx.hilt.compiler)
    api(libs.androidx.hilt.navigation.compose)
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.runtime.android)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material.icons.core)
    api(libs.androidx.compose.material.icons.extended)
    api(libs.androidx.compose.ui.tooling.preview)
    debugApi(libs.androidx.compose.ui.tooling)

    api(libs.google.hilt.android)
    ksp(libs.google.hilt.compiler)

    api(libs.logcat)
    api(libs.coil.coil)
    api(libs.coil.compose)
}
