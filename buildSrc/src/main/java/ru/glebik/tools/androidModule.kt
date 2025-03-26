package ru.glebik.tools

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

fun Project.androidModule(
    pkg: String,
    useCompose: Boolean,
    plugins: List<String> = emptyList(),
    deps: DependencyHandlerScope.() -> Unit,
) {
    apply(plugin = "com.android.library")
    apply(plugin = "org.jetbrains.kotlin.android")
    if (useCompose) {
        apply(plugin = "org.jetbrains.kotlin.plugin.compose")
    }
    plugins.forEach { id ->
        apply(plugin = id)
    }

    configure<LibraryExtension> {

        namespace = pkg
        compileSdk = Config.compileSdk

        buildFeatures {
            buildConfig = true
            compose = useCompose
        }

        defaultConfig {
            minSdk = Config.minSdk

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }


        compileOptions {
            sourceCompatibility = Config.sourceCompat
            targetCompatibility = Config.targetCompat
        }

        (this as ExtensionAware).extensions.configure<KotlinJvmOptions>("kotlinOptions") {
            jvmTarget = Config.kotlinJvmTarget.target
        }
    }
    dependencies {
        deps()
    }
}