package im.syf.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import im.syf.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val input = binding.costOfService.text.toString()
        val cost = input.toDouble()
        val percentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty -> 0.2
            R.id.option_eighteen -> 0.18
            else -> 0.15
        }

        var tip = cost * percentage
        if (binding.roundUpSwitch.isChecked) {
            tip = ceil(tip)
        }

        val formatted = NumberFormat.getCurrencyInstance(Locale.US).format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formatted)
    }
}
