package org.desperu.exominddemo.ui.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import org.desperu.exominddemo.R
import org.desperu.exominddemo.services.ResourceService
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import java.lang.Integer.min

/**
 * [LoadingUtils] object witch provide utils functions for user message during downloading.
 */
object LoadingUtils : KoinComponent {

    // FOR DATA
    private val resource = get<ResourceService>()

    /**
     * Fake loading duration, one minute to reach 100%.
     *
     * @param progress  the current progress.
     */
    internal suspend fun getFakeLoading(progress: Int) = flow {
        delay(ONE_MIN_MILLIS / 100)
        emit(min(progress + 1, 100))
    }

    /**
     * Returns downloading messages.
     *
     * @return downloading messages.
     */
    internal suspend fun getMessages() = flow {
        emit(resource.getString(R.string.downloads))
        delay(6000)
        emit(resource.getString(R.string.almost_over))
        delay(6000)
        emit(resource.getString(R.string.some_seconds))
        delay(6000)
    }
}
