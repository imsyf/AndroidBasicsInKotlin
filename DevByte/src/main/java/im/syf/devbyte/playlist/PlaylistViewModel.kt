package im.syf.devbyte.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import im.syf.devbyte.data.network.DevByteService
import im.syf.devbyte.data.network.video.VideoDto
import im.syf.devbyte.data.network.video.toDevByteVideo
import java.io.IOException
import kotlinx.coroutines.launch

class PlaylistViewModel(
    private val service: DevByteService,
) : ViewModel() {

    private val _playlist = MutableLiveData<List<DevByteVideo>>()
    val playlist: LiveData<List<DevByteVideo>> = _playlist

    private val _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean> = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean> = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
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
    private fun refreshDataFromNetwork() {
        viewModelScope.launch {
            try {
                val videos = service.getPlaylist().videos.map(VideoDto::toDevByteVideo)
                _playlist.postValue(videos)

                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (networkError: IOException) {
                _eventNetworkError.value = true
            }
        }
    }
}
