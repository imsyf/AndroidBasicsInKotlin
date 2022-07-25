package im.syf.forage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import im.syf.forage.databinding.FragmentForageableListBinding
import im.syf.forage.ui.adapter.ForageableListAdapter
import im.syf.forage.ui.viewmodel.ForageableViewModel

class ForageableListFragment : Fragment() {

    private var _binding: FragmentForageableListBinding? = null
    private val binding get() = _binding!!

    // TODO: Refactor the creation of the view model to take an instance of
    //  ForageableViewModelFactory. The factory should take an instance of the Database retrieved
    //  from BaseApplication
    private val viewModel: ForageableViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForageableListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ForageableListAdapter {
            val action = ForageableListFragmentDirections.toForageableDetailFragment(it.id)
            findNavController().navigate(action)
        }

        // TODO: observe the list of forageables from the view model and submit it the adapter

        with(binding) {
            recyclerView.adapter = adapter
            addForageableFab.setOnClickListener {
                val action = ForageableListFragmentDirections.toAddForageableFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
