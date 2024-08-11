package com.rodricorgom.semibreve

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rodricorgom.semibreve.databinding.ActivityMainBinding
import com.rodricorgom.semibreve.ui.MainMenuFragment
import com.rodricorgom.semibreve.ui.OptionsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    //var score:

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.commit()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, MainMenuFragment.newInstance())
            .commit()
    }


}