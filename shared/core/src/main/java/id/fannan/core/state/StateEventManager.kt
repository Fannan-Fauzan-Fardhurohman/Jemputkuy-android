package id.fannan.core.state

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.Closeable

abstract class StateEventManager<T> {
    var value: StateEvent<T> = StateEvent.Idle()
        protected set
    abstract var errorDispatcher: CoroutineExceptionHandler
    abstract var listener: StateEvent<T>.(StateEventManager<T>) -> Unit

    abstract fun subscribe(
        scope: CoroutineScope,
        onIdle: () -> Unit = {},
        onLoading: () -> Unit = {},
        onFailure: (throwable: Throwable) -> Unit = {},
        onSuccess: (T) -> Unit = {}
    )

    abstract fun <F> map(mapper: (T) -> F): StateEventManager<F>
    abstract fun invoke(): T?
    abstract fun createScope(another: CoroutineScope): CoroutineScope
}