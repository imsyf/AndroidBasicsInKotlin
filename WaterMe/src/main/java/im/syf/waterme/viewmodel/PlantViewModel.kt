package im.syf.waterme.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import im.syf.waterme.data.DataSource
import java.util.concurrent.TimeUnit

class PlantViewModel(application: Application) : ViewModel() {

    val plants = DataSource.plants

    private val workManager = WorkManager.getInstance(application)

    internal fun scheduleReminder(
        duration: Long,
        unit: TimeUnit,
        plantName: String
    ) {
        // TODO: create a Data instance with the plantName passed to it

        // TODO: Generate a OneTimeWorkRequest with the passed in duration, time unit, and data
        //  instance

        // TODO: Enqueue the request as a unique work request
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PlantViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PlantViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
