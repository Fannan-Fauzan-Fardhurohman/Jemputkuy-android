package id.fannan.cloningojekonlineandroid

import android.location.Location
import android.os.Bundle
import id.fannan.cloningojekonlineandroid.databinding.ActivityMainBinding
import id.fannan.cloningojekonlineandroid.listener.HomeFragmentListener
import id.fannan.cloningojekonlineandroid.listener.MainActivityListener
import id.fannan.core.extensions.attachFragment
import id.fannan.utils.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>(), MainActivityListener {
    override fun inflateBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var homeTag: String

    override fun onCreateBinding(savedInstanceState: Bundle?) {
        homeTag = attachFragment(binding.mainFrame,HomeFragment::class)
    }

    private fun onLocation(data: Location) {
        val instance =
            supportFragmentManager.findFragmentByTag(homeTag) as? HomeFragmentListener
        instance?.onMessageFromActivity("anuan...")

    }

    override fun onLocationResult(data: Location) {
        onLocation(data)
    }

}