package com.mertoenjosh.globotour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var toolbar        : MaterialToolbar
    private lateinit var navController  : NavController
    private lateinit var bottomNavView  : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views
        toolbar         = findViewById(R.id.activity_main_toolbar)
        bottomNavView   = findViewById(R.id.bottom_nav_view)



        // Get NavHostFragment and NavController
        val navHostFrag = supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        navController   = navHostFrag.navController

        // Define AppBarConfiguration
        val topLevelDestinations = setOf(R.id.fragmentCityList, R.id.fragmentFavoriteList)
        val appBarConfiguration = AppBarConfiguration(topLevelDestinations)

        // Connect toolbar with NavController
        toolbar.setupWithNavController(navController, appBarConfiguration)

        // connect BottomNavigationView with NavController
        bottomNavView.setupWithNavController(navController)
    }

}