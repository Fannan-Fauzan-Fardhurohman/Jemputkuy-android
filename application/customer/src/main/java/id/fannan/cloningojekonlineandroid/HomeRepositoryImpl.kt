package id.fannan.cloningojekonlineandroid

import android.location.Location
import id.fannan.core.state.MutableStateEventManager
import id.fannan.core.state.StateEventManager
import id.fannan.core.LocationManager
import id.fannan.core.extensions.mapStateEvent
import id.fannan.core.state.StateEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class HomeRepositoryImpl(
    private val locationManager: LocationManager
) : HomeRepository {

    private val _locationResult = MutableStateEventManager<Location>()
    override val locationResult: StateEventManager<Location>
        get() = _locationResult

    override suspend fun getLocation() {
//        locationManager.getLocationFlowEvent().collect{
//            print("----- $it ------")
//            _locationResult.emit(it)
//        }
        locationManager.getLocationFlow()
            .mapStateEvent()
            .collect{
                println("------ wow $it")
                _locationResult.emit(it)

            }
    }
}