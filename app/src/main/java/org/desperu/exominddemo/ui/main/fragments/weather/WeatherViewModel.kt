package org.desperu.exominddemo.ui.main.fragments.weather

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.desperu.exominddemo.domain.usecases.WeatherUseCases
import org.desperu.exominddemo.ui.utils.LoadingUtils.getFakeLoading
import org.desperu.exominddemo.ui.utils.LoadingUtils.getMessages
import org.desperu.exominddemo.ui.utils.cityList

/**
 * View Model which provide data for weather list.
 *
 * @param weatherUseCases    the weather use case which provide weather access function.
 *
 * @constructor Instantiates a new [WeatherViewModel].
 *
 * @property weatherUseCases    the weather use case which provide weather access function to set.
 * @property progress           the progress of the loading bar to set.
 * @property progressPercent    the progress in string with pattern "00 %" to set.
 * @property message            the message to display during loading to set.
 */
class WeatherViewModel(
    private val weatherUseCases: WeatherUseCases
): ViewModel() {

    // FOR DATA
    val progress = ObservableInt(0)
    val progressPercent = MutableLiveData("${progress.get()} %")
    val message = MutableLiveData("")

    // --------------
    // WEB
    // --------------

    /**
     * Fetch the Weather list for the requested towns.
     * And handle lifecycle thread for weather fetch, message and fake loading.
     */
    internal suspend fun fetchWeathers() = withContext(Dispatchers.Main) {
        val messagesThread = async { displayMessage() }
        val loadingThread = async { updateLoading() }

        messagesThread.start()
        loadingThread.start()

        val weathers = weatherUseCases.getWeathers(cityList)

        messagesThread.cancel()
        loadingThread.cancel()
        progress.set(0)

        return@withContext weathers
    }

    // --------------
    // LOADING
    // --------------

    /**
     * Display the downloading message to the user.
     */
    private suspend fun displayMessage() = withContext(Dispatchers.Main) {
        while (true) {
            getMessages()
                .collect { message.value = it }
        }
    }

    /**
     * Update the loading bar.
     */
    private suspend fun updateLoading() = withContext(Dispatchers.Main) {
        while (progress.get() <= 100) {
            getFakeLoading(progress.get())
                .collect {
                    progress.set(it)
                    progressPercent.value = "$it %"
                }
        }
    }
}
