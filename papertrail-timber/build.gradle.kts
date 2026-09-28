plugins {
    id("com.android.library")
}

group = "com.github.jdsingh"

android {
    namespace = "me.jagdeep.papertrail.timber"
    compileSdk = Android.compileSdk

    defaultConfig {
        minSdk = Android.minSdk
        consumerProguardFile("consumer-proguard-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lint {
        abortOnError = true
        lintConfig = file("lint.xml")
    }
}

dependencies {
    testImplementation(Libraries.junit)
    api(Libraries.kotlin)
    api(Libraries.timber)
    api(Libraries.slf4j)
    api(Libraries.logbackAndroidCore)
    api(Libraries.logbackAndroidClassic) {
        exclude("com.google.android", "android")
    }
    api(Libraries.logbackSyslog4j) {
        exclude("ch.qos.logback")
    }
}
