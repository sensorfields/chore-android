plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.metro)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "com.sensorfields.chore.android.data"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
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
    api(libs.androidx.room.runtime) // TODO api only because of test rule
    ksp(libs.androidx.room.compiler)
    implementation(libs.logcat)
}
