package org.desperu.exominddemo.ui.utils

import android.graphics.drawable.Drawable
import org.desperu.exominddemo.R
import org.desperu.exominddemo.services.ResourceService
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import java.lang.IllegalArgumentException

/**
 * [WeatherUtils] object which provide utils function to display the weather.
 */
object WeatherUtils: KoinComponent {

    // FOR DATA
    private val resource = get<ResourceService>()

    /**
     * Returns the drawable for the given icon code.
     *
     * @param iconCode  the given icon code.
     *
     * @return the drawable for the given icon code.
     */
    internal fun getWeatherIcon(iconCode: String): Drawable = when (parseNumber(iconCode)) {
        CLEAR_SKY -> resource.getDrawable(R.drawable.ic_sunny)
        FEW_CLOUDS -> resource.getDrawable(R.drawable.ic_partly_cloudy_day)
        SCATTERED_CLOUDS, BROKEN_CLOUDS -> resource.getDrawable(R.drawable.ic_cloudy)
        SHOWER_RAIN, RAIN -> resource.getDrawable(R.drawable.ic_rainy)
        THUNDERSTORM -> resource.getDrawable(R.drawable.ic_thunderstorm)
        SNOW -> resource.getDrawable(R.drawable.ic_snow)
        MIST -> resource.getDrawable(R.drawable.ic_cloudy) // Don't find mist icon on material.io
        else -> throw IllegalArgumentException("Icon code not found $iconCode")
    }

    /**
     * Parse given string with pattern "00a" to int.
     *
     * @param str   the given string to parse.
     *
     * @return given string with pattern "00a" to int.
     */
    private fun parseNumber(str: String): Int =
        str.replace("([a-z])".toRegex(), "").toInt()
}
