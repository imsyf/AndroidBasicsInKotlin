package im.syf.dogglers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import im.syf.dogglers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            verticalBtn.setOnClickListener { launch(VerticalListActivity::class.java) }
            horizontalBtn.setOnClickListener { launch(HorizontalListActivity::class.java) }
            gridBtn.setOnClickListener { launch(GridListActivity::class.java) }
        }
    }

    private fun <T : AppCompatActivity> launch(cls: Class<T>) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }
}
