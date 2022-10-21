package org.desperu.exominddemo.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.desperu.exominddemo.data.models.OpenWeatherMapResponse
import org.desperu.exominddemo.data.repositories.OpenWeatherMapRepository

/**
 * Weather Use Cases.
 */
interface WeatherUseCases {

    /**
     * Return the Weather for the given city.
     *
     * @param city      the city for which retrieve the weather.
     *
     * @return the Weather for the given city.
     */
    suspend fun getWeather(city: String): OpenWeatherMapResponse?

    /**
     * Return the Weather for the given cities.
     *
     * @param cities    the list of city for which retrieve the weather.
     *
     * @return the Weather for the given cities.
     */
    suspend fun getWeathers(cities: List<String>): List<OpenWeatherMapResponse>
}

/**
 * Implementation of the [WeatherUseCases] interface.
 *
 * @author Desperu
 *
 * @property openWeatherMapRepo      the repository for the OpenWeatherMap.
 *
 * @constructor Instantiates a new [WeatherUseCasesImpl].
 *
 * @param openWeatherMapRepo         the repository for the OpenWeatherMap to set.
 */
class WeatherUseCasesImpl(
    private val openWeatherMapRepo: OpenWeatherMapRepository
): WeatherUseCases {

    /**
     * {@inheritDoc}
     */
    override suspend fun getWeather(city: String): OpenWeatherMapResponse? = withContext(Dispatchers.IO) {
        openWeatherMapRepo.fetchWeather(city)
    }

    /**
     * {@inheritDoc}
     */
    override suspend fun getWeathers(cities: List<String>): List<OpenWeatherMapResponse> = withContext(Dispatchers.IO) {
        val weathers = mutableListOf<OpenWeatherMapResponse>()

        cities.forEach { city ->
            openWeatherMapRepo.fetchWeather(city)?.let { weathers.add(it) }
            delay(10000)
        }

        return@withContext weathers
    }
}
