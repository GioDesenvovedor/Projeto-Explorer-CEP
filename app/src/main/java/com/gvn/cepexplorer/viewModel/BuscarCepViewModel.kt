package com.gvn.cepexplorer.viewModel

import com.gvn.cepexplorer.repository.CepRepository

class BuscarCepViewModel {
    private lateinit var repository: CepRepository

    suspend  fun cepBuscado(cep: String){
        var cepdigitado = cep
        repository.recuperarEndereco(cepdigitado)
    }
}