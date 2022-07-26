package im.syf.forage.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import im.syf.forage.data.ForageableDao
import im.syf.forage.model.Forageable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Shared [ViewModel] to provide data to the [ForageableListFragment], [ForageableDetailFragment],
 * and [AddForageableFragment] and allow for interaction the the [ForageableDao]
 */
class ForageableViewModel(private val dao: ForageableDao) : ViewModel() {

    val forageables: LiveData<List<Forageable>> = dao.getForageables().asLiveData()

    fun getForageable(id: Long): LiveData<Forageable> = dao.getForageable(id).asLiveData()

    fun addForageable(name: String, address: String, inSeason: Boolean, notes: String) {
        val forageable = Forageable(
            name = name,
            address = address,
            inSeason = inSeason,
            notes = notes
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(forageable)
        }
    }

    fun updateForageable(
        id: Long,
        name: String,
        address: String,
        inSeason: Boolean,
        notes: String
    ) {
        val forageable = Forageable(
            id = id,
            name = name,
            address = address,
            inSeason = inSeason,
            notes = notes
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(forageable)
        }
    }

    fun deleteForageable(forageable: Forageable) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(forageable)
        }
    }

    fun isValidEntry(name: String, address: String): Boolean {
        return name.isNotBlank() && address.isNotBlank()
    }

    class Factory(private val dao: ForageableDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ForageableViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ForageableViewModel(dao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
