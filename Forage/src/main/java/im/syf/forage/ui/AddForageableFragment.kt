package im.syf.forage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import im.syf.forage.databinding.FragmentAddForageableBinding
import im.syf.forage.model.Forageable
import im.syf.forage.ui.viewmodel.ForageableViewModel

/**
 * A fragment to enter data for a new [Forageable] or edit data for an existing [Forageable].
 * [Forageable]s can be saved or deleted from this fragment.
 */
class AddForageableFragment : Fragment() {

    private val args: AddForageableFragmentArgs by navArgs()

    private var _binding: FragmentAddForageableBinding? = null
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
        _binding = FragmentAddForageableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.id > 0) {
            // TODO: Observe a Forageable that is retrieved by id, set the forageable variable, and call the bindForageable method

            binding.deleteBtn.visibility = View.VISIBLE
            binding.deleteBtn.setOnClickListener {
                deleteForageable(forageable)
            }
        } else {
            binding.saveBtn.setOnClickListener { addForageable() }
        }
    }

    private fun bindForageable(forageable: Forageable) = with(binding) {
        nameInput.setText(forageable.name, TextView.BufferType.SPANNABLE)
        locationAddressInput.setText(forageable.address, TextView.BufferType.SPANNABLE)
        inSeasonCheckbox.isChecked = forageable.inSeason
        notesInput.setText(forageable.notes, TextView.BufferType.SPANNABLE)
        saveBtn.setOnClickListener { updateForageable() }
    }

    private fun deleteForageable(forageable: Forageable) {
        viewModel.deleteForageable(forageable)
        navigateToListFragment()
    }

    private fun addForageable() {
        if (isValidEntry()) {
            viewModel.addForageable(
                binding.nameInput.text.toString(),
                binding.locationAddressInput.text.toString(),
                binding.inSeasonCheckbox.isChecked,
                binding.notesInput.text.toString()
            )
            navigateToListFragment()
        }
    }

    private fun updateForageable() {
        if (isValidEntry()) {
            viewModel.updateForageable(
                id = args.id,
                name = binding.nameInput.text.toString(),
                address = binding.locationAddressInput.text.toString(),
                inSeason = binding.inSeasonCheckbox.isChecked,
                notes = binding.notesInput.text.toString()
            )
            navigateToListFragment()
        }
    }

    private fun isValidEntry() = viewModel.isValidEntry(
        binding.nameInput.text.toString(),
        binding.locationAddressInput.text.toString()
    )

    private fun navigateToListFragment() {
        findNavController().navigate(AddForageableFragmentDirections.toForageableListFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
