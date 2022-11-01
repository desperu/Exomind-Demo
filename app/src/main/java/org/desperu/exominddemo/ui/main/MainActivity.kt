package org.desperu.exominddemo.ui.main

import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import org.desperu.exominddemo.R
import org.desperu.exominddemo.di.module.ui.mainModule
import org.desperu.exominddemo.ui.base.BaseActivity
import org.desperu.exominddemo.ui.helper.SnackBarHelper
import org.desperu.exominddemo.ui.main.fragments.home.HomeFragment
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

/**
 * Main Activity root activity of the application.
 *
 * @property mainModule the koin module of the activity to load at start.
 *
 * @constructor Instantiates a new MainActivity.
 */
class MainActivity : BaseActivity(mainModule), MainInterface {

    // FOR DATA
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    // FOR UI
    private val backArrow by lazy { findViewById<AppCompatImageView>(R.id.back_arrow) }

    // --------------
    // BASE METHODS
    // --------------

    override fun getActivityLayout(): Int = R.layout.activity_main

    override fun configureDesign() {
        configureKoinDependency()
        configureNavController()
        configureBackArrow()
        configureBackPressed()
    }

    // --------------
    // CONFIGURATION
    // --------------

    /**
     * Configure koin dependency for Main Activity.
     */
    private fun configureKoinDependency() {
        get<MainInterface> { parametersOf(this) }
        get<SnackBarHelper> { parametersOf(findViewById(R.id.constraint_layout)) }
    }

    /**
     * Configure the navigation controller and set graph to show start destination with arguments.
     */
    private fun configureNavController() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Set graph and so, show start fragment (article frag).
        navController.setGraph(R.navigation.nav_grap)
    }

    /**
     * Configure the back arrow, and setup listener.
     */
    private fun configureBackArrow() {
        backArrow.setOnClickListener {
            navigateToHomeFrag()
        }
    }

    /**
     * Override onBackPressed Callback to finish activity.
     */
    private fun configureBackPressed() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (getCurrentFragment() is HomeFragment)
                    finishAffinity()
                else
                    navigateToHomeFrag()
            }
        })
    }

    // --------------
    // FRAGMENT
    // --------------

    /**
     * Return the current fragment instance host by the navigation host fragment.
     *
     * @return the current fragment instance.
     */
    private fun getCurrentFragment(): Fragment? =
        navHostFragment.childFragmentManager.primaryNavigationFragment

    /**
     * Navigate to [HomeFragment].
     */
    private fun navigateToHomeFrag() {
        navController.navigate(R.id.homeFragment)
    }

    // --------------
    // UI
    // --------------

    /**
     * Show or hide back arrow depends of enable value.
     *
     * @param enable    true to show, false to hide.
     */
    override fun showBackArrow(enable: Boolean) {
        backArrow.visibility = if (enable) View.VISIBLE
                               else View.GONE

    }
}

/**
 * Main Interface that allow function acces.
 */
interface MainInterface {

    /**
     * Show or hide back arrow depends of enable value.
     *
     * @param enable    true to show, false to hide.
     */
    fun showBackArrow(enable: Boolean)
}
