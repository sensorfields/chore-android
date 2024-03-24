plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.sensorfields.chore.android.data.realm.test"
    compileSdk = 34
    defaultConfig {
        minSdk = 29
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
    api(projects.data.realm)
    coreLibraryDesugaring(libs.android.tools.desugarJdkLibs)
    api(libs.junit)
}
