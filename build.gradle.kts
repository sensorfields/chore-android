buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.android.tools.gradle)
        classpath(kotlin("gradle-plugin", version = libs.versions.kotlin.get()))
        classpath(libs.google.hilt.gradle)
        classpath(libs.google.gms.googleServices)
        classpath(libs.google.firebase.crashlyticsGradle)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
