package com.shakti.moyal.data.repository

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TruecallerApi {

    @GET("{year}/{month}/{date}/{title}")
    fun getBlogPostData(@Path(value = "year", encoded = true)year: String, @Path(value = "month", encoded = true)month: String, @Path(value = "date", encoded = true) date: String, @Path(value = "title", encoded = true) title: String): Single<String>
}