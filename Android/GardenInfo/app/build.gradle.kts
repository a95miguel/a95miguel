import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.sun.xml.fastinfoset.sax.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.medel.vivero_v1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.medel.vivero_v1"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        //Se añadio en buildFeatures { buildConfig = true
        //Para encriptar las api
        val key: String = gradleLocalProperties(rootDir).getProperty("URL_FIREBASE") ?: ""
        buildConfigField("String", "URL_FIREBASE", "\"$key\"")

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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    
    //para utilizar Uri
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    //Get day of week api 25 or lower
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.android.gms:play-services-auth:21.0.0")
    //Depedencia de Realtime database
    implementation("com.google.firebase:firebase-database-ktx")
    //Depedencia de storage para almacenamiento de imagenes
    implementation ("com.google.firebase:firebase-storage-ktx")
    // Room
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-runtime:$roomVersion")


    // Dagger Hilt
    val hiltVersion = "2.48"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    kapt("androidx.hilt:hilt-compiler:1.1.0")

    // Retrofit
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    //Dependecia para usar constraint
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    //Depedencia para usa lottie
    implementation ("com.airbnb.android:lottie-compose:6.0.0")
    //Depedencia de Toasty = https://www.geeksforgeeks.org/custom-toast-in-android-using-jetpack-compose/
    //Se agrege en el gradle settings maven (  "https://jitpack.io" )
    implementation ("com.github.GrenderG:Toasty:1.5.2")


    // Coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.28.0")

    //Test
    val mockk_version = "1.13.4"
    testImplementation ("io.mockk:mockk:$mockk_version")
    androidTestImplementation ("androidx.work:work-testing:2.8.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")


    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

kapt {
    correctErrorTypes = true
}
