plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.metro)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    namespace = "com.sensorfields.chore.android"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "com.sensorfields.chore"
        minSdk = libs.versions.android.minSdk.get().toInt()
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
    jvmToolchain(libs.versions.jdk.get().toInt())
}

dependencies {
    implementation(projects.core)
    implementation(projects.resources)
    implementation(projects.data)
    implementation(projects.domain)
    implementation(projects.theme)
    implementation(projects.app)

    coreLibraryDesugaring(libs.android.tools.desugarJdkLibs)

    implementation(libs.androidx.core)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.compose)

    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.navigation3.runtime)
    implementation(libs.compose.navigation3.ui)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)

    implementation(libs.google.android.material)

    implementation(libs.metro.viewmodel.compose)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.messaging)
}
