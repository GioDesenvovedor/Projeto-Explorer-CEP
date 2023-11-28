package com.gvn.cepexplorer.model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SaveCep (context: Context) {

    //  Criar uma instância do SharedPreferences para o aplicativo com o modo privado.
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "exploreCep", Context.MODE_PRIVATE)

    //  Função para salvar informações do CEP no SharedPreferences.
    fun saveCep(cep: String, endereco: String, bairro: String, cidade: String, uf: String){

        val listSave = getListCepSalvo().toMutableList()

        listSave.add(mapOf(
            "cep" to cep, "endereco" to endereco,
            "bairro" to bairro, "cidade" to cidade, "uf" to uf
        ))
        sharedPreferences.edit().putString("cepList", Gson().toJson(listSave)).apply()
    }

    // Função para recuperar a lista de conjuntos de dados salvos no SharedPreference
    fun getListCepSalvo(): List<Map<String, String?>>{
        val jsonList = sharedPreferences.getString("cepList", "[]")?:"[]"
        val type = object : TypeToken<List<Map<String, String?>>>(){}.type
        return Gson().fromJson(jsonList, type)
    }


}