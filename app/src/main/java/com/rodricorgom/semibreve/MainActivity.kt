package com.rodricorgom.semibreve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rodricorgom.semibreve.databinding.ActivityMainBinding
import com.rodricorgom.semibreve.ui.OptionsFragment
import com.rodricorgom.semibreve.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, OptionsFragment.newInstance())
            //.add(R.id.fragmentContainerView, OptionsFragment())
            .commit()
    }


}