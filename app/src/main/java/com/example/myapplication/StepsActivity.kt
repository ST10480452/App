package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityStepsBinding

class StepsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStepsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStepsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateUI()

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnChangeGoal.setOnClickListener {
            showGoalDialog()
        }

        binding.btnComplete.setOnClickListener {
            if (Preferences.isStepsCompletedToday()) {
                android.widget.Toast.makeText(this, "Already marked today!", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            Preferences.stepsCompletedToday = true
            Preferences.stepsStreak = Preferences.stepsStreak + 1
            Preferences.lastStepsCompletionDate = Preferences.getTodayDate()
            updateUI()
        }
    }

    private fun updateUI() {
        val goal = Preferences.stepsGoal
        val streak = Preferences.stepsStreak
        val completedToday = Preferences.isStepsCompletedToday()
        
        binding.txtGoal.text = "Target: $goal steps"
        binding.txtStreak.text = "You've hit steps $streak days in a row"
        binding.btnComplete.text = if (completedToday) "✓ Goal Hit Today" else "Hit Today's Goal"
        binding.btnComplete.isEnabled = !completedToday
    }

    private fun showGoalDialog() {
        val input = android.widget.EditText(this)
        input.hint = "Enter new goal"
        input.inputType = android.text.InputType.TYPE_CLASS_NUMBER

        AlertDialog.Builder(this)
            .setTitle("Set Steps Goal")
            .setView(input)
            .setPositiveButton("Save") { _, _ ->
                val newGoal = input.text.toString().toIntOrNull()
                if (newGoal != null) {
                    Preferences.stepsGoal = newGoal
                }
                updateUI()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
