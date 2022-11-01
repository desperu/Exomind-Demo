package org.desperu.exominddemo.data.models

import com.google.gson.annotations.Expose

/**
 * Model used to parse OpenWeather Map API response, from JSON.
 *
 * We parse here only needed data for this demo.
 */
data class OpenWeatherMapResponse(
    @Expose val weather: List<Weather>,
    @Expose val main: Main,
    @Expose var name: String
)

data class Weather(
    @Expose val id: Int,
    @Expose val main: String,
    @Expose val description: String,
    @Expose val icon: String
)

data class Main(
    @Expose val temp: Double
)
