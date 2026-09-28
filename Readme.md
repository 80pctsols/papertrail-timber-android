# papertrail-timber-android

Timber tree for Papertrail logging.

Fork of [jdsingh/papertrail-timber](https://github.com/jdsingh/papertrail-timber) 1.0.3 with a configurable syslog date pattern.

### How to use

Config Papertrail with your papertrail host and port. Application class is good place for this.

#### Kotlin
```kotlin
val tree = PapertrailTree.Builder()
            .system("Android")
            .program("Papertrail")
            .logger("My-App")
            .host(BuildConfig.PAPERTRAIL_HOST)
            .port(BuildConfig.PAPERTRAIL_PORT)
            // optional, RFC3339 timestamp with year, millis and offset (default "MMM dd HH:mm:ss")
            .datePattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            // send logs to papertrail with priority Log.INFO and above
            .priority(Log.INFO)
            .build()
            
Timber.plant(tree)
```

#### Java
```java
final PapertrailTree tree = new PapertrailTree.Builder()
            .system("Android")
            .program("Papertrail")
            .logger("My-App")
            .host(BuildConfig.PAPERTRAIL_HOST)
            .port(BuildConfig.PAPERTRAIL_PORT)
            // optional, RFC3339 timestamp with year, millis and offset (default "MMM dd HH:mm:ss")
            .datePattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            // send logs to papertrail with priority Log.INFO and above
            .priority(Log.INFO)
            .build()
            
Timber.plant(tree)
```

Once this setup is done, all Timber logs will be sent to Papertrail.

The date pattern uses `java.text.SimpleDateFormat` syntax. `XXX` needs API 24+.

### Download

This fork is not published to a Maven repository. Add it to your project as a git submodule and include it as a composite build.

Step 1. Add the submodule, pinned to a release tag

```sh
git submodule add git@github.com:80pctsols/papertrail-timber-android.git external/papertrail-timber-android
git -C external/papertrail-timber-android checkout 1.1.0
```

Step 2. Include the build in `settings.gradle.kts`

```kotlin
includeBuild("external/papertrail-timber-android") {
    dependencySubstitution {
        substitute(module("com.github.jdsingh:papertrail-timber"))
            .using(project(":papertrail-timber"))
    }
}
```

Step 3. Add the dependency

```kotlin
dependencies {
    implementation("com.github.jdsingh:papertrail-timber:1.1.0")
}
```

The version is ignored, Gradle builds the library from the submodule instead.

Requirements: the Android Gradle plugin version in `gradle/libs.versions.toml` must be the same as in the including project (currently 9.4.1). The `sample` module is only included when this project is built on its own.

### Proguard

`consumer-proguard-rules.pro` is included in the library, so you don't need to include these
proguard rules separately. These are the proguard rules used for this library.

```proguard
# Papertrail
-keep class org.productivity.java.syslog4j.impl.net.tcp.ssl.SSLTCPNetSyslog
-keep class org.productivity.java.syslog4j.impl.net.tcp.ssl.SSLTCPNetSyslogWriter

-dontwarn org.productivity.java.syslog4j.impl.**
-dontwarn ch.qos.logback.core.net.*

```

Thanks
------

Thanks to @tony19 for [logback-android](https://github.com/tony19/logback-android).
