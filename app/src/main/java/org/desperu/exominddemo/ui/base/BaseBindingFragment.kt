package org.desperu.exominddemo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Abstract base activity class witch provide standard functions for binding fragment.
 */
abstract class BaseBindingFragment : Fragment() {

    // FOR DATA
    protected lateinit var inflater: LayoutInflater
    protected var container: ViewGroup? = null
    protected var viewBinding: ViewDataBinding? = null

    // --------------
    // BASE METHODS
    // --------------

    protected abstract fun getBindingView(): View
    protected abstract fun configureDesign()
    protected abstract fun updateDesign()

    // -----------------
    // METHODS OVERRIDE
    // -----------------

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        this.inflater = inflater
        this.container = container
        return getBindingView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding?.lifecycleOwner = this
        configureDesign()
    }

    override fun onResume() {
        super.onResume()
        updateDesign()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
        container = null
    }
}