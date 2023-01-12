package xyz.siopa.retrofit_app.dto.response

import com.squareup.moshi.Json

data class GetCharacterByIdResponse(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    @Json(name="full_name")
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) {
    data class Location(
        val name: String,
        val url: String
    )

    data class Origin(
        val name: String,
        val url: String
    )
}