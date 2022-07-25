package im.syf.devbyte.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import im.syf.devbyte.data.DevByteRepository
import kotlinx.coroutines.launch
import java.io.IOException

class PlaylistViewModel(
    private val repository: DevByteRepository,
) : ViewModel() {

    val playlist: LiveData<List<DevByteVideo>> = repository.videos.asLiveData()

    private val _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean> = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean> = _isNetworkErrorShown

    init {
        refreshDataFromRepository()
    }

    /**
     * Resets the network error flag.
     */
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    /**
     * Refresh data from the repository. Use a coroutine launch to run in a
     * background thread.
     */
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                repository.refreshVideos()

                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (networkError: IOException) {
                _eventNetworkError.value = playlist.value.isNullOrEmpty()
            }
        }
    }
}
