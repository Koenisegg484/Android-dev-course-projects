plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.simplynotetaker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.simplynotetaker"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.protolite.well.known.types)
    val lifecycle_version = "2.8.3"
        val room_version = "2.6.1"

//      Room
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
//      ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version")
//      LiveData
    implementation("androidx.lifecycle:lifecycle-livedata:$lifecycle_version")
    // Annotation processor
    annotationProcessor("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}