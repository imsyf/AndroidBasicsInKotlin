package im.syf.devbyte.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import im.syf.devbyte.data.network.DevByteService

class PlaylistViewModelFactory(
    private val service: DevByteService,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlaylistViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlaylistViewModel(service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
