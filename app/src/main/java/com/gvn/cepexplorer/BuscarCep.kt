package com.gvn.cepexplorer

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
import com.gvn.cepexplorer.viewModel.BuscarCepViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.create

class BuscarCep : AppCompatActivity() {
    private lateinit var binding: ActivityBuscarCepBinding
    private val retrofit by lazy { RetrofitHelper.retrofit }
    private lateinit var viewModel: BuscarCepViewModel

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
            tratamenoErros()
        }
    }

    private fun tratamenoErros() {

        CoroutineScope(Dispatchers.IO).launch {

            var result = binding.editInput.text.toString().trim()

            if (result.length < 8) {

                withContext(Dispatchers.Main) {


                    Toast.makeText(
                        applicationContext,
                        "Digite o CEP corretamente, verifique se todos os números foram digitados. ",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            } else {

                recuperarEndereco()
            }
        }

    }

    private suspend fun recuperarEndereco() {


        var retorno: Response<Endereco>? = null
        var cepDigitado = binding.editInput.text.toString()

        try {
            val enderecoApi = retrofit.create(EnderecoAPI::class.java)
            retorno = enderecoApi.getEndereco(cepDigitado)

        } catch (e: Exception) {


            Log.i("T", "ERRO RECUPERAR $e")
        }
       if (retorno != null) {

            if (retorno.isSuccessful) {
                //chamar

                val endereco = retorno.body()

                val bundle = Bundle()
                bundle.putString("cep", endereco?.cep)
                bundle.putString("logradouro", endereco?.logradouro)
                bundle.putString("bairro", endereco?.bairro)
                bundle.putString("cidade", endereco?.uf)


                val intent = Intent(this, CepResultado::class.java)
                intent.putExtras(bundle)
                startActivity(intent)


                /*  binding.txtResult.text = endereco?.logradouro.toString()
                binding.txtResult2.text = endereco?.bairro.toString()
                binding.txtResult3.text = endereco?.uf.toString()
                if (cepDigitado != null && cepDigitado != null){
//                    val rua = binding.txtResult.text.toString().trim()
//                        endereco?.logradouro = rua

                    Log.i("Ta", "ende:  $endereco")
                }

            }*/


                //
                //verifica pelo log se der erro, apresentar o código de erro
            } else if (retorno == null) {

                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Erro: Cep Invalido", Toast.LENGTH_SHORT).show()
                    Log.i("tag", "ERRO CODE: ${retorno?.code()}")
                }
            }
        }
        retorno == null
    }
}

