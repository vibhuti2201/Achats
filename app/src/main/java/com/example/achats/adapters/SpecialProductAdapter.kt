package com.example.achats.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.achats.data.Product
import com.example.achats.databinding.SpecialRvItemBinding

class SpecialProductAdapter:RecyclerView.Adapter<SpecialProductAdapter.SpecialProductViewHolder>() {

    inner class SpecialProductViewHolder(private val binding:SpecialRvItemBinding):
        RecyclerView.ViewHolder(binding.root){

            fun bind(product: Product){
             binding.apply {
                 Glide.with(itemView).load(product.images[0]).into(imgSpecialRvItem)
                 tvSpecialProductName.text= product.name
                 tvSpecialProductPrice.text=product.price.toString()
             }
            }
        }

    val diffCallback = object : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem==newItem
        }

    }

    val differ= AsyncListDiffer(this,diffCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialProductViewHolder {
        return SpecialProductViewHolder(
            SpecialRvItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,null
            )
        )
    }

    override fun getItemCount(): Int {
       return  differ.currentList.size
    }

    override fun onBindViewHolder(holder: SpecialProductViewHolder, position: Int) {
        val product= differ.currentList[position]
        holder.bind(product)
    }


}