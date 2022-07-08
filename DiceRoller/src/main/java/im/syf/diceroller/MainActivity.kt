package im.syf.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text: TextView = findViewById(R.id.text)
        val rollButton: Button = findViewById(R.id.button)
        val dice = Dice(6)

        rollButton.setOnClickListener {
            text.text = "${dice.roll()}"
        }
    }
}
