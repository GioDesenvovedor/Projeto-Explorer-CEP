package com.gvn.cepexplorer.dataSource

import com.gvn.cepexplorer.model.Endereco
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoAPI {
    @GET("ws/{cep}/json/") // Data class Endereço crida como json da API
    suspend fun getEndereco(
       @Path ("cep") cep: String
    ): Response<Endereco>
}