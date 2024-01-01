import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HotPepperApiResponse(
    val results: Results,
)

@Serializable
data class Results(
    @SerialName("shop") val shop: List<Shop>,
)

@Serializable
data class Shop(
    val id: String,
    val name: String,
    val address: String,
    val lat: Double,
    val lng: Double,
    val access: String,
)

@Serializable
data class Urls(val pc: String)

@Serializable
data class Photo(val pc: PhotoSize, val mobile: PhotoSize)

@Serializable
data class PhotoSize(val l: String, val m: String, val s: String)
