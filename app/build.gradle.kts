import org.gradle.kotlin.dsl.implementation
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.*

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.dagger.hilt.androidid)
    kotlin("kapt") version "2.2.21"
}

android {
    namespace = "com.example.MyProjectInAndroid"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.genderrhisics"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        tasks.withType<KotlinCompile> {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_11)
            }
        }
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }

    packaging {
        resources.excludes += "META-INF/gradle/incremental.annotation.processors"
    }

    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions.freeCompilerArgs.addAll(listOf("-Xlint:deprecation"))
    }
}
dependencies {
    implementation("com.google.dagger:hilt-android:2.57.2")
    kapt("com.google.dagger:hilt-compiler:2.56.2")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation(libs.androidx.cardview)
    kapt ("com.github.bumptech.glide:compiler:4.12.0")
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.google.firebase:firebase-database-ktx:21.0.0")
    implementation("com.firebaseui:firebase-ui-auth:9.1.1")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.firebase.boom))
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.firebase.database)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
kapt {
    correctErrorTypes = true
    useBuildCache = false
}