package org.desperu.exominddemo

import android.app.Application
import org.desperu.exominddemo.di.module.network.*
import org.desperu.exominddemo.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ExomindDemo : Application() {

    /**
     * Initializes the application, by adding strict mode and starting koin.
     */
    override fun onCreate() {
        super.onCreate()
        if (GlobalContext.getOrNull() == null) { // For Robolectric in unit test.
            startKoin {
                androidLogger(Level.ERROR)
                androidContext(this@ExomindDemo)
                modules(
                    listOf(
                        networkModule,
                        apiModule,
                        repositoryModule,
                        useCasesModule,
                        viewModelModule
                    )
                )
            }
        }
    }
}