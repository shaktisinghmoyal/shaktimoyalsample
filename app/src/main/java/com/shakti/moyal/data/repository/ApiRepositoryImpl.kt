package com.shakti.moyal.data.repository

import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(retrofit: Retrofit): ApiRepository
{

    private var truecallerApi: TruecallerApi? = null

    init {
        truecallerApi = retrofit.create(TruecallerApi::class.java)
    }

    override fun getBlogPostData(year: String, month: String, date: String, title:String): Single<String> {
        return truecallerApi!!.getBlogPostData(year,month,date, title)
    }
}