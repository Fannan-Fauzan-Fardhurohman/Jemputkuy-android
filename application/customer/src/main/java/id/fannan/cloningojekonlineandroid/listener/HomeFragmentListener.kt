package id.fannan.cloningojekonlineandroid.listener

import id.fannan.listener.FragmentListener

interface HomeFragmentListener :FragmentListener{
    fun onMessageFromActivity(message: String)
}