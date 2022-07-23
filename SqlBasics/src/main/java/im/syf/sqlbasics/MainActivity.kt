package im.syf.sqlbasics

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            AppDatabase.getDatabase(applicationContext).californiaParkDao().getAll()
        }

        showDebugDBAddressLogToast()
    }

    private fun showDebugDBAddressLogToast() {
        if (BuildConfig.DEBUG) {
            try {
                val debugDB = Class.forName("com.amitshekhar.DebugDB")
                val getAddressLog = debugDB.getMethod("getAddressLog")
                val value = getAddressLog.invoke(null)
                Toast.makeText(this, "$value", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
            }
        }
    }
}
