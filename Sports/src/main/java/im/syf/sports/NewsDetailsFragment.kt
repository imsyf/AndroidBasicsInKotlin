package im.syf.sports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import im.syf.sports.databinding.FragmentNewsDetailsBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewsDetailsFragment : Fragment() {

    private val sportsViewModel: SportsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentNewsDetailsBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentNewsDetailsBinding.bind(view)

        /**
         * Attach an observer on the currentSport to update the UI automatically
         * when the data changes.
         */
        sportsViewModel.currentSport.observe(viewLifecycleOwner) {
            binding.titleDetail.setText(it.titleResourceId)
            binding.newsDetail.setText(it.newsDetails)
            binding.sportsImageDetail.load(it.sportsImageBanner)
        }
    }
}
