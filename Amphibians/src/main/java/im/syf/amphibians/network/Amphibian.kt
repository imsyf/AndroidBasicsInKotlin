package im.syf.amphibians.network

import com.squareup.moshi.JsonClass

/**
 * This data class defines an Amphibian which includes the amphibian's name, the type of
 * amphibian, and a brief description of the amphibian.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
@JsonClass(generateAdapter = true)
data class Amphibian(
    val name: String,
    val type: String,
    val description: String,
)
