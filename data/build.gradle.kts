plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.hilt)
}

android {
    namespace = "com.sensorfields.chore.android.data"
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

room {
    schemaDirectory(file("schemas").toString())
}

dependencies {
    coreLibraryDesugaring(libs.android.tools.desugarJdkLibs)
    implementation(libs.google.hilt.android)
    ksp(libs.google.hilt.compiler)
    api(libs.androidx.room.runtime) // TODO api only because of test rule
    implementation(libs.androidx.roomKtx)
    ksp(libs.androidx.room.compiler)
    implementation(libs.logcat)
}
