package org.desperu.exominddemo.di.module

import org.desperu.exominddemo.domain.usecases.WeatherUseCases
import org.desperu.exominddemo.domain.usecases.WeatherUseCasesImpl
import org.koin.dsl.module

/**
 * Koin module which provides Use Cases dependencies.
 */
val useCasesModule = module {

    /**
     * Provides an [WeatherUseCases] instance.
     */
    single<WeatherUseCases> {
        WeatherUseCasesImpl(
            get()
        )
    }
}
