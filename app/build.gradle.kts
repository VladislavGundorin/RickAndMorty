plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.rickandmorty"
    compileSdk = 34

    viewBinding {
            enable = true
    }

    defaultConfig {
        applicationId = "com.example.rickandmorty"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("io.coil-kt:coil-compose:2.1.0")
}
dependencies {
    val fragmentVersion = "1.8.3"

    implementation("androidx.fragment:fragment:$fragmentVersion")

    implementation("androidx.fragment:fragment-ktx:$fragmentVersion")
}
dependencies {
    val nav_version = "2.8.1"
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")
}
