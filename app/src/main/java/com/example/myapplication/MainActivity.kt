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

        // Display current date
        binding.txtDate.text = getCurrentDateString()

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

    private fun getCurrentDateString(): String {
        val calendar = java.util.Calendar.getInstance()
        val months = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
        return "Today: ${months[calendar.get(java.util.Calendar.MONTH)]} ${calendar.get(java.util.Calendar.DAY_OF_MONTH)}, ${calendar.get(java.util.Calendar.YEAR)}"
    }
}
