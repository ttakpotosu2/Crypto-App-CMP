plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.jetbrainsCompose)
}

android {
    namespace = "com.example.cryptoapp_cmp.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.cryptoapp_cmp.android"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)
    
    //implementation(libs.koin.bom)
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.viewmodel)
    implementation(libs.koin.compose.navigation)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp.compiler)
    // Compose dependencies
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.navigation.compose)
    // Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)
    // Coroutine Lifecycle Scopes
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.runtime.ktx)
    //Coil
    implementation(libs.coil.compose)
    //Y Charts
    implementation(libs.ycharts)
    // Shimmer
    implementation(libs.compose.shimmer)
    //Splashscreen
    implementation(libs.androidx.core.splashscreen)
}