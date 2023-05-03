package id.fannan.cloningojekonlineandroid

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.fannan.core.extensions.convertEventToSubscriber
import id.fannan.core.state.StateEventSubscriber
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {
    private val locationEvent = repository.locationResult

    private val locationScope = locationEvent.createScope(viewModelScope)
    fun subscribeLocation(subscriber: StateEventSubscriber<Location>) {
        convertEventToSubscriber(locationEvent, subscriber)
    }

    fun getLocation() = locationScope.launch {
        repository.getLocation()
    }
}