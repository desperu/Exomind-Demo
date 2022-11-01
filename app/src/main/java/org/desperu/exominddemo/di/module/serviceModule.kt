package org.desperu.exominddemo.di.module

import org.desperu.exominddemo.services.ResourceService
import org.desperu.exominddemo.services.ResourceServiceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Koin module which provide dependencies related to services.
 */
val serviceModule = module {

    /**
     * Provides a ResourceService instance.
     */
    single<ResourceService> {
        ResourceServiceImpl(
            androidContext()
        )
    }
}
