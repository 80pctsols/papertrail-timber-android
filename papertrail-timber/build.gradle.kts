plugins {
    alias(libs.plugins.android.library)
}

group = "com.github.jdsingh"

android {
    namespace = "me.jagdeep.papertrail.timber"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
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
    testImplementation(libs.junit)
    api(libs.timber)
    api(libs.slf4j.api)
    api(libs.logback.android.core)
    api(libs.logback.android.classic) {
        exclude("com.google.android", "android")
    }
    api(libs.logback.syslog4j) {
        exclude("ch.qos.logback")
    }
}
