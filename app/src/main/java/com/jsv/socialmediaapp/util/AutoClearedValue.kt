package com.jsv.socialmediaapp.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import androidx.lifecycle.DefaultLifecycleObserver


class AutoClearedValue<T : Any>(val fragment: Fragment) : ReadWriteProperty<Fragment, T> {
    private var _value: T? = null

    init {
        fragment.lifecycle.addObserver(object: DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
                    viewLifecycleOwner?.lifecycle?.addObserver(object: DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            _value = null
                        }
                    })
                }
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return _value ?: throw IllegalStateException(
            "call auto-cleared-value only where is available"
        )
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        _value = value
    }
}


fun <T : Any> Fragment.autoCleared() = AutoClearedValue<T>(this)