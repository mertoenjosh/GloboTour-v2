package com.mertoenjosh.globotour.favorite

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mertoenjosh.globotour.R
import com.mertoenjosh.globotour.city.City
import com.mertoenjosh.globotour.city.VacationSpots
import java.util.*
import kotlin.collections.ArrayList


class FavoriteFragment : Fragment() {
    private lateinit var favCityList: ArrayList<City>
    private lateinit var favouriteAdapter: FavouriteAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_favorite_list, container, false)


        setUpRecyclerView(view)

        return view
    }

    private fun setUpRecyclerView(view: View) {
        val context = requireContext()

        // create an instance of the adapter
        favCityList = VacationSpots.favoriteCityList as ArrayList<City> // cast from mutable list
        favouriteAdapter = FavouriteAdapter(context, favCityList)

        // init the recyclerview
        recyclerView = view.findViewById<RecyclerView>(R.id.favorite_recycler_view)

        // set the adapter
        recyclerView.adapter = favouriteAdapter
        recyclerView.setHasFixedSize(true)

        // define a layout manager
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager

        // attach the itemTouch Helper function to the recycler view
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    // implement swipe to delete and drag to rearrange
    private val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, targetViewHolder: RecyclerView.ViewHolder
        ): Boolean {
            // called when item is dragged
            val fromPosition = viewHolder.adapterPosition
            val toPosition = targetViewHolder.adapterPosition

            Collections.swap(favCityList, fromPosition, toPosition)
            recyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)

            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            // called when item is swiped
            val position = viewHolder.adapterPosition
            val deletedCity: City = favCityList[position]

            deleteItem(position)
            updateCityList(deletedCity, false)

            Snackbar.make(recyclerView, "DELETED", Snackbar.LENGTH_LONG)
                .setAction("UNDO") {
                    undoDelete(position, deletedCity)
                    updateCityList(deletedCity, true)
                }
                .show()
        }

    })

    private fun updateCityList(deletedCity: City, isFavourite: Boolean) {
        val cityList = VacationSpots.cityList!!
        val position = cityList.indexOf(deletedCity)
        cityList[position].isFavorite = isFavourite
    }

    private fun deleteItem(position: Int) {
        favCityList.removeAt(position)
        favouriteAdapter.notifyItemRemoved(position)
        favouriteAdapter.notifyItemRangeChanged(position, favCityList.size)
    }

    private fun undoDelete(position: Int, deletedCity: City) {
        favCityList.add(position, deletedCity)
        favouriteAdapter.notifyItemInserted(position)
        favouriteAdapter.notifyItemRangeChanged(position, favCityList.size)
    }
}
