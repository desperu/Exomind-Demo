package org.desperu.exominddemo.ui.helper

import android.view.View
import com.google.android.material.behavior.SwipeDismissBehavior
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.desperu.exominddemo.ui.utils.DURATION
import org.koin.core.component.KoinComponent

/**
 * SnackBarHelper witch provide functions to display messages into the snack bar.
 */
interface SnackBarHelper {

    /**
     * Show the message into the snack bar for the given key and data.
     *
     * @param message the message to display to the user.
     */
    suspend fun showMessage(message: String): Unit?
}

/**
 * Implementation of the SnackBarHelper which use an Activity instance
 * to display message into the snack bar.
 *
 * @param rootView      the root view of the current activity.
 *
 * @constructor Instantiate a new SnackBarHelperImpl.
 *
 * @property rootView   the root view of the current activity, to set.
 */
class SnackBarHelperImpl(private val rootView: View) : SnackBarHelper, KoinComponent {

    // FOR DATA
    private var snackBar: Snackbar? = null

    // --------------
    // CALL FUNCTION
    // --------------

    /**
     * Show the message into the snack bar for the given key and data.
     *
     * @param message the message to display to the user.
     */
    override suspend fun showMessage(message: String) = withContext(Dispatchers.Main) {
        if (snackBar == null)
            initSnackBar(message)
        else
            updateSnackBar(message)

        snackBar?.show()
    }

    // --------------
    // CONFIGURATION
    // --------------

    /**
     * Init the snack bar for the given key and data.
     *
     * @param message the message to display to the user.
     */
    private fun initSnackBar(message: String) {
        snackBar = Snackbar.make(
            rootView,
            message,
            DURATION
        )
        snackBar?.apply {
            behavior = BaseTransientBottomBar.Behavior()
            behavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY)
        }
    }

    /**
     * Update the snack bar with the new message, the duration, and add button if needed.
     *
     * @param message the message to display to the user.
     */
    private fun updateSnackBar(message: String) {
        snackBar?.setText(message)
    }
}
