package br.pucpr.appdev.prescript.ui.medicinelist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.pucpr.appdev.prescript.databinding.MedicineItemBinding
import br.pucpr.appdev.prescript.model.Medicine

class MedicineListAdapter(
    private val medicines: List<Medicine>
) : RecyclerView.Adapter<MedicineListAdapter.MedicineListViewHolder>() {


    var onItemClick: ((entity: Medicine) -> Unit )? = null

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

        fun bindView(medicine: Medicine)
        {



            /*textViewMedicineImage.setImageURI(Uri.parse(medicine.imageMedicine))*/
            textViewMedicineName.text = medicine.nameMedicine.toString()
            textViewMedicineLabName.text = medicine.labName.toString()
            textViewMedicineActivePrinciple.text = medicine.activePrinciple.toString()
            textViewMedicineQuantity.text = medicine.quantity.toString()

            itemView.setOnClickListener{

                onItemClick?.invoke(medicine)

            }
        }
    }

}