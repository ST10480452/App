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

        binding.btnChangeGoal.setOnClickListener {
            showGoalDialog()
        }

        binding.btnComplete.setOnClickListener {
            val currentStatus = Preferences.stepsCompletedToday
            Preferences.stepsCompletedToday = !currentStatus
            if (!currentStatus) {
                Preferences.stepsStreak = Preferences.stepsStreak + 1
            }
            updateUI()
        }
    }

    private fun updateUI() {
        val goal = Preferences.stepsGoal
        val streak = Preferences.stepsStreak
        val completed = Preferences.stepsCompletedToday
        
        binding.txtGoal.text = "Target: $goal steps"
        binding.txtStreak.text = "You've hit steps $streak days in a row"
        binding.btnComplete.text = if (completed) "✓ Goal Hit Today" else "Hit Today's Goal"
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
