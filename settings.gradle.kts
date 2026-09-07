@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention").version("1.0.0")
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "chore-android"
include(
    ":utils",
    ":data",
    ":data-test",
    ":domain",
    ":ui",
    ":features:dashboard:ui",
    ":features:stats:ui",
    ":features:settings:ui",
    ":features:home:ui",
    ":features:chore:ui",
    ":features:chore:create:ui",
    ":features:chore:details:ui",
    ":androidApp"
)
