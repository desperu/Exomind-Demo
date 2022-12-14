package org.desperu.exominddemo.ui.main.fragments.home

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import org.desperu.exominddemo.R
import org.desperu.exominddemo.ui.base.BaseBindingFragment
import org.desperu.exominddemo.ui.main.MainInterface
import org.desperu.exominddemo.ui.main.fragments.weather.WeatherFragment
import org.koin.android.ext.android.get

/**
 * HomeFragment, fragment show at the application launching.
 *
 * @author Desperu
 */
class HomeFragment: BaseBindingFragment(), HomeHandler {

    // FOR DATA
    private val binding get() = viewBinding!!

    // --------------
    // BASE METHODS
    // --------------

    override fun getBindingView(): View = configureViewModel()

    override fun configureDesign() {}

    override fun updateDesign() {
        hideBackArrow()
    }

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
        navigateToWeather()
    }

    // -----------------
    // NAVIGATION
    // -----------------

    /**
     * Navigate to the [WeatherFragment]
     */
    private fun navigateToWeather() {
        findNavController().navigate(R.id.action_homeFragment_to_weatherFragment)
    }

    // --------------
    // UI
    // --------------

    /**
     * Hide Back arrow.
     */
    private fun hideBackArrow() {
        get<MainInterface>().showBackArrow(false)
    }
}

/**
 * Handler for the Home Fragment.
 */
interface HomeHandler {

    /**
     * On click weather.
     */
    fun onClickWeather()
}
