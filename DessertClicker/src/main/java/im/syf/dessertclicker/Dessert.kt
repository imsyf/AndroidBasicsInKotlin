package im.syf.dessertclicker

import androidx.annotation.DrawableRes

/**
 * Simple data class that represents a dessert. Includes the resource id integer associated with
 * the image, the price it's sold for, and the [startProductionAmount], which determines when
 * the dessert starts to be produced.
 */
data class Dessert(
    @DrawableRes val imageId: Int,
    val price: Int,
    val startProductionAmount: Int,
)
