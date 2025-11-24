package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Preferences.init(applicationContext) // init SharedPreferences

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // App title display

        binding.btnSteps.setOnClickListener {
            startActivity(Intent(this, StepsActivity::class.java))
        }

        binding.btnCalories.setOnClickListener {
            startActivity(Intent(this, CaloriesActivity::class.java))
        }

        binding.btnWorkout.setOnClickListener {
            startActivity(Intent(this, WorkoutActivity::class.java))
        }

        binding.btnWeight.setOnClickListener {
            startActivity(Intent(this, WeightActivity::class.java))
        }
    }
}
