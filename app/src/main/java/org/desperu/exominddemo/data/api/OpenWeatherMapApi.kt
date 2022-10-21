package org.desperu.exominddemo.data.api

import org.desperu.exominddemo.BuildConfig
import org.desperu.exominddemo.data.models.OpenWeatherMapAPI
import org.desperu.exominddemo.data.utils.METRIC
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface to request Open Weather Map APIs.
 */
interface OpenWeatherMapApi {

    /**
     * Returns the weather for the given city.
     *
     * @param city      the requested city.
     * @param apiKey    the api key.
     * @param units     the requested unit.
     *
     * @return the weather for the given city.
     */
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = BuildConfig.OPEN_WEATHER_MAP_API_KEY,
        @Query("units") units: String = METRIC
    ): OpenWeatherMapAPI
}