plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.metro)
    alias(libs.plugins.google.ksp)
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
    api(libs.androidx.room.runtime) // TODO api only because of test rule
    ksp(libs.androidx.room.compiler)
    implementation(libs.logcat)
}
