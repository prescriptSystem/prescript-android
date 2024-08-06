package br.pucpr.appdev.prescript.ui.medicinelist

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.pucpr.appdev.prescript.R
import br.pucpr.appdev.prescript.data.db.entity.MedicineEntity
import br.pucpr.appdev.prescript.databinding.FragmentMedicineListBinding
import br.pucpr.appdev.prescript.databinding.MedicineItemBinding

class MedicineListAdapter(
    private val medicines: List<MedicineEntity>
) : RecyclerView.Adapter<MedicineListAdapter.MedicineListViewHolder>() {


    var onItemClick: ((entity: MedicineEntity) -> Unit )? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineListViewHolder {
        val view = LayoutInflater.from(parent.context)/*.inflate(R.layout.medicine_item, parent, false)*/
        val binding =  MedicineItemBinding.inflate(view, parent, false)


        return MedicineListViewHolder(binding)

    }

    override fun getItemCount() = medicines.size

    override fun onBindViewHolder(holder: MedicineListViewHolder, position: Int) {
        holder.bindView(medicines[position])
    }

    inner class MedicineListViewHolder(itemView: MedicineItemBinding) :RecyclerView.ViewHolder(itemView.root)
    {

        //val binding = MedicineItemBinding.inflate(layoutInflater)
        /*private val textViewMedicineImage: ImageView = itemView.ivImageMedicine*/
        private val textViewMedicineName: TextView = itemView.tvNameMedicineValue
        private val textViewMedicineLabName: TextView = itemView.tvNameLabValue
        private val textViewMedicineActivePrinciple: TextView = itemView.tvActivePrincipleValue
        private val textViewMedicineQuantity: TextView = itemView.tvQuantityValue

        fun bindView(medicine: MedicineEntity)
        {



            /*textViewMedicineImage.setImageURI(Uri.parse(medicine.imageMedicine))*/
            textViewMedicineName.text = medicine.nameMedicine
            textViewMedicineLabName.text = medicine.labName
            textViewMedicineActivePrinciple.text = medicine.activePrinciple
            textViewMedicineQuantity.text = medicine.quantity

            itemView.setOnClickListener{

                onItemClick?.invoke(medicine)

            }
        }
    }

}