plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        name = "DevFest"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("ios/Podfile")
        framework {
            baseName = "DevFest"
            isStatic = true

            export(libs.decompose.router)
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            api(libs.common.koin.android)
        }
        commonMain.dependencies {
            // Coroutines
            implementation(libs.coroutines.core)

            // Compose
            implementation(compose.ui)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)

            // Decompose
            api(libs.decompose.router)
            implementation(libs.decompose)
            implementation(libs.decompose.compose.multiplatform)

            // Utilities
            implementation(libs.qdsfdhvh.image.loader)
            implementation(libs.common.koin)
            implementation(libs.common.parcelable)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "app.isfa.devfest"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}