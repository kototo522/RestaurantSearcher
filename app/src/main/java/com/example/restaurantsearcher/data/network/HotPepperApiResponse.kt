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
    val id: String, // id
    val name: String, // 店舗名称
    val address: String, // 住所
    val lat: Double, // 緯度
    val lng: Double, // 経度
    val access: String, // アクセス(〇〇駅から徒歩x分)
    val photo: Photo, // 画像
    val open: String, // 営業時間
)

@Serializable
data class Photo(val pc: PhotoSize, val mobile: PhotoSize)

@Serializable
data class PhotoSize(val l: String, val m: String, val s: String)
