package im.syf.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image: ImageView = findViewById(R.id.image)
        val rollButton: Button = findViewById(R.id.button)
        val dice = Dice(6)

        rollButton.setOnClickListener {
            val side = dice.roll()
            val id = when (side) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }

            image.setImageResource(id)
            image.contentDescription = getString(R.string.dice_side, side)
        }
    }
}
