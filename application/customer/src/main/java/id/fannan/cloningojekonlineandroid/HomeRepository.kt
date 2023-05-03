package id.fannan.cloningojekonlineandroid

import android.location.Location
import id.fannan.core.state.StateEventManager

interface HomeRepository {
    val locationResult: StateEventManager<Location>
    suspend fun getLocation()
}