package im.syf.sports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import im.syf.sports.databinding.FragmentSportsListBinding

class SportsListFragment : Fragment() {

    private val sportsViewModel: SportsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSportsListBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSportsListBinding.bind(view)

        // Initialize the adapter and set it to the RecyclerView.
        val adapter = SportsAdapter {

            // Update the user selected sport as the current sport in the shared viewmodel
            // This will automatically update the dual pane content
            sportsViewModel.updateCurrentSport(it)

            // Navigate to the details screen
            findNavController().navigate(SportsListFragmentDirections.toNewsDetailsFragment())
        }

        binding.recyclerView.adapter = adapter
        adapter.submitList(sportsViewModel.sportsData)
    }
}
