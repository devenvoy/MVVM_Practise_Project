plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.googleGmsGoogleServices)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.interviewpractise"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.interviewpractise"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


// retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
// GSON
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")


// 1. for Live Data
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
// 2. for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")


//     Import the Bom for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
    implementation("com.google.firebase:firebase-auth")

//    // Also add the dependency for the Google Play services library and specify its version
//    implementation("com.google.android.gms:play-services-auth:20.7.0")

//  dependencies injection using hilt
    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-android-compiler:2.47")
//    implementation("androidx.activity:activity-ktx:1.9.0")

    implementation("androidx.fragment:fragment-ktx:1.7.0")

// Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")


//  coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

//  coil
    implementation("io.coil-kt:coil:2.6.0")

//    room
//    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")

    // dots
    implementation("com.tbuonomo:dotsindicator:5.0")

}
kapt {
    correctErrorTypes = true
}