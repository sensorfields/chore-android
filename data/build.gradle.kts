plugins {
    alias(libs.plugins.android.multiplatform.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.metro)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.google.ksp)
}

room {
    schemaDirectory("$projectDir/schemas")
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Data"
            isStatic = true
        }
    }

    explicitApi()

    android {
        namespace = "com.sensorfields.chore.data"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)

            implementation(libs.androidx.datastore.preferences)

            api(libs.androidx.room.runtime) // TODO implementation
            implementation(libs.androidx.sqlite.bundled)

            implementation(libs.ktor.client.core)

            api(libs.supabase.postgrest) // TODO implementationo
            api(libs.supabase.auth)
            api(libs.supabase.realtime)
            api(libs.supabase.functions)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }

    jvmToolchain(libs.versions.jdk.get().toInt())

    compilerOptions {
        freeCompilerArgs.addAll(
            "-Xexpect-actual-classes",
        )
    }
}

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
}
