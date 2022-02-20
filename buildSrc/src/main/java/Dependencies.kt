object Dependencies {

    // Plugin
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val hiltAndroidGradlePlugin =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val googleServicePlugin = "com.google.gms:google-services:4.3.10"
    const val dependenciesPlugin = "com.github.ben-manes.versions"

    // AndroidX
    const val appCompat = "androidx.appcompat:appcompat:1.4.0"

    // Compose
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeIcons = "androidx.compose.material:material-icons-core:${Versions.compose}"
    const val composeIconsExtended =
        "androidx.compose.material:material-icons-extended:${Versions.compose}"

    val composeDependencies = listOf(
        composeUi,
        composeUiTooling,
        composeFoundation,
        composeMaterial,
        composeIcons,
        composeIconsExtended,
    )

    // Compose and others
    const val composeActivity = "androidx.activity:activity-compose:1.4.0"
    const val composeNavigation = "androidx.navigation:navigation-compose:2.4.0-beta02"
    const val composeHiltNavigation = "androidx.hilt:hilt-navigation-compose:1.0.0-beta01"
    const val composeCoil = "io.coil-kt:coil-compose:1.4.0"

    val composeAndOthersDependencies = listOf(
        composeActivity,
        composeNavigation,
        composeHiltNavigation,
        composeCoil,
    )

    // Accompanist
    const val accompanist = "com.google.accompanist:accompanist-systemuicontroller:0.24.2-alpha"

    // Hilt
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltDaggerCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}" // kapt
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0" //kapt

    val hiltDependencies = listOf(
        hiltAndroid,
        hiltViewModel,
    )
    val hiltKaptDependencies = listOf(
        hiltDaggerCompiler,
        hiltCompiler,
    )

    // Coroutines
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutinesFirebase =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines}"

    val coroutinesDependencies = listOf(
        coroutinesCore,
        coroutinesAndroid,
        coroutinesFirebase
    )

    // Firebase
    const val firebaseBom = "com.google.firebase:firebase-bom:29.0.0"
    const val firebaseAuth = "com.google.firebase:firebase-auth-ktx"

    // Play Services
    const val playServicesAuth = "com.google.android.gms:play-services-auth:19.2.0"

    // Lottie
    const val lottie = "com.airbnb.android:lottie-compose:4.2.1"

    // Tests
    const val junit = "junit:junit:4.13.2"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val androidxTest = "androidx.arch.core:core-testing:2.1.0"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val turbine = "app.cash.turbine:turbine:0.7.0"

    val testDependencies = listOf(
        junit,
        coroutinesTest,
        androidxTest,
        mockk,
        turbine,
    )
}