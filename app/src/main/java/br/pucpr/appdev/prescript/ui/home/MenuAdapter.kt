package br.pucpr.appdev.prescript.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.view.menu.MenuView.ItemView
import br.pucpr.appdev.prescript.databinding.FragmentHomeBinding
import br.pucpr.appdev.prescript.databinding.FragmentMedicineBinding
import br.pucpr.appdev.prescript.databinding.MedicineItemBinding
import br.pucpr.appdev.prescript.databinding.MenuItemBinding
import br.pucpr.appdev.prescript.model.MenuItem

class MenuAdapter(private val context: Context, private val itens: List<MenuItem> ) : BaseAdapter() {



    override fun getCount(): Int {
        return itens.size
    }

    override fun getItem(position: Int): Any {
        return itens[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View?
    {

        val view = LayoutInflater.from(context)/*.inflate(R.layout.medicine_item, parent, false)*/
        val binding =  MenuItemBinding.inflate(view, parent, false)

        val currentItem = getItem(position) as MenuItem

        val textViewItemName = binding.text
        val imageViewIcon = binding.imagemview

        textViewItemName.text = currentItem.texto
        imageViewIcon.setImageDrawable(currentItem.icone)

        return binding.root


    }
}