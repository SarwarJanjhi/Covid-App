package com.anew.retrofitdataretrievepractice

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import java.util.Locale

class CountryAdapter(context: Context, private var countries: List<CountryInfo>) :
    ArrayAdapter<CountryInfo>(context, 0, countries), Filterable {

    private var filteredCountries: List<CountryInfo> = countries

    override fun getCount(): Int {
        return filteredCountries.size
    }

    override fun getItem(position: Int): CountryInfo? {
        return filteredCountries[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            android.R.layout.simple_list_item_1, parent, false
        )

        val country = getItem(position)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = country?.country

        view.setOnClickListener {
            val intent = Intent(context, Full_report::class.java).apply {
                putExtra("COUNTRY_NAME", country?.country)
                putExtra("COVID_CASES", country?.cases.toString())
                putExtra("FLAG_URL", country?.countryInfo?.flag)
                putExtra("DEATHS_NUM", country?.deaths.toString())
                putExtra("ACTIVE_NUM", country?.active.toString())
                putExtra("RECOVERED_NUM", country?.recovered.toString())
            }
            context.startActivity(intent)
        }

        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                val queryString = constraint?.toString()?.lowercase(Locale.getDefault())

                filterResults.values = if (queryString.isNullOrEmpty()) {
                    countries
                } else {
                    countries.filter {
                        it.country.lowercase(Locale.getDefault()).contains(queryString)
                    }
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredCountries = results?.values as List<CountryInfo>
                notifyDataSetChanged()
            }
        }
    }
}
