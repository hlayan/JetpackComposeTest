plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

val env: MutableMap<String, String> = System.getenv()

android {

    val sdkVersion = 33

    compileSdk = sdkVersion
    namespace = "com.hlayan.forexrate"

    defaultConfig {
        minSdk = 21
        targetSdk = sdkVersion
        versionCode = 1
        versionName = "1.0.0"
        applicationId = "com.hlayan.forexrate"
        vectorDrawables.useSupportLibrary = true
    }

    val releaseSigningConfig = signingConfigs.create("release") {
        storeFile = File(env["ReleaseStoreFile"]!!)
        storePassword = env["ReleaseStorePassword"]
        keyAlias = env["ReleaseKeyAlias"]
        keyPassword = env["ReleaseKeyPassword"]
    }

    buildTypes {
        debug {
            versionNameSuffix = "-debug"
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = releaseSigningConfig
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2022.11.00"))

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material-icons-extended")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.core:core-splashscreen:1.0.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

//    implementation("androidx.compose.material3:material3:1.0.0-alpha14")
//    implementation("androidx.navigation:navigation-compose:2.5.2")
//    implementation("com.google.accompanist:accompanist-pager:0.19.0")

    implementation("com.google.android.material:material:1.7.0")
    implementation("com.google.code.gson:gson:2.10")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    implementation("com.github.skydoves:sandwich:1.3.2")
    implementation("com.jakewharton.timber:timber:5.0.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("com.google.dagger:hilt-android:2.44.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.44.2")

    debugImplementation("androidx.compose.ui:ui-tooling")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.2.2")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}