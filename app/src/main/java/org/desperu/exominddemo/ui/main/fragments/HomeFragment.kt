package org.desperu.exominddemo.ui.main.fragments

import android.view.View
import androidx.databinding.DataBindingUtil
import org.desperu.exominddemo.R
import org.desperu.exominddemo.ui.base.BaseBindingFragment

class HomeFragment: BaseBindingFragment(), HomeHandler {

    // FOR DATA
    private val binding get() = viewBinding!!

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
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.setVariable(org.desperu.exominddemo.BR.handler, this)
        return binding.root
    }

    // -----------------
    // METHODS OVERRIDE
    // -----------------

    override fun onClickWeather() {
//        navigateToWeather()
    }
}

interface HomeHandler {

    /**
     * On click weather.
     */
    fun onClickWeather()
}
