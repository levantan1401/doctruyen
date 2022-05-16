package com.example.doctruyen.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doctruyen.Model.BestSellerData
import com.example.doctruyen.R

class BestSellerAdapter(val context: Context?, val bestSellerData: List<BestSellerData>) : RecyclerView.Adapter<BestSellerAdapter.ViewHolder>(){

    private lateinit var adapter: Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.best_seller_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BestSellerAdapter.ViewHolder, position: Int) {
        holder.bestSellerImg.setImageResource(bestSellerData[position].imgCategory)
        holder.bestSellerName.text = bestSellerData[position].nameCategory
        holder.best_auditor_name.text = bestSellerData[position].nameAuditor
        holder.bestSellerViews.text = "( ${bestSellerData[position].views.toString() +')'}"
        holder.bestSellerDesc.text = "${bestSellerData[position].descBestSeller }" + "..."

    }

    override fun getItemCount(): Int {
        return bestSellerData.size
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val bestSellerImg = view.findViewById<ImageView>(R.id.img_author_lib)
        val bestSellerName = view.findViewById<TextView>(R.id.name_author_lib)
        val best_auditor_name = view.findViewById<TextView>(R.id.best_auditor_name)
        val bestSellerViews = view.findViewById<TextView>(R.id.views)
        val bestSellerDesc = view.findViewById<TextView>(R.id.descAuthor_lib)

    }

}

class TheLatestAdapter(val context: Context?, val theLatestrData: List<BestSellerData>) : RecyclerView.Adapter<TheLatestAdapter.ViewHolder>(){

    private lateinit var adapter: Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheLatestAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.best_seller_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TheLatestAdapter.ViewHolder, position: Int) {
        holder.bestSellerImg.setImageResource(theLatestrData[position].imgCategory)
        holder.bestSellerName.text = theLatestrData[position].nameCategory
        holder.best_auditor_name.text = theLatestrData[position].nameAuditor
        holder.bestSellerViews.text = "( ${theLatestrData[position].views.toString() +')'}"
        holder.bestSellerDesc.text = "${theLatestrData[position].descBestSeller }" + "..."

    }

    override fun getItemCount(): Int {
        return theLatestrData.size
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val bestSellerImg = view.findViewById<ImageView>(R.id.img_author_lib)
        val bestSellerName = view.findViewById<TextView>(R.id.name_author_lib)
        val best_auditor_name = view.findViewById<TextView>(R.id.best_auditor_name)
        val bestSellerViews = view.findViewById<TextView>(R.id.views)
        val bestSellerDesc = view.findViewById<TextView>(R.id.descAuthor_lib)

    }
}

class FavouricAdapter(val context: Context?, val theLatestrData: List<BestSellerData>) : RecyclerView.Adapter<FavouricAdapter.ViewHolder>(){

    private lateinit var adapter: Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouricAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.best_seller_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouricAdapter.ViewHolder, position: Int) {
        holder.bestSellerImg.setImageResource(theLatestrData[position].imgCategory)
        holder.bestSellerName.text = theLatestrData[position].nameCategory
        holder.best_auditor_name.text = theLatestrData[position].nameAuditor
        holder.bestSellerViews.text = "( ${theLatestrData[position].views.toString() +')'}"
        holder.bestSellerDesc.text = "${theLatestrData[position].descBestSeller }" + "..."

    }

    override fun getItemCount(): Int {
        return theLatestrData.size
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val bestSellerImg = view.findViewById<ImageView>(R.id.img_author_lib)
        val bestSellerName = view.findViewById<TextView>(R.id.name_author_lib)
        val best_auditor_name = view.findViewById<TextView>(R.id.best_auditor_name)
        val bestSellerViews = view.findViewById<TextView>(R.id.views)
        val bestSellerDesc = view.findViewById<TextView>(R.id.descAuthor_lib)

    }
}

class ComingSoonAdapter(val context: Context?, val theLatestrData: List<BestSellerData>) : RecyclerView.Adapter<ComingSoonAdapter.ViewHolder>(){

    private lateinit var adapter: Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComingSoonAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.best_seller_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComingSoonAdapter.ViewHolder, position: Int) {
        holder.bestSellerImg.setImageResource(theLatestrData[position].imgCategory)
        holder.bestSellerName.text = theLatestrData[position].nameCategory
        holder.best_auditor_name.text = theLatestrData[position].nameAuditor
        holder.bestSellerViews.text = "( ${theLatestrData[position].views.toString() +')'}"
        holder.bestSellerDesc.text = "${theLatestrData[position].descBestSeller }" + "..."

    }

    override fun getItemCount(): Int {
        return theLatestrData.size
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val bestSellerImg = view.findViewById<ImageView>(R.id.img_author_lib)
        val bestSellerName = view.findViewById<TextView>(R.id.name_author_lib)
        val best_auditor_name = view.findViewById<TextView>(R.id.best_auditor_name)
        val bestSellerViews = view.findViewById<TextView>(R.id.views)
        val bestSellerDesc = view.findViewById<TextView>(R.id.descAuthor_lib)

    }
}

