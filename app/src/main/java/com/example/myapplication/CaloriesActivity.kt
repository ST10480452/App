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

        binding.btnChangeCalGoal.setOnClickListener { showChangeGoalDialog() }

        binding.btnWithin.setOnClickListener {
            // mark today as within target
            Preferences.calorieWeeklyCount = Preferences.calorieWeeklyCount + 1
            updateUIFromPrefs()
        }

        binding.btnOver.setOnClickListener {
            // mark today as over target (no increment)
            updateUIFromPrefs()
        }
    }

    private fun updateUIFromPrefs() {
        val goal = Preferences.calorieGoal
        val weekly = Preferences.calorieWeeklyCount
        binding.txtCalGoal.text = "Daily target: $goal calories"
        binding.txtWeeklyCount.text = "$weekly/7 days on target"
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
