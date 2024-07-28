package com.rodricorgom.semibreve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rodricorgom.semibreve.databinding.ActivityMainBinding
import com.rodricorgom.semibreve.ui.OptionsFragment
import com.rodricorgom.semibreve.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    var score : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, OptionsFragment.newInstance())
            .commit()
    }


}