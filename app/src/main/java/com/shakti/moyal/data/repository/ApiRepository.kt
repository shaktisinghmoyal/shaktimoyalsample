package com.shakti.moyal.data.repository

import io.reactivex.Single

interface ApiRepository
{
    fun getBlogPostData(year: String, month: String, date: String, title:String): Single<String>
}