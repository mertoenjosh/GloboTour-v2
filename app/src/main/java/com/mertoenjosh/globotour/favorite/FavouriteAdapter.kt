package com.mertoenjosh.globotour.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mertoenjosh.globotour.R
import com.mertoenjosh.globotour.city.City

class FavouriteAdapter(val context: Context, var favCityList: ArrayList<City>): RecyclerView.Adapter<FavouriteAdapter.FavCityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavCityViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_favorite, parent, false)
        return FavCityViewHolder(itemView)
    }

    override fun onBindViewHolder(favCityViewHolder: FavCityViewHolder, position: Int) {
        val city = favCityList[position]
        favCityViewHolder.setData(city, position)
    }

    override fun getItemCount(): Int = favCityList.size


    inner class FavCityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var currentPosition = -1
        private var currFavCity: City? = null

        private val txvCityImage = itemView.findViewById<ImageView>(R.id.imv_city)
        private val imvCityName = itemView.findViewById<TextView>(R.id.txv_city_name)


        fun setData(favouriteCity: City, position: Int) {
            this.currentPosition = position
            this.currFavCity = favouriteCity

            imvCityName.text = favouriteCity.name
            txvCityImage.setImageResource(favouriteCity.imageId)

            this.currentPosition = position
            this.currFavCity = favouriteCity

        }

    }

}