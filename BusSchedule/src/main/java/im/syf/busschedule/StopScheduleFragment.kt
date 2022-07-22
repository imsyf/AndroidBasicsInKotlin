package im.syf.busschedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import im.syf.busschedule.databinding.StopScheduleFragmentBinding
import im.syf.busschedule.viewmodels.BusScheduleViewModel
import im.syf.busschedule.viewmodels.BusScheduleViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StopScheduleFragment : Fragment() {

    companion object {
        var STOP_NAME = "stopName"
    }

    private var _binding: StopScheduleFragmentBinding? = null
    private val binding get() = _binding!!

    private val busScheduleViewModel: BusScheduleViewModel by activityViewModels {
        val busScheduleApplication = activity?.application as BusScheduleApplication
        BusScheduleViewModelFactory(busScheduleApplication.database.scheduleDao())
    }

    private lateinit var stopName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            stopName = it.getString(STOP_NAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = StopScheduleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BusStopAdapter()
        binding.recyclerView.adapter = adapter

        // submitList() is a call that accesses the database. To prevent the
        // call from potentially locking the UI, you should use a
        // coroutine scope to launch the function. Using GlobalScope is not
        // best practice, and in the next step we'll see how to improve this.
        GlobalScope.launch(Dispatchers.IO) {
            adapter.submitList(busScheduleViewModel.scheduleForStopName(stopName))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
