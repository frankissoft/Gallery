package edu.vt.cs.cs5254.gallery.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface FlickrApi {
    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=3bb32b77fb2a6e8ab13387caefb77a55" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s, geo"
    )
    fun fetchPhotos(): Call<FlickrResponse>

    @GET
    fun fetchUrlBytes(@Url url: String): Call<ResponseBody>
}