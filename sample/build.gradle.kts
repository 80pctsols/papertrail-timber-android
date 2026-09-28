plugins {
    id("com.android.application")
}

android {
    namespace = "me.jagdeep.papertrailtimber"
    compileSdk = Android.compileSdk

    defaultConfig {
        applicationId = "me.jagdeep.papertrailtimber"
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "PAPERTRAIL_HOST", "\"logsX.papertrailapp.com\"")
        buildConfigField("int", "PAPERTRAIL_PORT", "30123")
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lint {
        abortOnError = false
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    testImplementation(Libraries.junit)
    implementation(Libraries.kotlin)
    implementation(Libraries.appCompact)

    implementation(project(":papertrail-timber"))
}
