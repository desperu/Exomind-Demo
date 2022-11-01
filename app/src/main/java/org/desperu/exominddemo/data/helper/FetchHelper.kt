package org.desperu.exominddemo.data.helper

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.desperu.exominddemo.ui.helper.SnackBarHelper
import org.koin.core.component.KoinComponent

/**
 * FetchHelper which provide functions to fetch data.
 */
object FetchHelper: KoinComponent    {

    // FOR DATA
    private val snackBarHelper: SnackBarHelper? get() = getKoin().getOrNull()

    /**
     * Wrap coroutine block in secure call (try/catch), add message for logs.
     * Used in network repository to fetch data and display message to the user.
     *
     * @param block the coroutine block to execute into secure call.
     *
     * @return the fetched data list, or null if an error happened.
     */
    internal suspend fun <T: Any> catchFetchWithMessage(
        block: suspend () -> T?
    ): T? =
        try {
            withContext(Dispatchers.IO) { return@withContext block() }
        } catch (e: Exception) {

            val tag = "${block.javaClass.enclosingClass?.simpleName}" +
                    "-${block.javaClass.enclosingMethod?.name}"
            Log.e(tag, e.message.toString())
            e.printStackTrace()

            snackBarHelper?.showMessage(e.message.toString())

            null
        }
}
