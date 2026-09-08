plugins {
    alias(libs.plugins.android.multiplatform.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.metro)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Theme"
            isStatic = true
        }
    }

    explicitApi()

    android {
        namespace = "com.sensorfields.chore.theme"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.resources)

            api(libs.compose.foundation)
            api(libs.compose.ui)
            api(libs.compose.ui.tooling.preview)
            implementation(libs.compose.material3)
        }
    }

    jvmToolchain(libs.versions.jdk.get().toInt())
}

dependencies {
    androidRuntimeClasspath(libs.compose.ui.tooling)
}
