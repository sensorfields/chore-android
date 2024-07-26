@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
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
    ":lint",
    ":utils",
    ":data:realm",
    ":data:realm:test",
    ":domain",
    ":features:dashboard:ui",
    ":features:stats:ui",
    ":features:settings:ui",
    ":features:home:ui",
    ":features:chore:ui",
    ":features:chore:create:ui",
    ":features:chore:details:ui",
    ":app"
)
