package org.desperu.exominddemo.di.module.ui

import android.view.View
import org.desperu.exominddemo.ui.base.BaseActivity
import org.desperu.exominddemo.ui.helper.SnackBarHelper
import org.desperu.exominddemo.ui.helper.SnackBarHelperImpl
import org.desperu.exominddemo.ui.main.MainInterface
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

    /**
     * Provides a MainInterface from the instance of MainFragment.
     */
    single { (activity: BaseActivity) ->
        activity as MainInterface
    }
}
