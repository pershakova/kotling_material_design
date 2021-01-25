package geekbarains.material.ui.picture

import geekbarains.material.ui.marspicture.MarsResponseDataPhotos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {

    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<PODServerResponseData>

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    fun getMarsPicture(@Query("api_key") apiKey: String, @Query("sol") sol: Int, @Query("camera") camera: String): Call<MarsResponseDataPhotos>

}
