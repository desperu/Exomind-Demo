package org.desperu.exominddemo.data.models

import com.google.gson.annotations.Expose

/**
 * Model used to parse OpenWeather Map API response, from JSON.
 *
 * TODO: We parse here only needed data for this demo.
 */
data class OpenWeatherMapResponse(
    @Expose val weather: Weather,
    @Expose val main: Main,
    @Expose val name: String
)

data class Weather(
    @Expose val id: Number,
    @Expose val main: String,
    @Expose val description: String,
    @Expose val icon: String
)

data class Main(
    @Expose val temp: Number
)
