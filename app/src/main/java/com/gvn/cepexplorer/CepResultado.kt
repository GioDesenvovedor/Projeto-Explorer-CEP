package com.gvn.cepexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gvn.cepexplorer.databinding.ActivityCepResultadoBinding

class CepResultado : AppCompatActivity() {

    private lateinit var binding: ActivityCepResultadoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCepResultadoBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}