package org.desperu.exominddemo.di.module

import org.desperu.exominddemo.ui.main.fragments.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module which provides view model dependencies.
 */
val viewModelModule = module {

    /**
     * Provides the WeatherViewModel instance.
     */
    viewModel {
        WeatherViewModel()
    }
}
