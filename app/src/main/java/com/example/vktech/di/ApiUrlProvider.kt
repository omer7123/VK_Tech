package com.example.vktech.di

import javax.inject.Inject

interface ApiUrlProvider {
    var url: String
}

class ApiUrlProviderImpl @Inject constructor() : ApiUrlProvider {

    override var url =
        "https://pixabay.com/api/"
}