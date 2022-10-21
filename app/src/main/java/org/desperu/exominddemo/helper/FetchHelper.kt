package org.desperu.exominddemo.helper

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * FetchHelper which provide functions to fetch data.
 */
object FetchHelper {

    /**
     * Wrap coroutine block in secure call (try/catch), add message for logs.
     * Used in network repository to fetch data.
     *
     * @param block the coroutine block to execute into secure call.
     *
     * @return the fetched data list.
     */
    internal suspend fun <T : Any> catchFetch(
        block: suspend () -> T?
    ): T? = // Use T on test
        try {
            withContext(Dispatchers.IO) { return@withContext block() }
        } catch (e: Exception) {

            // add store fetch result

            val tag = "${block.javaClass.enclosingClass?.simpleName}" +
                    "-${block.javaClass.enclosingMethod?.name}"
            Log.e(tag, e.message.toString())
            e.printStackTrace()

            null
        }
}
