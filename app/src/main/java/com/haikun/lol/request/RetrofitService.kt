package com.haikun.lol.request

import com.haikun.lol.data.AgResponse
import com.haikun.lol.data.responseentity.Content
import com.haikun.lol.data.responseentity.User
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("getUser")
    suspend fun getUser(@Query("page") page: Int): AgResponse<List<User>>

    @GET("getContent")
    suspend fun getContentByTitle(@Query("page") page: Int, @Query("title") title: String): AgResponse<List<Content>>
}