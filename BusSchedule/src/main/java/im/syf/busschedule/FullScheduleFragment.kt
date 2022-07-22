package im.syf.busschedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import im.syf.busschedule.databinding.FullScheduleFragmentBinding
import im.syf.busschedule.viewmodels.BusScheduleViewModel
import im.syf.busschedule.viewmodels.BusScheduleViewModelFactory

class FullScheduleFragment : Fragment() {

    private var _binding: FullScheduleFragmentBinding? = null
    private val binding get() = _binding!!

    private val busScheduleViewModel: BusScheduleViewModel by activityViewModels {
        val busScheduleApplication = activity?.application as BusScheduleApplication
        BusScheduleViewModelFactory(busScheduleApplication.database.scheduleDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FullScheduleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BusStopAdapter {
            val action = FullScheduleFragmentDirections.toStopScheduleFragment(it.stopName)
            findNavController().navigate(action)
        }

        binding.recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            val list = busScheduleViewModel.fullSchedule()  // main thread safe
            adapter.submitList(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
