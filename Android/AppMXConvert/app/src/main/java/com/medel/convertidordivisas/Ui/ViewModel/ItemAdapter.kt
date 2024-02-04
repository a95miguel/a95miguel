package com.medel.convertidordivisas.Data.Ui.ViewModel

import android.content.Context
import com.medel.convertidordivisas.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.medel.convertidordivisas.Data.Model.SpinnerData

class ItemAdapter(ctx: Context, pais: ArrayList<SpinnerData>) : ArrayAdapter<SpinnerData>(ctx, 0, pais) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent);
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent);
    }

    fun createItemView(position: Int, recycledView: View?, parent: ViewGroup): View {
        val pais = getItem(position)

        val view = recycledView ?: LayoutInflater.from(context).inflate(
            R.layout.spinner_iitem,
            parent,
            false
        )
        pais?.let {
            view.findViewById<ImageView>(R.id.imageIcon).setImageResource(pais.image)
            view.findViewById<TextView>(R.id.tv).setText(pais.name)
        }
        return view
    }
}