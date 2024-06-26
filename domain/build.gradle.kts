plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.hilt)
    alias(libs.plugins.detekt)
    alias(libs.plugins.realm)
}

android {
    namespace = "com.sensorfields.chore.android.domain"
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
    implementation(projects.data.realm)
    coreLibraryDesugaring(libs.android.tools.desugarJdkLibs)
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.google.hilt.android)
    ksp(libs.google.hilt.compiler)

    testImplementation(projects.data.realm.test)
    testImplementation(libs.junit)
    testImplementation(libs.google.truth)
    testImplementation(libs.kotlinx.coroutines.test)
}
