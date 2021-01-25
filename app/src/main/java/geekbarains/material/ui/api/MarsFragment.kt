package geekbarains.material.ui.api

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.squareup.picasso.Picasso
import geekbarains.material.R
import geekbarains.material.ui.marspicture.MarsData
import geekbarains.material.ui.marspicture.MarsPictureViewModel
import kotlinx.android.synthetic.main.fragment_mars.*
import kotlinx.android.synthetic.main.main_fragment.*
import java.io.File

class MarsFragment : Fragment() {

    private val viewModel: MarsPictureViewModel by lazy {
        ViewModelProviders.of(this).get(MarsPictureViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData()
                .observe(this@MarsFragment, Observer<MarsData>{ renderData(it)} )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mars, container, false)
    }

    private fun renderData(data: MarsData) {
        when (data) {
            is MarsData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.photos.first().url
                if (url.isNullOrEmpty()) {
                    toast("Link is empty")
                } else {
                    image_view_mars.load(url) {
                        lifecycle(this@MarsFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)
                   }
                }
            }
            is MarsData.Loading -> {
                toast("Loading")
            }
            is MarsData.Error -> {
                toast(data.error.message)
            }
        }
    }

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }

    companion object {
        fun newInstance() = MarsFragment()
    }
}
