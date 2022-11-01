package org.desperu.exominddemo.di.module.ui

import org.desperu.exominddemo.ui.base.BaseBindingFragment
import org.desperu.exominddemo.ui.main.fragments.weather.WeatherInterface
import org.koin.dsl.module

/**
 * Koin module which provide dependencies related to main activity.
 */
val mainModule = module {

    /**
     * Provides a WeatherInterface from the instance of WeatherFragment.
     */
    single { (fragment: BaseBindingFragment) ->
        fragment as WeatherInterface
    }
}
