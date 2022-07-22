package im.syf.busschedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import im.syf.busschedule.databinding.FullScheduleFragmentBinding
import im.syf.busschedule.viewmodels.BusScheduleViewModel
import im.syf.busschedule.viewmodels.BusScheduleViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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

        // submitList() is a call that accesses the database. To prevent the
        // call from potentially locking the UI, you should use a
        // coroutine scope to launch the function. Using GlobalScope is not
        // best practice, and in the next step we'll see how to improve this.
        GlobalScope.launch(Dispatchers.IO) {
            adapter.submitList(busScheduleViewModel.fullSchedule())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
