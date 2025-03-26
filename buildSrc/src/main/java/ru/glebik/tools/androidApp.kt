package ru.glebik.tools

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

fun Project.androidApp(
    pkg: String,
    code: Int,
    version: String,
    useCompose: Boolean,
    deps: DependencyHandlerScope.() -> Unit,
    variants: List<Variant>,
    placeholders: Map<String, String> = emptyMap(),
    plugins: List<String> = emptyList(),
    configure: Project.() -> Unit = { }
) {

    apply(plugin = "com.android.application")
    apply(plugin = "org.jetbrains.kotlin.android")
    if (useCompose) {
        apply(plugin = "org.jetbrains.kotlin.plugin.compose")
    }
    plugins.forEach { id ->
        apply(plugin = id)
    }

    configure()

    configure<BaseAppModuleExtension> {
        namespace = pkg
        compileSdk = Config.compileSdk

        buildFeatures {
            buildConfig = true
            compose = useCompose
        }

        defaultConfig {
            applicationId = pkg
            minSdk = Config.minSdk
            targetSdk = Config.targetSdk

            versionCode = code
            versionName = version

            multiDexEnabled = true

            vectorDrawables { useSupportLibrary = true }

            placeholders.forEach { (key, value) ->
                manifestPlaceholders[key] = value
            }

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            variants.forEach { variant ->
                maybeCreate(variant.name).let { type ->
                    type.isMinifyEnabled = variant.isMinifyEnabled
                    type.proguardFiles.addAll(
                        listOf(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            file(variant.projectRelativeProguard)
                        ),
                    )
                }
            }
        }

        compileOptions {
            sourceCompatibility = Config.sourceCompat
            targetCompatibility = Config.targetCompat
        }

        (this as ExtensionAware).extensions.configure<KotlinJvmOptions>("kotlinOptions") {
            jvmTarget = Config.kotlinJvmTarget.target
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }

    dependencies {
        deps()
    }
}