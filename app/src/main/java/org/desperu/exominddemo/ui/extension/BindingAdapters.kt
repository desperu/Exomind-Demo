package org.desperu.exominddemo.ui.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import org.desperu.exominddemo.ui.utils.WeatherUtils.getWeatherIcon

/**
 * Set the image with the icon code into the image view.
 * @param iconCode  the code of the icon to set.
 */
@BindingAdapter("setIcon")
fun ImageView.setIcon(iconCode: String?) {
    iconCode?.let { setImageDrawable(getWeatherIcon(it)) }
}
