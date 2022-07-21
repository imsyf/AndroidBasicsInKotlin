package im.syf.amphibians.ui

import im.syf.amphibians.network.Amphibian

fun interface AmphibianListener {
    fun onClick(amphibian: Amphibian)
}
