import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.compose.ui)
            implementation(libs.compose.material3)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            
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
            
            //Ktor
            api(libs.ktor.core)
            implementation(libs.ktor.json)
            implementation(libs.ktor.serialization.json)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.contentnegotiation)
            implementation(libs.ktor.client.logging)
            
            ///// KOIN /////
            implementation(project.dependencies.platform(libs.koin.bom))
            api(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.navigation)
            implementation(libs.koin.annotations)
            implementation(libs.koin.compose)
            implementation(libs.lifecycle.viewmodel)
            implementation(libs.navigation.compose)
        }
        androidMain.dependencies {
            implementation(libs.ktor.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.ios)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
    // KSP Common sourceSet
    sourceSets.named("commonMain").configure {
        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
    }
}

android {
    namespace = "com.example.cryptoapp_cmp"
    compileSdk = 34
    defaultConfig {
        minSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

// Add Koin KSP compiler dependency for code generation in KSP.
dependencies {
    add("kspCommonMainMetadata", "io.insert-koin:koin-ksp-compiler:1.3.0")
}

// Trigger Common Metadata Generation from Native tasks
project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
    if(name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}