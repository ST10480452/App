package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityCaloriesBinding

class CaloriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCaloriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCaloriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load saved prefs
        updateUIFromPrefs()

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnChangeCalGoal.setOnClickListener { showChangeGoalDialog() }

        binding.btnWithin.setOnClickListener {
            if (Preferences.isCalorieLoggedToday()) {
                android.widget.Toast.makeText(this, "Already logged today!", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // mark today as within target
            Preferences.calorieWeeklyCount = Preferences.calorieWeeklyCount + 1
            Preferences.lastCalorieLogDate = Preferences.getTodayDate()
            updateUIFromPrefs()
        }

        binding.btnOver.setOnClickListener {
            if (Preferences.isCalorieLoggedToday()) {
                android.widget.Toast.makeText(this, "Already logged today!", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // mark today as over target (no increment)
            Preferences.lastCalorieLogDate = Preferences.getTodayDate()
            updateUIFromPrefs()
        }
    }

    private fun updateUIFromPrefs() {
        val goal = Preferences.calorieGoal
        val weekly = Preferences.calorieWeeklyCount
        val loggedToday = Preferences.isCalorieLoggedToday()
        
        binding.txtCalGoal.text = "Daily target: $goal calories"
        binding.txtWeeklyCount.text = "$weekly/7 days on target"
        
        binding.btnWithin.isEnabled = !loggedToday
        binding.btnOver.isEnabled = !loggedToday
        
        if (loggedToday) {
            binding.txtWeeklyCount.text = "$weekly/7 days on target\n(Already logged today)"
        }
    }

    private fun showChangeGoalDialog() {
        val input = android.widget.EditText(this)
        input.hint = "Enter daily calories (number)"
        input.inputType = android.text.InputType.TYPE_CLASS_NUMBER

        AlertDialog.Builder(this)
            .setTitle("Set Calorie Goal")
            .setView(input)
            .setPositiveButton("Save") { _, _ ->
                val v = input.text.toString().toIntOrNull()
                if (v != null) {
                    Preferences.calorieGoal = v
                }
                updateUIFromPrefs()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
