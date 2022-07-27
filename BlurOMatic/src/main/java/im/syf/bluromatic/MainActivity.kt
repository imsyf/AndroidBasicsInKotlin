package im.syf.bluromatic

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import im.syf.bluromatic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: BlurViewModel by viewModels {
        BlurViewModel.Factory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goButton.setOnClickListener { viewModel.applyBlur(blurLevel) }
        binding.cancelButton.setOnClickListener { viewModel.cancelWork() }

        viewModel.outputWorkInfos.observe(this) {
            // If there are no matching work info, do nothing
            if (it.isNullOrEmpty()) return@observe

            // We only care about the one output status.
            // Every continuation has only one worker tagged TAG_OUTPUT
            val workInfo = it[0]

            if (workInfo.state.isFinished) {
                showWorkFinished()

                // Normally this processing, which is not directly related to drawing views on
                // screen would be in the ViewModel. For simplicity we are keeping it here.
                val outputImageUri = workInfo.outputData.getString(KEY_IMAGE_URI)

                // If there is an output file show "See File" button
                if (!outputImageUri.isNullOrEmpty()) {
                    viewModel.setOutputUri(outputImageUri)
                    binding.seeFileButton.visibility = View.VISIBLE
                }
            }
            else {
                showWorkInProgress()
            }
        }

        // Setup view output image file button
        binding.seeFileButton.setOnClickListener {
            viewModel.outputUri?.let { currentUri ->
                val actionView = Intent(Intent.ACTION_VIEW, currentUri)
                actionView.resolveActivity(packageManager)?.run {
                    startActivity(actionView)
                }
            }
        }
    }

    /**
     * Shows and hides views for when the Activity is processing an image
     */
    private fun showWorkInProgress() = with(binding) {
        progressBar.visibility = View.VISIBLE
        cancelButton.visibility = View.VISIBLE
        goButton.visibility = View.GONE
        seeFileButton.visibility = View.GONE
    }

    /**
     * Shows and hides views for when the Activity is done processing an image
     */
    private fun showWorkFinished() = with(binding) {
        progressBar.visibility = View.GONE
        cancelButton.visibility = View.GONE
        goButton.visibility = View.VISIBLE
    }

    private val blurLevel: Int
        get() = when (binding.radioBlurGroup.checkedRadioButtonId) {
            R.id.radio_blur_lv_1 -> 1
            R.id.radio_blur_lv_2 -> 2
            R.id.radio_blur_lv_3 -> 3
            else -> 1
        }
}
