package org.desperu.exominddemo.ui.main.fragments.weather

import android.annotation.SuppressLint
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.desperu.exominddemo.data.models.OpenWeatherMapResponse
import org.desperu.exominddemo.domain.usecases.WeatherUseCases
import org.desperu.exominddemo.ui.utils.LoadingUtils.getFakeLoading
import org.desperu.exominddemo.ui.utils.LoadingUtils.getMessages
import org.desperu.exominddemo.ui.utils.cityList
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

/**
 * View Model which provide data for weather list.
 *
 * @param weatherUseCases    the weather use case which provide weather access function.
 *
 * @constructor Instantiates a new [WeatherViewModel].
 *
 * @property weatherUseCases    the weather use case which provide weather access function to set.
 * @property weatherInterface   the weather interface which provide fragment interface to set.
 * @property progress           the progress of the loading bar to set.
 * @property progressPercent    the progress in string with pattern "00 %" to set.
 * @property message            the message to display during loading to set.
 * @property weatherAdapter     the recycler view adapter to update data to the recycler to set.
 */
class WeatherViewModel(
    private val weatherUseCases: WeatherUseCases
): ViewModel(), KoinComponent {

    // FOR COMMUNICATION
    private val weatherInterface: WeatherInterface get() = get()

    // FOR DATA
    val progress = ObservableInt(0)
    val progressPercent = MutableLiveData("${progress.get()} %")
    val message = MutableLiveData("")
    val weatherAdapter get() = weatherInterface.getRecyclerAdapter()

    // --------------
    // WEB
    // --------------

    /**
     * Fetch the Weather list for the requested towns.
     */
    internal fun fetchWeathers() = viewModelScope.launch(Dispatchers.IO) {
        updateRecyclerData(weatherUseCases.getWeathers(cityList))
    }

    // --------------
    // LOADING
    // --------------

    /**
     * Display the downloading message to the user.
     */
    internal fun displayMessage() = viewModelScope.launch {
        while (true) {
            getMessages()
                .collect { message.value = it }
        }
    }

    /**
     * Update the loading bar.
     */
    internal fun updateLoading() = viewModelScope.launch {
        while (progress.get() <= 100) {
            getFakeLoading(progress.get())
                .collect {
                    progress.set(it)
                    progressPercent.value = "$it %"
                }
        }
    }

    // --------------
    // UPDATE
    // --------------

    /**
     * Update list into article list adapter.
     *
     * @param weatherList the new weather list to set.
     */
    @SuppressLint("NotifyDataSetChanged")
    private suspend fun updateRecyclerData(
        weatherList: List<OpenWeatherMapResponse>
    ) = withContext(Dispatchers.Main) {

        weatherAdapter?.apply {
            updateList(weatherList.map { ItemWeatherViewModel(it) }.toMutableList())
            notifyDataSetChanged()
        }
    }
}
