package org.desperu.exominddemo.data.repositories

import org.desperu.exominddemo.data.api.OpenWeatherMapApi
import org.desperu.exominddemo.data.models.OpenWeatherMapResponse
import org.desperu.exominddemo.helper.FetchHelper.catchFetch
import org.koin.core.component.KoinComponent

/**
 * Repository interface to get OpenWeather Map data from API.
 *
 * @author Desperu
 */
interface OpenWeatherMapRepository {

    /**
     * Returns the weather for the given city from the API of OpenWeatherMap.
     *
     * @param city  the city for which request the weather.
     *
     * @return the weather for the given city from the API of OpenWeatherMap.
     */
    suspend fun fetchWeather(city: String): OpenWeatherMapResponse?
}

/**
 * Implementation of the [OpenWeatherMapRepository] interface.
 *
 * @author Desperu
 *
 * @property openWeatherMapApi      the service to request the OpenWeatherMap API.
 *
 * @constructor Instantiates a new [OpenWeatherMapRepositoryImpl].
 *
 * @param openWeatherMapApi         the service to request the OpenWeatherMap API to set.
 */
class OpenWeatherMapRepositoryImpl(
    private val openWeatherMapApi: OpenWeatherMapApi
): OpenWeatherMapRepository, KoinComponent {

    /**
     * {@inheritDoc}
     */
    override suspend fun fetchWeather(city: String): OpenWeatherMapResponse? = catchFetch {
        openWeatherMapApi.getWeather(city)
    }
}
