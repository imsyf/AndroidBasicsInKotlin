package im.syf.waterme.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import im.syf.waterme.R
import im.syf.waterme.viewmodel.PlantViewModel
import java.util.concurrent.TimeUnit

class ReminderDialogFragment(private val plantName: String) : DialogFragment() {

    private val viewModel: PlantViewModel by viewModels {
        PlantViewModel.Factory(requireActivity().application)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (activity != null) {
            val builder = AlertDialog.Builder(activity)
                .setTitle(R.string.water_reminder)
                .setItems(R.array.water_schedule_array) { _, position ->
                    when (position) {
                        0 -> viewModel.scheduleReminder(5, TimeUnit.SECONDS, plantName)
                        1 -> viewModel.scheduleReminder(1, TimeUnit.DAYS, plantName)
                        2 -> viewModel.scheduleReminder(7, TimeUnit.DAYS, plantName)
                        3 -> viewModel.scheduleReminder(30, TimeUnit.DAYS, plantName)
                    }
                }

            return builder.create()
        } else {
            throw IllegalStateException("Activity cannot be null")
        }
    }
}
