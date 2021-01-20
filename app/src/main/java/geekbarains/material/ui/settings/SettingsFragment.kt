package geekbarains.material.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import geekbarains.material.R
import kotlinx.android.synthetic.main.fragment_chips.*

class SettingsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chipGroup.setOnCheckedChangeListener { chipGroup, position ->
            chipGroup.findViewById<Chip>(position)?.let {
                Toast.makeText(context, "Выбран ${it.text}", Toast.LENGTH_SHORT).show()
                if (it.text == getString(R.string.theme1)){
                    activity?.setTheme(R.style.PinkTheme)
                }
                else{
                    activity?.setTheme(R.style.IndigoTheme)
                }
                activity?.recreate()
            }
        }

        chip_close.setOnCloseIconClickListener {
            Toast.makeText(
                    context,
                    "Close is Clicked",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }
}