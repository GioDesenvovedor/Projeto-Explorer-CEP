package com.gvn.cepexplorer

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gvn.cepexplorer.dataSource.EnderecoAPI
import com.gvn.cepexplorer.dataSource.RetrofitHelper
import com.gvn.cepexplorer.databinding.ActivityCepResultadoBinding
import com.gvn.cepexplorer.model.Endereco
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class CepResultado : AppCompatActivity() {

    private lateinit var binding: ActivityCepResultadoBinding
    private val retrofit by lazy { RetrofitHelper.retrofit }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCepResultadoBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //esconder actionBar
        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#52057B")

        val extras = intent.extras
        binding.txtEndereco.text = extras?.getString("logradouro")
        binding.txtBairro.text = extras?.getString("bairro")
        binding.txtCidade.text = extras?.getString("cidade")

        val cardResultado = binding.card
        val endereco = binding.txtEndereco.text.toString()
        val bairro = binding.txtBairro.text.toString()
        val cidade = binding.txtCidade.text.toString()
        btnCompartilhar(endereco, bairro, cidade)

    }


    private fun btnCompartilhar(endereco: String,bairro: String,cidade: String) {
       binding.btnCompartilhar.setOnClickListener {
           CoroutineScope(Dispatchers.IO).launch {

               compartilharCep(endereco, bairro, cidade)
           }

       }
    }


    private fun compartilharCep(logradouro: String, bairro: String, cidade: String){

        val menssagem = "Endereco: $logradouro, Bairro: $bairro, cidade: $cidade"

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, menssagem)
    }
}