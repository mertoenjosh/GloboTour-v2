package com.mertoenjosh.globotour.city

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.mertoenjosh.globotour.R
import com.mertoenjosh.globotour.city.VacationSpots.cityList

class CityAdapter(val context: Context, val classList: ArrayList<City>): RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    // creates a view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_city, parent, false)
        return CityViewHolder(itemView)
    }

    // binds data to the view holder
    override fun onBindViewHolder(cityViewHolder: CityViewHolder, position: Int) {
        val city = cityList!![position]
        cityViewHolder.setData(city, position)
    }

    // returns the total number of elements in the data set
    override fun getItemCount(): Int = cityList!!.size

    // use 'inner' to use properties of CityAdapter
    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var curretPosition: Int = -1
        private var currentCity: City? = null

        private val txvCityName = itemView.findViewById<TextView>(R.id.txv_city_name)
        private val imvCityName = itemView.findViewById<ImageView>(R.id.imv_city)
        private val imvDelete = itemView.findViewById<ImageView>(R.id.imv_delete)
        private val imvFavourite = itemView.findViewById<ImageView>(R.id.imv_favorite)

        // referencing to a drawable resource
        private val icFavouriteFilledImage = ResourcesCompat.getDrawable(context.resources,
            R.drawable.ic_favorite_filled, null)

        private val icFavouriteBorderdImage = ResourcesCompat.getDrawable(context.resources,
            R.drawable.ic_favorite_bordered, null)

        fun setData(city: City, position: Int) {
            txvCityName.text = city.name
            imvCityName.setImageResource(city.imageId)

            if (city.isFavorite)
                imvFavourite.setImageDrawable(icFavouriteFilledImage)
            else
                imvFavourite.setImageDrawable(icFavouriteBorderdImage)

            this.curretPosition = position
            this.currentCity = city
        }
    }

}