package im.syf.amphibians.ui

import im.syf.amphibians.network.Amphibian

class AmphibianListener(
    val clickListener: (amphibian: Amphibian) -> Unit
) {
    fun onClick(amphibian: Amphibian) = clickListener(amphibian)
}
