package com.gvn.cepexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gvn.cepexplorer.dataSource.EnderecoAPI
import com.gvn.cepexplorer.dataSource.RetrofitHelper
import com.gvn.cepexplorer.databinding.ActivityCepResultadoBinding
import com.gvn.cepexplorer.model.Endereco
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CepResultado : AppCompatActivity() {

    private lateinit var binding: ActivityCepResultadoBinding
    private val retrofit by lazy { RetrofitHelper.retrofit }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCepResultadoBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val extras = intent.extras
        binding.txtEndereco.text = extras?.getString("logradouro")
        binding.txtBairro.text = extras?.getString("bairro")
        binding.txtCidade.text = extras?.getString("cidade")

    }


}