// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.0.2" apply false
    id("com.android.library") version "8.0.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.21" apply false
    id("com.google.dagger.hilt.android") version "2.44.2" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.1.0" apply true
    id("io.gitlab.arturbosch.detekt") version "1.17.0" apply true
    id("com.github.ben-manes.versions") version "0.42.0" apply true
}

apply(from = "buildscripts/githooks.gradle")
apply(from = "buildscripts/setup.gradle")

subprojects {
    apply(from = "../buildscripts/ktlint.gradle")
    apply(from = "../buildscripts/detekt.gradle")
    apply(from = "../buildscripts/versionsplugin.gradle")
}

task("clean") {
    delete(rootProject.buildDir)
}

afterEvaluate {
    // We install the hook at the first occasion
    tasks["clean"].dependsOn("installGitHooks")
}
