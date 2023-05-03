package id.fannan.core.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.fannan.core.state.StateEvent
import id.fannan.core.state.StateEventManager
import id.fannan.core.state.StateEventSubscriber
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

val <T>StateEvent<T>.value: T?
    get() {
        return if (this is StateEvent.Success) {
            this.data
        } else {
            null
        }
    }

fun <T> ViewModel.convertEventToSubscriber(
    eventManager: StateEventManager<T>,
    subscriber: StateEventSubscriber<T>
) {
    eventManager.subscribe(
        scope = viewModelScope,
        onIdle = subscriber::onIdle,
        onLoading = subscriber::onLoading,
        onFailure = subscriber::onFailure,
        onSuccess = subscriber::onSuccess
    )
}

fun <T> Flow<T>.mapStateEvent(): Flow<StateEvent<T>> {
    return this.catch {
        StateEvent.Failure<T>(it)
    }.map {
        StateEvent.Success(it)
    }
}