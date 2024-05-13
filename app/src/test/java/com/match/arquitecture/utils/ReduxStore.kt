package com.match.arquitecture.utils


import com.match.arquitecture.redux.reducers.AppState
import org.reduxkotlin.GetState
import org.reduxkotlin.Store
import org.reduxkotlin.StoreSubscriber
import org.reduxkotlin.StoreSubscription
import org.reduxkotlin.TypedDispatcher
import org.reduxkotlin.TypedReducer
import org.reduxkotlin.TypedStore

class ReduxStore: TypedStore<AppState, Any> {
    override var dispatch: TypedDispatcher<Any>
        get() = TODO("Not yet implemented")
        set(value) {}
    override val getState: GetState<AppState>
        get() = { AppState() }
    override val replaceReducer: (TypedReducer<AppState, Any>) -> Unit
        get() = TODO("Not yet implemented")
    override val store: Store<AppState>
        get() = this
    override val subscribe: (StoreSubscriber) -> StoreSubscription
        get() = TODO("Not yet implemented")

}