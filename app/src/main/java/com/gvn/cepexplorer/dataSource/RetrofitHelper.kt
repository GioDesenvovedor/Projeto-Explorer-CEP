package com.gvn.cepexplorer.dataSource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    companion object{
        var retrofit = Retrofit.Builder()
            .baseUrl("https://viacep.com.br")//Url do ViaCep
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}