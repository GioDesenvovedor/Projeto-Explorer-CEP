package com.gvn.cepexplorer.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gvn.cepexplorer.dataSource.EnderecoAPI
import com.gvn.cepexplorer.dataSource.RetrofitHelper
import com.gvn.cepexplorer.databinding.ActivityBuscarCepBinding
import com.gvn.cepexplorer.model.Endereco
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class BuscarCep : AppCompatActivity() {

    private lateinit var binding: ActivityBuscarCepBinding
    private val retrofit by lazy { RetrofitHelper.retrofit }


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBuscarCepBinding.inflate(layoutInflater)


        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //esconder actionBar
        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#52057B")

        btnBuscarCep()
    }

    private fun btnBuscarCep() {
        binding.btnBuscar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                tratamenoErros()
            }

        }
    }

    //Realiza o tratamento de erros ao buscar o CEP
    private suspend fun tratamenoErros() {



            var result = binding.editInput.text.toString().trim()

            //Verifica se o CEP digitado tem o comprimento correto e, em caso afirmativo, chama a função
            // Caso não seja afirmativo, exibe menssagem
            if (result.length != 8) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        applicationContext,
                        "Digite o CEP corretamente, verifique se todos os números foram digitados. ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {

                //viewModel.cepBuscado(result)
                recuperarEndereco()
            }


    }

    //Metodo para chamada da API
    private suspend fun recuperarEndereco() {

        CoroutineScope(Dispatchers.IO).launch {
            try {

                var retorno: Response<Endereco>? = null
                var cepDigitado = binding.editInput.text.toString()


                val enderecoApi = retrofit.create(EnderecoAPI::class.java)
                retorno = enderecoApi.getEndereco(cepDigitado)


                if (retorno != null) {
                    if (retorno.isSuccessful) {



                        val endereco = retorno.body()
                        exibirResultado(endereco)

                    } else {
                        withContext(Dispatchers.Main){
                            Toast.makeText(
                                applicationContext,
                                "Cep digitado incorreto, verifique a quantidade de dígitos",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }

                    }

                }
            } catch (e: Exception) {
                Log.i("TAG2", "Erro ao recuperar :  ${e.message}")
                withContext(Dispatchers.Main){
                    Toast.makeText(applicationContext, "Erro ao recuperar o endereço", Toast.LENGTH_SHORT).show()

                    Log.e("TAG", "Erro ao recuperar o endereço:  $e")
                    // exibirMensagemDeErro("Erro ao recuperar o endereço.")
                }


            }
        }


    }

    private  fun exibirResultado(endereco: Endereco?) {


        //VERIFICA SE O RETORNO DO CEP VEIO COMO NULL (CEP NÃO EXISTE)
        if (endereco?.cep == null) {

            exibirMensagemDeErro()

        } else {

            val bundle = Bundle()

            bundle.putString("cep", endereco?.cep)
            bundle.putString("logradouro", endereco?.logradouro)
            bundle.putString("bairro", endereco?.bairro)
            bundle.putString("cidade", endereco?.localidade)
            bundle.putString("uf", endereco?.uf)

            val intent = Intent(this, CepResultado::class.java)
            intent.putExtras(bundle)
            startActivity(intent)


                binding.editInput.setText("")


        }
    }


    private  fun exibirMensagemDeErro() {

            Toast.makeText(
                applicationContext,
                "O CEP DIGITADO NÃO EXISTE, VERIFIQUE SE DIGITOU CORRETAMENTE ",
                Toast.LENGTH_SHORT
            ).show()

    }
}
