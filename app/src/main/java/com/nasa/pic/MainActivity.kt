package com.nasa.pic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nasa.pic.databinding.ActivityMainBinding
import com.nasa.pic.presentation.NasaFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
        if (savedInstanceState == null) {
            val fm = supportFragmentManager
            val ft = fm.beginTransaction()
            ft.add(R.id.container, NasaFragment.newInstance())
            ft.commit()
        }
    }
}