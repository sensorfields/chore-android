plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.hilt)
    alias(libs.plugins.detekt)
    alias(libs.plugins.realm)
}

android {
    namespace = "com.sensorfields.chore.android.data"
    compileSdk = 34
    defaultConfig {
        minSdk = 29
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
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
    implementation(libs.google.hilt.android)
    ksp(libs.google.hilt.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.roomKtx)
    ksp(libs.androidx.room.compiler)
}