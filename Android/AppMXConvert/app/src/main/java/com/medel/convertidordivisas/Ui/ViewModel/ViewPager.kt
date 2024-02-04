package com.medel.convertidordivisas.Data.Ui.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.medel.convertidordivisas.Model.Intro
import com.medel.convertidordivisas.R
import com.medel.convertidordivisas.databinding.BoardItemBinding

class ViewPager(private val introList: List<Intro>,private val onItemSelected : OnItemSelected? = null) :
    RecyclerView.Adapter<ViewPager.IntroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.board_item, parent, false)
        return IntroViewHolder(view,onItemSelected)
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        holder.bind(introList[position])
    }

    override fun getItemCount(): Int {
        return introList.size
    }

    inner class IntroViewHolder(
        view: View,
        private val onItemSelected : OnItemSelected? = null
    ) : RecyclerView.ViewHolder(view) {
        private val binding = BoardItemBinding.bind(view)
        private val container = binding.container
        private val image = binding.imageView
        private val title = binding.tvTitulo
        private val subtitle = binding.tvSubtitulo
        fun bind(intro: Intro) = with(itemView) {
            container.background = ContextCompat.getDrawable(context, intro.background)
            image.setImageResource(intro.image)
            title.text = intro.title
            subtitle.text = intro.subtitle
            if (adapterPosition.equals(introList.size - 1)) {
                binding.btnSiguiente.text = context.getString(R.string.end)
            }
            binding.btnSiguiente.setOnClickListener {
                onItemSelected?.onClickListener(adapterPosition)
            }
        }
    }

    interface OnItemSelected{
        fun onClickListener(position: Int)
    }
}