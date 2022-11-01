package org.desperu.exominddemo.ui.main.fragments.weather

import androidx.lifecycle.ViewModel
import org.desperu.exominddemo.data.models.OpenWeatherMapResponse
import kotlin.math.roundToInt

/**
 * View Model which provide data for weather item.
 *
 * @param weather           the weather object data.
 *
 * @constructor Instantiates a new [ItemWeatherViewModel].
 *
 * @property weather        the weather object data to set.
 * @property weatherIcon    the weather icon code to set.
 * @property weatherTemp    the weather temperature to set.
 */
class ItemWeatherViewModel(
    val weather: OpenWeatherMapResponse // We use here API Object and don't map to UI Object to spend less time
): ViewModel() {

    // FOR DATA
    val weatherIcon = weather.weather[0].icon
    val weatherTemp = weather.main.temp.roundToInt().toString()
}
