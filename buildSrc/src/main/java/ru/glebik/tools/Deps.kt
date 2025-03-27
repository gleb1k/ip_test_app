package ru.glebik.tools

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.api(dep: Any) = add("api", dep)

fun DependencyHandlerScope.impl(dep: Any) = add("implementation", dep)

fun DependencyHandlerScope.debugImpl(dep: Any) = add("debugImplementation", dep)

fun DependencyHandlerScope.releaseImpl(dep: Any) = add("releaseImplementation", dep)

fun DependencyHandlerScope.kapt(dep: Any) = add("kapt", dep)

fun DependencyHandlerScope.coreDesugaring(dep: Any) = add("coreLibraryDesugaring", dep)