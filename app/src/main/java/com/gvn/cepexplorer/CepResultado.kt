package com.gvn.cepexplorer

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gvn.cepexplorer.dataSource.EnderecoAPI
import com.gvn.cepexplorer.dataSource.RetrofitHelper
import com.gvn.cepexplorer.databinding.ActivityCepResultadoBinding
import com.gvn.cepexplorer.model.Endereco
import com.gvn.cepexplorer.model.SaveCep
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class CepResultado : AppCompatActivity() {

    private lateinit var binding: ActivityCepResultadoBinding
    private lateinit var saveCep: SaveCep
    private val retrofit by lazy { RetrofitHelper.retrofit }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCepResultadoBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //esconder actionBar
        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#52057B")

        val extras = intent.extras
        binding.txtCep.text = extras?.getString("cep")
        binding.txtEndereco.text = extras?.getString("logradouro")
        binding.txtBairro.text = extras?.getString("bairro")
        binding.txtCidade.text = extras?.getString("cidade")



        val cardResultado = binding.card
        val cep = binding.txtCep.text.toString()
        val endereco = binding.txtEndereco.text.toString()
        val bairro = binding.txtBairro.text.toString()
        val cidade = binding.txtCidade.text.toString()

        saveCep()

        btnCompartilhar(endereco, bairro, cidade, cep)

    }
    private fun saveCep(){


    }


    private fun btnCompartilhar(endereco: String,bairro: String,cidade: String, cep: String) {
       binding.btnCompartilhar.setOnClickListener {
           CoroutineScope(Dispatchers.IO).launch {

               compartilharCep(endereco, bairro, cidade, cep)
           }

       }
    }


    private fun compartilharCep( logradouro: String, bairro: String, cidade: String, cep: String,){

        val menssagem = " Endereco: $logradouro - Bairro: $bairro - Cidade: $cidade - Cep: $cep "

        val intentss = Intent(Intent.ACTION_SEND)
        intentss.type = "text/plain"
        intentss.putExtra(Intent.EXTRA_TEXT, menssagem)

        val compartilharIntent = Intent.createChooser(intentss, null)
        startActivity(compartilharIntent)
    }

}