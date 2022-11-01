package org.desperu.exominddemo.ui.main.fragments.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Recycler view adapter for Source List.
 *
 * @property layoutId   the unique identifier of the item layout to set.
 *
 * @constructor Instantiate a new SourceListAdapter.
 *
 * @param layoutId      the unique identifier of the item layout to set.
 */
class RecyclerAdapter(
    @LayoutRes private val layoutId: Int
): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    // FOR DATA
    private lateinit var list: MutableList<Any>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), viewType, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemViewType(position: Int) = layoutId

    override fun getItemCount(): Int = if (::list.isInitialized) list.size else 0

    /**
     * Update all item list.
     * @param newList the new list to set.
     */
    internal fun updateList(newList: MutableList<Any>) { list = newList }

    class ViewHolder(private val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {

        internal fun bind(any: Any?) {
            binding.setVariable(org.desperu.exominddemo.BR.viewModel, any)
            binding.executePendingBindings()
        }
    }
}