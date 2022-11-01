package org.desperu.exominddemo.di.module.ui

import android.view.View
import org.desperu.exominddemo.ui.helper.SnackBarHelper
import org.desperu.exominddemo.ui.helper.SnackBarHelperImpl
import org.koin.dsl.module

/**
 * Koin module which provide dependencies related to main activity.
 */
val mainModule = module {

    /**
     * Provides a SnackBarHelper interface from the instance of the root view.
     */
    single<SnackBarHelper> { (rootView: View) ->
        SnackBarHelperImpl(
            rootView
        )
    }
}
