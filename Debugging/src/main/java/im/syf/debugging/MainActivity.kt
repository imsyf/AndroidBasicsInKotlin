package im.syf.debugging

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        division()
    }

    private fun division() {
        val numerator = 60
        var denominator = 4
        repeat(4) {
            Log.v(TAG, "${numerator / denominator}")
            denominator--
        }
    }
}
