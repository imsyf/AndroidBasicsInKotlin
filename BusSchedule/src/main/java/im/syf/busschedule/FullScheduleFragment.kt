package im.syf.busschedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import im.syf.busschedule.databinding.FullScheduleFragmentBinding
import im.syf.busschedule.viewmodels.BusScheduleViewModel
import im.syf.busschedule.viewmodels.BusScheduleViewModelFactory
import kotlinx.coroutines.launch

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

        // Create a new coroutine in the lifecycleScope
        viewLifecycleOwner.lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Trigger the flow and start listening for values.
                // This happens when lifecycle is STARTED and stops
                // collecting when the lifecycle is STOPPED
                busScheduleViewModel.fullSchedule().collect {
                    adapter.submitList(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
