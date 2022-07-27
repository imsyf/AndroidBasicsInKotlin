package im.syf.waterme

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import im.syf.waterme.adapater.PlantAdapter
import im.syf.waterme.databinding.ActivityMainBinding
import im.syf.waterme.ui.ReminderDialogFragment
import im.syf.waterme.viewmodel.PlantViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: PlantViewModel by viewModels {
        PlantViewModel.Factory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PlantAdapter {
            val dialog = ReminderDialogFragment(it.name)
            dialog.show(supportFragmentManager, "WaterReminderDialogFragment")
            true
        }

        binding.recyclerView.adapter = adapter
        adapter.submitList(viewModel.plants)
    }
}
