package com.gvn.cepexplorer.repository

import android.util.Log
import com.gvn.cepexplorer.dataSource.EnderecoAPI
import com.gvn.cepexplorer.dataSource.RetrofitHelper
import com.gvn.cepexplorer.model.Endereco
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CepRepository {

    private val retrofit by lazy { RetrofitHelper.retrofit }


     suspend fun recuperarEndereco(cepDigitado: String) {

        var retorno: Response<Endereco>? = null

       // var cepDigitado = binding.editInput.text.toString()
        try {
            val enderecoApi = retrofit.create(EnderecoAPI::class.java)
            retorno = enderecoApi.getEndereco(cepDigitado)

        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("T", "ERRO RECUPERAR")
        }
        if (retorno != null){

            if (retorno.isSuccessful){
                //chamar
                val endereco = retorno.body()

              //  binding.txtResult.text = endereco?.logradouro.toString()
              //  binding.txtResult2.text = endereco?.bairro.toString()
              //  binding.txtResult3.text = endereco?.uf.toString()
              //  if (cepDigitado != null && cepDigitado != null){
//                    val rua = binding.txtResult.text.toString().trim()
//                        endereco?.logradouro = rua

                    Log.i("Ta", "ende:  $endereco")
                }

            }



            //
            //verifica pelo log se der erro, apresentar o código de erro
       /* }else{
            withContext(Dispatchers.Main){
                Log.i("tag","ERRO CODE: ${retorno?.code()}")
            }
        }*/
    }
}