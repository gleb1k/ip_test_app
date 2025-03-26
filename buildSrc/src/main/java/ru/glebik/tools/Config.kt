package ru.glebik.tools

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Config {
    const val compileSdk = 35
    const val minSdk = 23
    const val targetSdk = 35

    val sourceCompat = JavaVersion.VERSION_17
    val targetCompat = JavaVersion.VERSION_17

    val kotlinJvmTarget = JvmTarget.JVM_17
}