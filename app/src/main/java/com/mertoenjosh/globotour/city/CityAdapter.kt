package com.mertoenjosh.globotour.city

import android.content.Context
import android.util.Log
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

    private val tag = this::class.simpleName

    // creates a view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        Log.i(tag, "onCreateViewHolder: View Holder Created")
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_city, parent, false)

        // inflate wih the grid layout
//        val itemView = LayoutInflater.from(context).inflate(R.layout.grid_item_city, parent, false)
        return CityViewHolder(itemView)
    }

    // binds data to the view holder
    override fun onBindViewHolder(cityViewHolder: CityViewHolder, position: Int) {
        Log.i(tag, "onBindViewHolder: position: $position")
        val city = cityList!![position]
        cityViewHolder.setData(city, position)
        cityViewHolder.setListeners()
    }

    // returns the total number of elements in the data set
    override fun getItemCount(): Int = cityList!!.size

    // use 'inner' to use properties of CityAdapter
    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
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

        fun setListeners() {
            imvDelete.setOnClickListener(this@CityViewHolder)
            imvFavourite.setOnClickListener(this@CityViewHolder)
        }

        override fun onClick(v: View?) {
            when(v!!.id) {
                R.id.imv_favorite -> addToFavourite()
                R.id.imv_delete -> deleteItem()
            }
        }

        fun deleteItem() {
            cityList?.removeAt(curretPosition)
            notifyItemRemoved(curretPosition)
            notifyItemRangeChanged(curretPosition, cityList!!.size)

            // delete from favourites also
            VacationSpots.favoriteCityList.remove(currentCity)

        }

        fun addToFavourite() {
            currentCity?.isFavorite = !(currentCity?.isFavorite!!) // toggle "isFavourite" Boolean Value

            if (currentCity?.isFavorite!!) { // if it is favourite - update icon and add the city object to favourite list
                imvFavourite.setImageDrawable(icFavouriteFilledImage)
                VacationSpots.favoriteCityList.add(currentCity!!)
            } else { // update icon and remove the city object from favourite list
                imvFavourite.setImageDrawable(icFavouriteBorderdImage)
                VacationSpots.favoriteCityList.remove(currentCity)

            }
        }
    }

}