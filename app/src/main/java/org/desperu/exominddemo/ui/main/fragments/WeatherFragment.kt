package org.desperu.exominddemo.ui.main.fragments

import android.view.View
import androidx.databinding.DataBindingUtil
import org.desperu.exominddemo.R
import org.desperu.exominddemo.ui.base.BaseBindingFragment
import org.koin.android.ext.android.get

class WeatherFragment: BaseBindingFragment() {

    // FOR DATA
    private val binding get() = viewBinding!!
    private val viewModel = get<WeatherViewModel>()

    // --------------
    // BASE METHODS
    // --------------

    override fun getBindingView(): View = configureViewModel()

    override fun configureDesign() {}

    override fun updateDesign() {}

    // --------------
    // CONFIGURATION
    // --------------

    /**
     * Configure data binding, recycler view and view model.
     */
    private fun configureViewModel(): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)
        binding.setVariable(org.desperu.exominddemo.BR.viewModel, viewModel)
        return binding.root
    }

    // --------------
    // METHODS OVERRIDE
    // --------------
}
