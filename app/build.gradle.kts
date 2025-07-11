plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.hilt)
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
            storeFile = File(projectDir, "debug.keystore")
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
    }
}

kotlin {
    jvmToolchain(17)
}

detekt {
    buildUponDefaultConfig = true
}

dependencies {
    implementation(projects.ui)
    implementation(projects.features.home.ui)
    implementation(projects.features.chore.create.ui)
    implementation(projects.features.chore.details.ui)

    coreLibraryDesugaring(libs.android.tools.desugarJdkLibs)

    detektPlugins(libs.detekt.plugin.twitter.compose)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.playServices)
    implementation(libs.kotlinx.collections.immutable)

    implementation(libs.androidx.core.coreKtx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activityKtx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.common.java8)
    implementation(libs.androidx.lifecycle.viewmodelKtx)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.bundles.navigation)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.work.runtimeKtx)
    ksp(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.runtime.android)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.google.hilt.android)
    ksp(libs.google.hilt.compiler)
    implementation(libs.google.android.material)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlyticsKtx)
    implementation(libs.firebase.analyticsKtx)
    implementation(libs.firebase.messagingKtx)

    implementation(libs.logcat)
    implementation(libs.coil.coil)
    implementation(libs.coil.compose)
}
