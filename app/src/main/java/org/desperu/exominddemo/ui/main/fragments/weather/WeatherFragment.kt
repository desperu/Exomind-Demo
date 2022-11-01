package org.desperu.exominddemo.ui.main.fragments.weather

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import org.desperu.exominddemo.R
import org.desperu.exominddemo.databinding.FragmentWeatherBinding
import org.desperu.exominddemo.ui.base.BaseBindingFragment
import org.desperu.exominddemo.ui.main.MainInterface
import org.koin.android.ext.android.get

/**
 * WeatherFragment, display the weather list for towns.
 *
 * @author Desperu
 */
class WeatherFragment: BaseBindingFragment(), WeatherHandler {

    // FOR DATA
    private val binding: FragmentWeatherBinding get() = (viewBinding as? FragmentWeatherBinding)!!
    private val viewModel = get<WeatherViewModel>()
    private var recyclerAdapter: RecyclerAdapter? = null

    // FOR UI
    private val recyclerView by lazy { binding.recyclerView }
    private val loadingContainer by lazy { binding.clProgressContainer }
    private val retryBtn by lazy { binding.btnRetry }

    // --------------
    // BASE METHODS
    // --------------

    override fun getBindingView(): View = configureViewModel()

    override fun configureDesign() {
        configureRecyclerView()
        showBackArrow()
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
        binding.setVariable(org.desperu.exominddemo.BR.handler, this)
        return binding.root
    }

    /**
     * Configure Recycler view.
     */
    private fun configureRecyclerView() {
        recyclerAdapter = RecyclerAdapter(R.layout.item_weather)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
    }

    // --------------
    // DATA
    // --------------

    /**
     * Launch the data for the weather recycler view.
     */
    private fun launchData() = lifecycleScope.launch {
        showRetryButton(false)
        updateRecyclerView(viewModel.fetchWeathers().map { ItemWeatherViewModel(it) })
        showRetryButton(true)
    }

    // --------------
    // ACTIONS
    // --------------

    /**
     * On click retry button.
     */
    override fun onClickRetry() {
        updateRecyclerView(emptyList()) // Reset recycler data
        launchData()
    }

    // --------------
    // UI
    // --------------

    /**
     * Show the back arrow.
     */
    private fun showBackArrow() {
        get<MainInterface>().showBackArrow(true)
    }

    /**
     * Update the recycler view data.
     *
     * @param items the item list to set.
     */
    private fun updateRecyclerView(items: List<ItemWeatherViewModel>) {
        recyclerAdapter?.updateList(items.toMutableList())
    }

    /**
     * Show or hide retry button, depends of the toShow value.
     * @param toShow true to show, false to hide retry button.
     */
    private fun showRetryButton(toShow: Boolean) {
        if (toShow) {
            retryBtn.visibility = View.VISIBLE
            loadingContainer.visibility = View.GONE
        } else {
            retryBtn.visibility = View.GONE
            loadingContainer.visibility = View.VISIBLE
        }
    }
}

/**
 * Handler for the Weather Fragment.
 */
interface WeatherHandler {

    /**
     * On click retry button.
     */
    fun onClickRetry()
}
