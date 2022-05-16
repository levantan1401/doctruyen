package com.example.doctruyen.Adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.doctruyen.FilterCategory
import com.example.doctruyen.databinding.AdminRowCategoryItemBinding
import com.example.doctruyen.firebase.fireBase_Category
import com.google.firebase.database.FirebaseDatabase

class AdminCategoryAdapter:RecyclerView.Adapter<AdminCategoryAdapter.HolderCategory>, Filterable {
    private  lateinit var binding: AdminRowCategoryItemBinding
    private val context: Context
    public var categoryArrayList: ArrayList<fireBase_Category>
    private var filterList: ArrayList<fireBase_Category>

    private var filter: FilterCategory? = null

    constructor(context: Context, categoryArrayList: ArrayList<fireBase_Category>) : super() {
        this.context = context
        this.categoryArrayList = categoryArrayList
        this.filterList = categoryArrayList
    }

    inner class HolderCategory(itemView: View): RecyclerView.ViewHolder(itemView){
        var categoryTV: TextView = binding.categoryTV
        var deleteBtn: ImageButton = binding.deleteBtn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderCategory {
        binding = AdminRowCategoryItemBinding.inflate(LayoutInflater.from(context),parent,false)

        return HolderCategory(binding.root)
    }

    override fun onBindViewHolder(holder: HolderCategory, position: Int) {
        val model = categoryArrayList[position]
        val id = model.id
        val category = model.category
        val uid = model.uid
        val timestamp = model.timestamp

        holder.categoryTV.text = category

        holder.deleteBtn.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete")
                .setMessage("Are you sure want to delete category?")
                .setPositiveButton("Confirm"){a,d->
                    Toast.makeText(context,"Deleting....",Toast.LENGTH_LONG).show()
                    deleteCategory(model, holder)

                }
                .setNegativeButton("Cancel"){a,d->
                    a.dismiss()
                }
                .show()

        }

    }

    private fun deleteCategory(model: fireBase_Category, holder: HolderCategory) {
        val id = model.id

        val ref = FirebaseDatabase.getInstance().getReference("Categories")

        ref.child(id).removeValue()
            .addOnSuccessListener{
                Toast.makeText(context,"Deleted....", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e->
                Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show()
            }


    }

    override fun getItemCount(): Int {
        return categoryArrayList.size
    }

    override fun getFilter(): Filter {

        if(filter == null ){
            filter = FilterCategory(filterList,this)
        }
        return filter as FilterCategory
    }
}