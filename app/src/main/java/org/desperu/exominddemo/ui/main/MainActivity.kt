package org.desperu.exominddemo.ui.main

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import org.desperu.exominddemo.R
import org.desperu.exominddemo.di.module.ui.mainModule
import org.desperu.exominddemo.ui.base.BaseActivity

/**
 * Main Activity root activity of the application.
 *
 * @property mainModule the koin module of the activity to load at start.
 *
 * @constructor Instantiates a new MainActivity.
 */
class MainActivity : BaseActivity(mainModule) {

    // FOR DATA
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    // --------------
    // BASE METHODS
    // --------------

    override fun getActivityLayout(): Int = R.layout.activity_main

    override fun configureDesign() {
        configureNavController()
    }

    // --------------
    // CONFIGURATION
    // --------------

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
}
