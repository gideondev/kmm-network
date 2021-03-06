plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = "1.0"

object Dependencies {
    object Version {
        const val ktorVersion = "2.0.1"
        const val logbackVersion = "1.2.11"
    }

    object Ktor {
        const val core = "io.ktor:ktor-client-core:${Version.ktorVersion}"
        const val cio = "io.ktor:ktor-client-cio:${Version.ktorVersion}"
        const val serialization = "io.ktor:ktor-client-serialization:${Version.ktorVersion}"
        const val contentNegotiation =
            "io.ktor:ktor-client-content-negotiation:${Version.ktorVersion}"

        // For Android
        const val okhttp = "io.ktor:ktor-client-okhttp:${Version.ktorVersion}"

        // For iOS
        const val ios = "io.ktor:ktor-client-ios:${Version.ktorVersion}"

        // Logging
        const val logback = "ch.qos.logback:logback-classic:${Version.logbackVersion}"
        const val ktorClientLogging = "io.ktor:ktor-client-logging:${Version.ktorVersion}"
    }
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "network"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.Ktor.core)
                implementation(Dependencies.Ktor.cio)
                implementation(Dependencies.Ktor.serialization)
                implementation(Dependencies.Ktor.contentNegotiation)
                implementation(Dependencies.Ktor.logback)
                implementation(Dependencies.Ktor.ktorClientLogging)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 32
    }
}