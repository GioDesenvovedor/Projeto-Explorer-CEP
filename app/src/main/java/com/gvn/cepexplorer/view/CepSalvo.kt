package com.gvn.cepexplorer.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gvn.cepexplorer.adapter.CepAdapter
import com.gvn.cepexplorer.databinding.ActivityCepSalvoBinding
import com.gvn.cepexplorer.model.SaveCep

class CepSalvo : AppCompatActivity() {

    private lateinit var binding: ActivityCepSalvoBinding
    private lateinit var saveCep: SaveCep
    private lateinit var cepAdapter: CepAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCepSalvoBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //esconder actionBar
        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#52057B")

        saveCep = SaveCep(this)
        initAdapter()

       infoSalvas()
    }

    fun initAdapter(){

        cepAdapter = CepAdapter()
       binding.recyclerCep.layoutManager = LinearLayoutManager(this)
        binding.recyclerCep.adapter = cepAdapter
    }

    private fun infoSalvas(){

        val cepList = saveCep.getListCepSalvo()
        cepAdapter.list(cepList)
    }


}