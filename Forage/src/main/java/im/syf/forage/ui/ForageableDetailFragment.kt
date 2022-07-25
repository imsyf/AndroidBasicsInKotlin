package im.syf.forage.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import im.syf.forage.R
import im.syf.forage.databinding.FragmentForageableDetailBinding
import im.syf.forage.model.Forageable
import im.syf.forage.ui.viewmodel.ForageableViewModel

class ForageableDetailFragment : Fragment() {

    private val args: ForageableDetailFragmentArgs by navArgs()

    private var _binding: FragmentForageableDetailBinding? = null
    private val binding get() = _binding!!

    // TODO: Refactor the creation of the view model to take an instance of
    //  ForageableViewModelFactory. The factory should take an instance of the Database retrieved
    //  from BaseApplication
    private val viewModel: ForageableViewModel by activityViewModels()

    private lateinit var forageable: Forageable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForageableDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Observe a forageable that is retrieved by id, set the forageable variable, and call the bind forageable method

    }

    private fun bindForageable() = with(binding) {
        name.text = forageable.name
        location.text = forageable.address
        notes.text = forageable.notes

        if (forageable.inSeason) {
            season.text = getString(R.string.in_season)
        } else {
            season.text = getString(R.string.out_of_season)
        }

        editForageableFab.setOnClickListener {
            val action = ForageableDetailFragmentDirections.toAddForageableFragment(forageable.id)
            findNavController().navigate(action)
        }

        location.setOnClickListener { launchMap() }
    }

    private fun launchMap() {
        val address = forageable.address.let {
            it.replace(", ", ",")
            it.replace(". ", " ")
            it.replace(" ", "+")
        }
        val gmmIntentUri = Uri.parse("geo:0,0?q=$address")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
