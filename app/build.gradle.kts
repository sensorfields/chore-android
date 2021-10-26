@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization") version libs.versions.kotlin
    kotlin("plugin.parcelize")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    alias(libs.plugins.detekt)
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.sensorfields.chore"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0.0${property("appVersionNameSuffix")}"
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
                arg("room.incremental", "true")
            }
        }
    }
    signingConfigs {
        maybeCreate("debug").apply {
            storeFile = File(projectDir, "debug.keystore")
        }
    }
    buildTypes {
        maybeCreate("debug").apply {
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs["debug"]
        }
    }
    buildFeatures {
        compose = true
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf(
            "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi",
            "-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-Xopt-in=coil.annotation.ExperimentalCoilApi"
        )
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.get()
    }
}

detekt {
    buildUponDefaultConfig = true
}

dependencies {
    coreLibraryDesugaring(libs.android.tools.desugarJdkLibs)

    implementation(kotlin("stdlib"))
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.playServices)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.annotation.annotation)
    implementation(libs.androidx.core.coreKtx)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.uiTooling)

    implementation(libs.androidx.paging.commonKtx)
    implementation(libs.androidx.paging.runtimeKtx)
    implementation(libs.androidx.paging.compose)

    implementation(libs.androidx.room.roomKtx)
    kapt(libs.androidx.room.compiler)

    implementation(libs.google.accompanist.insets)
    implementation(libs.google.accompanist.insetsUi)
    implementation(libs.google.accompanist.systemuicontroller)

    implementation(libs.google.firebase.crashlyticsKtx)

    implementation(libs.google.hilt.android)
    kapt(libs.google.hilt.compiler)

    implementation(libs.squareup.okhttp.okhttp)
    implementation(libs.squareup.okhttp.logginInterceptor)
    implementation(libs.squareup.retrofit.retrofit)
    implementation(libs.jakewharton.retrofit.kotlinxSerializationConverter)

    implementation(libs.coil.coil)
    implementation(libs.coil.compose)

    implementation(libs.jakewharton.timber.timber)
}
