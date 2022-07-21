package im.syf.amphibians.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import im.syf.amphibians.network.Amphibian
import im.syf.amphibians.network.AmphibianApi
import kotlinx.coroutines.launch

enum class AmphibianApiStatus { LOADING, ERROR, DONE }

class AmphibianViewModel : ViewModel() {

    // TODO: Create properties to represent MutableLiveData and LiveData for the API status
    private val _status = MutableLiveData<AmphibianApiStatus>()
    val status: LiveData<AmphibianApiStatus> = _status

    // TODO: Create properties to represent MutableLiveData and LiveData for a list of amphibian objects
    private val _amphibians = MutableLiveData<List<Amphibian>>()
    val amphibians: LiveData<List<Amphibian>> = _amphibians

    // TODO: Create properties to represent MutableLiveData and LiveData for a single amphibian object.
    //  This will be used to display the details of an amphibian when a list item is clicked

    init {
        getAmphibianList()
    }

    // TODO: Create a function that gets a list of amphibians from the api service and sets the
    //  status via a Coroutine
    private fun getAmphibianList() {
        viewModelScope.launch {
            _status.value = AmphibianApiStatus.LOADING
            try {
                _amphibians.value = AmphibianApi.retrofitService.getAmphibians()
                _status.value = AmphibianApiStatus.DONE
            } catch (e: Exception) {
                _status.value = AmphibianApiStatus.ERROR
                _amphibians.value = emptyList()
            }
        }
    }

    fun onAmphibianClicked(amphibian: Amphibian) {
        // TODO: Set the amphibian object
    }
}
