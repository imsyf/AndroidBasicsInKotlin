package im.syf.devbyte.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import im.syf.devbyte.data.DevByteRepository

class PlaylistViewModelFactory(
    private val repository: DevByteRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlaylistViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlaylistViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
