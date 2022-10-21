package org.desperu.exominddemo.di.module

import org.desperu.exominddemo.data.repositories.OpenWeatherMapRepository
import org.desperu.exominddemo.data.repositories.OpenWeatherMapRepositoryImpl
import org.koin.dsl.module

/**
 * Koin module which provide dependencies related to repositories.
 */
val repositoryModule = module {

    /**
     * Provides an [OpenWeatherMapRepository] instance.
     */
    single<OpenWeatherMapRepository> {
        OpenWeatherMapRepositoryImpl(
            get()
        )
    }
}
