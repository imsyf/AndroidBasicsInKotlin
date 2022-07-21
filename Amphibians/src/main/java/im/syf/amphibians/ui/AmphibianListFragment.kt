package im.syf.amphibians.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import im.syf.amphibians.databinding.FragmentAmphibianListBinding

class AmphibianListFragment : Fragment() {

    private val amphibianViewModel: AmphibianViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAmphibianListBinding.inflate(inflater)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            viewModel = amphibianViewModel

            recyclerView.adapter = AmphibianListAdapter {
                amphibianViewModel.onAmphibianClicked(it)
                findNavController().navigate(
                    AmphibianListFragmentDirections.toAmphibianDetailFragment()
                )
            }
        }

        return binding.root
    }
}
