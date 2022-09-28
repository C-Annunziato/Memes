package com.example.memes

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.memes.databinding.RecyclerRowBinding

class MemesAdapter(private val onClickListener: OnClickListener) :
    androidx.recyclerview.widget.ListAdapter<Meme, MemesAdapter.MyViewHolder>(MyDiffUtil) {

    companion object MyDiffUtil : DiffUtil.ItemCallback<Meme>() {

        override fun areItemsTheSame(oldItem: Meme, newItem: Meme): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Meme, newItem: Meme): Boolean {
            return oldItem.id == newItem.id
        }
    }

     class MyViewHolder(private val binding: RecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meme: Meme?) {
            Glide.with(binding.imageView).load(meme?.url).into(binding.imageView)
            binding.memeName.text = meme?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RecyclerRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val meme = getItem(position)
            holder.itemView.setOnClickListener{
                onClickListener.onClick(meme)
            }
        holder.bind(meme)
    }


class OnClickListener(val clickListener: (meme: Meme) -> Unit) {
    fun onClick(meme: Meme) = clickListener(meme)
}



}