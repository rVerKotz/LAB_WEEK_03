package com.example.lab_week_03

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView

interface CoffeeListener {
    fun onSelected(id: Int)
}

class MainActivity : AppCompatActivity(), CoffeeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tambahkan ListFragment secara dinamis saat pertama kali dibuat
        if (savedInstanceState == null) {
            findViewById<FragmentContainerView>(R.id.fragment_container).let { container ->
                val listFragment = ListFragment()
                supportFragmentManager.beginTransaction()
                    .add(container.id, listFragment)
                    .commit()
            }
        }
    }

    override fun onSelected(id: Int) {
        // Ganti dengan DetailFragment baru setiap kali item dipilih
        findViewById<FragmentContainerView>(R.id.fragment_container).let { container ->
            val detailFragment = DetailFragment.newInstance(id)
            supportFragmentManager.beginTransaction()
                .replace(container.id, detailFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}