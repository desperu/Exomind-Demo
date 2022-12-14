package org.desperu.exominddemo.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

/**
 * Abstract base activity class witch provide standard functions for activities.
 *
 * @param module the koin module to load for the corresponding activity.
 */
abstract class BaseActivity(private vararg val module: Module): AppCompatActivity() {

    init {
        unloadKoinModules(module.toList())
        loadKoinModules(module.toList())
    }

    // --------------------
    // BASE METHODS
    // --------------------

    protected abstract fun getActivityLayout(): Int
    protected abstract fun configureDesign()

    // --------------------
    // LIFE CYCLE
    // --------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(getActivityLayout())
        configureDesign()
    }

    override fun onDestroy() {
        unloadKoinModules(module.toList())
        super.onDestroy()
    }
}