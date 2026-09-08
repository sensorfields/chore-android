plugins {
    alias(libs.plugins.android.multiplatform.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.metro)
    alias(libs.plugins.skie)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "App"
            isStatic = true
        }
    }

    explicitApi()

    android {
        namespace = "com.sensorfields.chore.app"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.resources)
            implementation(projects.data)
            implementation(projects.domain)

            api(libs.androidx.lifecycle.viewmodel)

            implementation(libs.metro.viewmodel)
        }
    }

    jvmToolchain(libs.versions.jdk.get().toInt())
}
