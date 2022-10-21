package org.desperu.exominddemo.di.module.network

import org.desperu.exominddemo.data.api.OpenWeatherMapApi
import org.desperu.exominddemo.data.utils.OPEN_WEATHER_MAP_BASE_URL
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Koin module which provides dependencies related to network APIs.
 */
val apiModule = module {

    /**
     * Provides the API service for OpenWeatherMap.
     */
    single<OpenWeatherMapApi> {
        (get<Retrofit> { parametersOf(OPEN_WEATHER_MAP_BASE_URL) }).create(OpenWeatherMapApi::class.java)
    }
}