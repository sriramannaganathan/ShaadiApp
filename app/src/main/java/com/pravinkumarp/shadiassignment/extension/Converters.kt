package com.pravinkumarp.shadiassignment.extension

import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*


object Converters {
    private fun getDateFormatter(): SimpleDateFormat {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSS", Locale.getDefault());
    }

    private fun getDateSmallFormatter(): SimpleDateFormat {
        return SimpleDateFormat("dd MMM", Locale.getDefault());
    }

    fun toSmallDate(date: String): String {
        val formattedDate = getDateFormatter().parse(date);
        return getDateSmallFormatter().format(formattedDate!!)
    }

    fun <T> MutableLiveData<T>.modifyValue(transform: T.() -> T) {
        this.value = this.value?.run(transform)
    }
}
