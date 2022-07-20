package im.syf.marsphotos.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import im.syf.marsphotos.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private val overviewViewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOverviewBinding.inflate(inflater)

        with(binding) {
            // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
            lifecycleOwner = viewLifecycleOwner

            // Giving the binding access to the OverviewViewModel
            viewModel = overviewViewModel
        }

        return binding.root
    }
}
