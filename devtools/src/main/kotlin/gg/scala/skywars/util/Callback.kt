package gg.scala.skywars.util

import java.io.Serializable

fun interface Callback<T> : Serializable {
    /**
     * Called when the request is successfully completed
     *
     * @param data the data received from the call
     */
    fun callback(data: T)
}