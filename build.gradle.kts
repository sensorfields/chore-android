import java.util.Properties

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.google.ksp) apply false
    alias(libs.plugins.google.hilt) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.realm) apply false
}

val version = Properties().apply { load(file("version.properties").inputStream()) }
val versionCode = version.getProperty("version.code")?.toInt() ?: error("version.code not found")
val versionName = version.getProperty("version.name") ?: error("version.name not found")
val isSnapshot = hasProperty("snapshot")

extra["application.versionCode"] = versionCode
extra["application.versionName"] = versionName + (if (isSnapshot) "-SNAPSHOT-$versionCode" else "")
