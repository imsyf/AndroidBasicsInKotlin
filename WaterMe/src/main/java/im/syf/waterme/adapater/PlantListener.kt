package im.syf.waterme.adapater

import im.syf.waterme.model.Plant

fun interface PlantListener {
    fun onLongClick(plant: Plant): Boolean
}
