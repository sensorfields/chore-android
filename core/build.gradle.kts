plugins {
    alias(libs.plugins.android.multiplatform.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Core"
            isStatic = true
        }
    }

    explicitApi()

    android {
        namespace = "com.sensorfields.chore.core"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.coroutines)
            api(libs.kotlinx.serialization.json)
            api(libs.kotlinx.collections.immutable)
            api(libs.kotlinx.datetime)

            api(libs.compose.runtime)
        }
    }

    jvmToolchain(libs.versions.jdk.get().toInt())
}
