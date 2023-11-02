plugins {
    kotlin("multiplatform") version "1.9.20"
}

group = "me.devnatan"
version = "0.1.0"

repositories {
    mavenCentral()
}

kotlin {
    explicitApi()

    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.3.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("org.apache.commons:commons-compress:1.24.0")
            }
        }

//        val nativeMain by creating { dependsOn(commonMain) }

        // Android and Linux targets
//        val nixMain by creating { dependsOn(nativeMain) }
//        val linuxX64Main by getting { dependsOn(nixMain) }
//
//        // macOS and iOS targets
//        val appleMain by creating { dependsOn(nativeMain) }
//        val macosArm64Main by getting { dependsOn(appleMain) }
    }

    sourceSets.configureEach {
        val suffix = "Main"
        val srcDir = if (name.endsWith(suffix)) "src" else "test"
        val platform = name.dropLast(suffix.length)
        kotlin.srcDir("$platform/$srcDir")

        languageSettings {
            progressiveMode = true
        }
    }
}