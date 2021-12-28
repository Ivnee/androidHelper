package com.geek.infoandroid.Kotlin.modules

class DSL {
/*
1) создаем папку buildSrc
2)добавляем файл build.gradle.kts
        a)plugins {`kotlin-dsl`}
        б)repositories {
             google()
             mavenCentral()
        }
3)cоздаем папки src->main->java
4)внутри создаем котлин файл (Dependencies)
5)в апп гредле добавляем ->
                            subprojects {//это стандартный код ,который применит общие настройки для всех модулей
                                afterEvaluate { project ->
                                    if (project.plugins.findPlugin('android') ?: project.plugins.findPlugin('android-library')) {
                                        android {
                                            compileSdkVersion Config.compile_sdk

                                            buildFeatures {
                                                viewBinding true
                                            }

                                            defaultConfig {
                                                minSdkVersion Config.min_sdk
                                                targetSdkVersion Config.target_sdk
                                            }

                                            compileOptions {
                                                sourceCompatibility Config.java_version
                                                targetCompatibility Config.java_version
                                            }
                                        }
                                    }
                                }
                            }

6) создаем нужные нам модули
7) добавляем зависимости в модули ->
                                    implementation project(path: Modules.model)
                                    implementation project(path: Modules.repository)
                                    implementation project(path: Modules.utils)
8)добавляем библиотеки в модули с помощью dsl ->
                                         //Koin
                                        implementation Koin.compat
                                        implementation Koin.core
                                        implementation Koin.koin_android
9) в settings.gradle должны быть добавлены все модули(они добавляются автоматически) ->
                                        rootProject.name = "IDictionary"
                                        include ':app'
                                        include ':model'
                                        include ':repository'
                                        include ':utils'
                                        include ':historyScreen'

*/
}