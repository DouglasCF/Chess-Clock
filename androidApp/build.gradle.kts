plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Versions.compileSdk
    defaultConfig {
        applicationId = "br.com.fornaro.chessclock.android"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = 1
        versionName = "1.2"
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    buildTypes {
        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            applicationIdSuffix = ".DEV"
            versionNameSuffix = ".DEV"
            isDebuggable = true
        }
    }
}

dependencies {
    implementation(project(":shared"))

    // AndroidX
    implementation(Dependencies.appCompat)

    // Compose
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeIcons)
    implementation(Dependencies.composeIconsExtended)

    // Compose and others
    implementation(Dependencies.composeActivity)
    implementation(Dependencies.composeNavigation)
    implementation(Dependencies.composeHiltNavigation)

    // Accompanist
    implementation(Dependencies.accompanist)

    // Hilt
    implementation(Dependencies.hiltAndroid)
    implementation(Dependencies.hiltViewModel)
    kapt(Dependencies.hiltDaggerCompiler)
    kapt(Dependencies.hiltCompiler)
}