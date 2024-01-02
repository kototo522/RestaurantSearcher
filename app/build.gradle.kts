import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.example.restaurantsearcher"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.restaurantsearcher"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // local.propertiesのデータをBuildConfigに追加
        val localPropertiesFile = File("${rootDir.path}/local.properties")
        if (localPropertiesFile.exists()) {
            val properties = Properties()
            properties.load(FileInputStream(localPropertiesFile))
            buildConfigField("String", "HOT_PEPPER_API_KEY", "\"${properties["HOT_PEPPER_API_KEY"]}\"")
        } else {
            val empty = ""
            buildConfigField("String", "HOT_PEPPER_API_KEY", "\"$empty}\"")
        }

        buildFeatures {
            buildConfig = true
        }
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            resValue("string", "GOOGLE_MAP_API_KEY", project.findProperty("GOOGLE_MAPS_API_KEY").toString())
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            resValue("string", "GOOGLE_MAP_API_KEY", project.findProperty("GOOGLE_MAPS_API_KEY").toString())
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3:1.0.1")
    // google map
    implementation("com.google.maps.android:maps-compose:2.11.4")
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    // permission
    implementation("com.google.accompanist:accompanist-permissions:0.27.1")
    // location
    implementation("com.google.android.gms:play-services-location:21.0.1")
    // GMS - Google Mobile Services
    implementation("com.google.android.gms:play-services-location:21.0.1")
    // Needed if targeting API > 31 (Android 12+)
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    // Number Picker
    implementation("com.chargemap.compose:numberpicker:1.0.3")
    // navigation
    implementation("androidx.navigation:navigation-compose:2.7.6")
    // coil
    implementation("io.coil-kt:coil-compose:2.4.0")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    implementation("com.google.code.gson:gson:2.10")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // okhttp3
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    // serialization-json
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    // room
    implementation("androidx.room:room-runtime:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
