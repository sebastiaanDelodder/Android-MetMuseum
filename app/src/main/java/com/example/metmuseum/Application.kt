package com.example.metmuseum

import android.app.Application
import com.example.metmuseum.data.AppContainer
import com.example.metmuseum.data.DefaultAppContainer

/**
 * Custom Application class responsible for initializing the application-wide components and dependencies.
 *
 * This class extends the Android [Application] class and initializes the [AppContainer] in its [onCreate] method.
 * The [container] property holds the instance of the application container.
 *
 * @see Application
 * @see AppContainer
 * @see DefaultAppContainer
 */
class Application : Application() {
    /**
     * The application container that manages application-wide components and dependencies.
     */
    lateinit var container: AppContainer

    /**
     * Called when the application is first created.
     *
     * Initializes the [container] by creating an instance of [DefaultAppContainer] with the application context.
     */
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)
    }
}