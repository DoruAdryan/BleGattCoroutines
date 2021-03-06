@file:Suppress("SpellCheckingInspection")

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    `maven-publish`
    id("com.jfrog.bintray")
}

android {
    setDefaults()
}

kotlin {
    metadataPublication(project)
    androidWithPublication(project)
    sourceSets {
        getByName("commonMain").dependencies {
            api(kotlin("stdlib-common"))
            api(Libs.kotlinX.coroutines.coreCommon)
        }
        getByName("androidMain").dependencies {
            api(Libs.kotlinX.coroutines.android)
            api(Libs.androidX.annotation)
            implementation(Libs.splitties.appctx)
            implementation(Libs.splitties.bitflags)
            implementation(Libs.splitties.checkedlazy)
            implementation(Libs.splitties.lifecycleCoroutines)
            implementation(Libs.splitties.mainthread)
        }
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlin.Experimental")
            }
        }
    }
}

afterEvaluate {
    publishing {
        setupAllPublications(project)
    }

    bintray {
        setupPublicationsUpload(project, publishing, skipMetadataPublication = true)
    }
}
