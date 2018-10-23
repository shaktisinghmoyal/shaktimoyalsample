package com.shakti.moyal.data

import com.shakti.moyal.data.repository.ApiRepository
import javax.inject.Inject

class DataProviderImpl @Inject constructor(private val apiRepository: ApiRepository): DataProvider {
    override fun getBlogPostData(year: String, month: String, date: String, title: String) = apiRepository.getBlogPostData(year, month, date, title)
}