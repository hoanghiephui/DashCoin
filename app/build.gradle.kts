import com.mathroda.buildsrc.Configuration
import com.mathroda.buildsrc.Deps
import com.mathroda.buildsrc.Version

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    //id 'org.jetbrains.kotlin.plugin.serialization'
}

android {
    compileSdk = Configuration.compileSdk
    namespace = "com.mathroda.dashcoin"

    defaultConfig {
        applicationId = Configuration.applicationId
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName

        testInstrumentationRunner = "com.mathroda.dashcoin.CustomTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
       // signingConfig signingConfigs.debug
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.compose_version
    }
    packaging {
        resources.excludes.apply {
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
            add("META-INF/gradle/incremental.annotation.processors")
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-domain"))
    implementation(project(":core-datasource"))
    implementation(project(":core-infrastructure"))
    implementation(project(":feat-common"))
    implementation(project(":feat-coin-detail"))
    implementation(project(":feat-coins"))
    implementation(project(":feat-favorite-coins"))
    implementation(project(":feat-news"))
    implementation(project(":feat-profile"))
    implementation(project(":feat-onboarding"))
    implementation(project(":feat-auth-signin"))
    implementation(project(":feat-auth-signup"))
    implementation(project(":feat-auth-forgot-password"))

    with(Deps.AndroidX.Core){
        implementation(coreKtx)
        implementation(splashScreen)
    }
    with(Deps.AndroidX.Compose) {
        implementation(ui)
        implementation(material)
        implementation(toolingPreview)
        implementation(materialIconsExtended)
        implementation(runtime)
    }
    // Coroutine Lifecycle Scopes
    with(Deps.AndroidX.Lifecycle) {
        implementation(viewModelKtx)
        implementation(runtimeKtx)
        implementation(viewModelCompose)
        implementation(runtime)
    }
    implementation(Deps.AndroidX.Activity.compose)

    // Compose dependencies
    implementation(Deps.AndroidX.Navigation.compose)
    implementation(Deps.AndroidX.ConstraintLayout.compose)

    // Accompanist insets UI
    with(Deps.Google.Accompanist) {
        implementation(insetsUi)
        implementation(flowLayout)
        implementation(navigationAnimation)
    }

    // Coroutines
    with(Deps.Org.Jetbrains.Kotlinx) {
        implementation(coroutineCore)
        implementation(coroutineAndroid)
        implementation(coroutinePlayServices)
    }

    // Dagger hilt
    with(Deps.Google.DaggerHilt) {
        implementation(android)
        kapt(compiler)
    }

    with(Deps.AndroidX.Hilt) {
        implementation(navigationCompose)
        implementation(work)
        kapt(compiler)
    }
}
