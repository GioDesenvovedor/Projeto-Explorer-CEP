package com.gvn.cepexplorer.model

data class Endereco(
    val cep: String,
    var logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val ibge: Int,
    val gia: Int,
    val ddd: Int,
    val siafi: Int,
)