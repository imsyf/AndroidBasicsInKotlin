package im.syf.sports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import im.syf.sports.model.Sport

class SportsViewModel : ViewModel() {

    private val _sportsData = Sport.SAMPLES.toMutableList()
    val sportsData: List<Sport> = _sportsData

    private var _currentSport = MutableLiveData(_sportsData.first())
    val currentSport: LiveData<Sport> = _currentSport

    fun updateCurrentSport(sport: Sport) {
        _currentSport.value = sport
    }
}
