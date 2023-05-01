package id.fannan.core.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import id.fannan.core.R
import id.fannan.core.extensions.findIdByLazy
import id.fannan.core.view.component.TransportCardView

class ComponentPlaygroundActivity : AppCompatActivity() {
    companion object {
        fun launch(context: Context){
            context.startActivity(
                Intent(context, ComponentPlaygroundActivity::class.java)
            )
        }
    }

    private val transportBike:TransportCardView by findIdByLazy(R.id.transport_bike)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_component_playground)
        transportBike.imageRes = R.drawable.ic_transport_car
        transportBike.title = "Anuan Mas"
    }
}