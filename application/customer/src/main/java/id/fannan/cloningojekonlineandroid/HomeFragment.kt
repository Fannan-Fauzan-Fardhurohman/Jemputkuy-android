package id.fannan.cloningojekonlineandroid

import android.Manifest
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import id.fannan.cloningojekonlineandroid.databinding.FragmentHomeBinding
import id.fannan.cloningojekonlineandroid.listener.HomeFragmentListener
import id.fannan.cloningojekonlineandroid.listener.MainActivityListener
import id.fannan.core.extensions.toLatLng
import id.fannan.core.state.StateEventSubscriber
import id.fannan.listener.findActivityListener
import id.fannan.utils.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import pub.devrel.easypermissions.EasyPermissions

class HomeFragment : BindingFragment<FragmentHomeBinding>(), HomeFragmentListener {
    companion object {
        private const val RC_LOCATION = 16
    }

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var map: GoogleMap

    override fun inflateBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateBinding(savedInstanceState: Bundle?) {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map_view) as SupportMapFragment
        mapFragment.getMapAsync {
            map = it
            getLocationWithPermission()

        }

        viewModel.subscribeLocation(locationSubscriber())
    }

    private fun getLocationWithPermission() {
        val fineLocation = Manifest.permission.ACCESS_FINE_LOCATION
        val coarseLocation = Manifest.permission.ACCESS_COARSE_LOCATION
        context?.let {
            if (EasyPermissions.hasPermissions(it, fineLocation, coarseLocation)) {
                viewModel.getLocation()
            } else {
                EasyPermissions.requestPermissions(
                    this,
                    "Granted for Location",
                    RC_LOCATION,
                    fineLocation,
                    coarseLocation
                )
            }
        }

    }

    private fun locationSubscriber() = object : StateEventSubscriber<Location> {
        override fun onIdle() {
            println("-------- location idle")
        }

        override fun onLoading() {
            println("-------- location loading")
        }

        override fun onFailure(throwable: Throwable) {
            println("-------- location failure ----> ${throwable.message}")
        }

        override fun onSuccess(data: Location) {
            println("-------- location success ----> $data")
            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(data.toLatLng(), 14f)
            map.animateCamera(cameraUpdate)
            findActivityListener<MainActivityListener>()?.onLocationResult(data)
        }

    }

    private fun actionFromActivity(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onMessageFromActivity(message: String) {
        actionFromActivity(message)
    }
}