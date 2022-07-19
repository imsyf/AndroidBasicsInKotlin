package im.syf.lunchtray

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import im.syf.lunchtray.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /**
     * Binding object instance corresponding to the [R.layout.activity_main] layout
     * when the view hierarchy is attached to the fragment.
     */
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Retrieve NavController from the NavHostFragment
    }
}
