package im.syf.inventory

import androidx.lifecycle.ViewModel
import im.syf.inventory.data.ItemDao

class InventoryViewModel(
    private val itemDao: ItemDao,
) : ViewModel() {

}
