package com.anew.retrofitdataretrievepractice

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rc_items_show.view.*

class MyAdaptor(private val context: Context, private val dataList: List<CountryInfo>) : RecyclerView.Adapter<MyAdaptor.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var CountryName: TextView = itemView.tv_countryName
        var CovidCases: TextView = itemView.tv_covid_cases
        var flag: ImageView = itemView.iv_flag_Image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.rc_items_show, parent, false)
        Log.d("Ad1", "onCreateViewHolder passed")
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = dataList[position]
        holder.CountryName.text = currentitem.country
        holder.CovidCases.text = "Cases : ${currentitem.cases.toString()}"

        // Use Picasso to load the flag image
        Picasso.get().load(currentitem.countryInfo.flag).into(holder.flag)
        Log.d("Ad2", "onBindViewHolder passed")

        // Set an OnClickListener to the itemView
        holder.itemView.setOnClickListener {
            val intent = Intent(context, Full_report::class.java).apply {
                putExtra("COUNTRY_NAME", currentitem.country)
                putExtra("COVID_CASES", currentitem.cases.toString())
                putExtra("FLAG_URL", currentitem.countryInfo.flag)
                putExtra("ACTIVE_NUM", currentitem.active.toString())
                putExtra("DEATHS_NUM", currentitem.deaths.toString())
                putExtra("RECOVERED_NUM", currentitem.recovered.toString())
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        Log.d("Ad3", "getItemCount passed")
        return dataList.size
    }
}
