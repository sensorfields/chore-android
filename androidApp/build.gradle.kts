plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.metro)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.sensorfields.chore.android"
    compileSdk = property("android.compileSdk") as Int
    defaultConfig {
        applicationId = "com.sensorfields.chore"
        minSdk = property("android.minSdk") as Int
        targetSdk = property("android.targetSdk") as Int
        versionCode = property("application.versionCode") as Int
        versionName = property("application.versionName") as String
    }
    signingConfigs {
        named("debug") {
            storeFile = file("debug.keystore")
        }
        register("devRelease") {
            storeFile = file("dev-release.jks")
            keyAlias = "upload"
            keyPassword = System.getenv("DEV_RELEASE_PASSWORD")
            storePassword = System.getenv("DEV_RELEASE_PASSWORD")
        }
    }
    flavorDimensions += "environment"
    productFlavors {
        create("dev") {
            isDefault = true
            dimension = "environment"
            applicationIdSuffix = ".dev"
        }
    }
    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs["debug"]
        }
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            productFlavors["dev"].signingConfig = signingConfigs["devRelease"]
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    buildFeatures {
        compose = true
        buildConfig = true
        resValues = true
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(projects.data)
    implementation(projects.domain)
    implementation(projects.ui)

    coreLibraryDesugaring(libs.android.tools.desugarJdkLibs)

    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.coroutines.playServices)
    implementation(libs.kotlinx.collections.immutable)

    implementation(libs.androidx.core)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.navigation)
    implementation(libs.androidx.navigation.runtime)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.datastore.preferences)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.google.android.material)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.messaging)

    implementation(libs.logcat)
    implementation(libs.coil.coil)
    implementation(libs.coil.compose)
}
