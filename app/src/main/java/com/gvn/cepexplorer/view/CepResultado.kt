package com.gvn.cepexplorer.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gvn.cepexplorer.databinding.ActivityCepResultadoBinding
import com.gvn.cepexplorer.model.SaveCep
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CepResultado : AppCompatActivity() {

    private lateinit var binding: ActivityCepResultadoBinding

    private lateinit var saveCep: SaveCep
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCepResultadoBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //esconder actionBar
        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#52057B")

        saveCep = SaveCep(this)





        val extras = intent.extras
        binding.txtCep.text = extras?.getString("cep")
        binding.txtEndereco.text = extras?.getString("logradouro")
        binding.txtBairro.text = extras?.getString("bairro")
        binding.txtCidade.text = extras?.getString("cidade")
        binding.txtUf.text = extras?.getString("uf")

        saveCep()

    }
    private fun saveCep(){


        val cardResultado = binding.card
        val cep = binding.txtCep.text.toString()
        val endereco = binding.txtEndereco.text.toString()
        val bairro = binding.txtBairro.text.toString()
        val cidade = binding.txtCidade.text.toString()
        val uf = binding.txtUf.text.toString()



        btnCompartilhar(endereco, bairro, cidade, uf,cep)
        btnSalvar()

    }

    private fun btnSalvar() {
        binding.btnSave.setOnClickListener {
            val cep = binding.txtCep.text.toString()
            val endereco = binding.txtEndereco.text.toString()
            val bairro = binding.txtBairro.text.toString()
            val cidade = binding.txtCidade.text.toString()
            val uf = binding.txtUf.text.toString()

            saveCep.saveCep(cep, endereco, uf, bairro, cidade)
            CoroutineScope(Dispatchers.IO).launch {

                val intent = Intent(applicationContext, CepSalvo::class.java)
                startActivity(intent)
                finish()
            }
        }
    }


    private fun btnCompartilhar(endereco: String,bairro: String,cidade: String, uf: String, cep: String) {
       binding.btnCompartilhar.setOnClickListener {
           CoroutineScope(Dispatchers.IO).launch {

               compartilharCep(endereco, bairro, cidade, uf, cep)
           }

       }
    }


    private fun compartilharCep( logradouro: String, bairro: String, cidade: String, uf: String, cep: String,){

        val menssagem = " Endereço: $logradouro - Bairro: $bairro - Cidade: $cidade - $uf - Cep: $cep "

        val intentss = Intent(Intent.ACTION_SEND)
        intentss.type = "text/plain"
        intentss.putExtra(Intent.EXTRA_TEXT, menssagem)

        val compartilharIntent = Intent.createChooser(intentss, null)
        startActivity(compartilharIntent)
    }

}