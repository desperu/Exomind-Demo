package org.desperu.exominddemo.ui.main.fragments.weather

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import org.desperu.exominddemo.R
import org.desperu.exominddemo.databinding.FragmentWeatherBinding
import org.desperu.exominddemo.ui.base.BaseBindingFragment
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

/**
 * WeatherFragment, display the weather list for towns.
 *
 * @author Desperu
 */
class WeatherFragment: BaseBindingFragment(), WeatherInterface {

    // FOR DATA
    private val binding: FragmentWeatherBinding get() = (viewBinding as? FragmentWeatherBinding)!!
    private val viewModel = get<WeatherViewModel>()
    private var recyclerAdapter: RecyclerAdapter? = null

    // FOR UI
    private val recyclerView by lazy { binding.recyclerView }

    // --------------
    // BASE METHODS
    // --------------

    override fun getBindingView(): View = configureViewModel()

    override fun configureDesign() {
        configureKoinDependency()
        configureRecyclerView()
    }

    override fun updateDesign() {
        launchData()
    }

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

    /**
     * Configure koin dependency for weather fragment.
     */
    private fun configureKoinDependency() {
        get<WeatherInterface> { parametersOf(this) }
    }

    /**
     * Configure Recycler view.
     */
    private fun configureRecyclerView() {
        recyclerAdapter = RecyclerAdapter(R.layout.item_weather)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
    }

    // -----------------
    // METHODS OVERRIDE
    // -----------------

    override fun onDestroy() {
        super.onDestroy()
        recyclerAdapter = null
    }

    // --------------
    // DATA
    // --------------

    /**
     * Launch the data for the weather recycler view.
     */
    private fun launchData() = lifecycleScope.launch {
        viewModel.run {
            fetchWeathers()
            displayMessage()
            updateLoading()
        }
    }

    // --- GETTERS ---

    /**
     * Returns the recycler adapter instance.
     */
    override fun getRecyclerAdapter(): RecyclerAdapter? = recyclerAdapter
}

/**
 * Weather interface that's allow communication with it's fragment.
 */
interface WeatherInterface {

    /**
     * Returns the recycler adapter instance.
     */
    fun getRecyclerAdapter(): RecyclerAdapter?
}
