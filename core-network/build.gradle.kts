plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.powakaz.core_network"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }
    buildFeatures {
        buildConfig = true

    }
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.coroutines.core)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}