package com.gvn.cepexplorer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gvn.cepexplorer.databinding.ItemCepAdapterBinding

class CepAdapter: RecyclerView.Adapter<CepAdapter.CepViewHolder>() {

    private var cepList : List<Map<String, String?>> = emptyList()

    fun list(newlist: List<Map<String, String?>>){
        cepList = newlist
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CepViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val  binding = ItemCepAdapterBinding.inflate(inflater, parent, false)
        return CepViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CepAdapter.CepViewHolder, position: Int) {
        // Obten o índice invertido na lista original
        val reverseIndex = cepList.size -1 - position

        // Acesse o item invertido na lista original
        holder.bind(cepList[reverseIndex])

    }

    override fun getItemCount(): Int = cepList.size

    class CepViewHolder(private val binding: ItemCepAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cepData: Map<String, String?>) {
            binding.txtCep.text = cepData["cep"]
            binding.txtEndereco.text = cepData["endereco"]
            binding.txtBairro.text = cepData["bairro"]
            binding.txtCidade.text = cepData["cidade"]
        }
    }

}