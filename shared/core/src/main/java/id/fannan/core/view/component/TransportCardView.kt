package id.fannan.core.view.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import id.fannan.core.R
import id.fannan.core.extensions.findIdByLazy

class TransportCardView(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet) {

    private val componentView: View by findIdByLazy(R.id.transport_card_root)
    private val imageView: ImageView by findIdByLazy(R.id.transport_card_img)
    private val titleView: TextView by findIdByLazy(R.id.transport_card_title)

    private var _imageRes: Int = -1
    private var _title: String = ""
    private var _isSelected: Boolean = false
    var imageRes: Int
        get() = _imageRes
        set(value) {
            setImageView(value)
        }

    var title: String
        get() = _title
        set(value){
            titleView.text = value
        }

    init {
        inflate(context, R.layout.component_transport_card, this)
        context.obtainStyledAttributes(attributeSet, R.styleable.TransportCardView, 0, 0)
            .apply {
                val image = getResourceId(R.styleable.TransportCardView_image, -1)
                val title = getString(R.styleable.TransportCardView_title).orEmpty()
                val isSelected = getBoolean(R.styleable.TransportCardView_isSelected, isSelected)
                _title = title
                setImageView(image)
                setBackgroundRoot(isSelected)
                titleView.text = title
            }.recycle()
    }

    private fun setImageView(imageRes: Int) {
        _imageRes = imageRes

        if (imageRes != -1) {
            imageView.setImageResource(imageRes)
        }
    }

    private fun setBackgroundRoot(isSelected: Boolean) {
        this.isSelected = isSelected
        val backgroundRoot = if (isSelected) {
            R.drawable.bg_transport_card_stroke
        } else {
            R.drawable.bg_transport_card_white
        }
        componentView.setBackgroundResource(backgroundRoot)


    }

    override fun isSelected(): Boolean {
        return super.isSelected()
    }
}