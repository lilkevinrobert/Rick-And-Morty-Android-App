package xyz.siopa.retrofit_app

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import xyz.siopa.retrofit_app.dto.response.GetCharacterByIdResponse

interface RickAndMortyService {

    @GET("character/{characterId}")
    fun getCharacterById(@Path("characterId") characterId: Int):Call<GetCharacterByIdResponse>
}